package com.example.foush.otgmvp.ui.Notification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.foush.otgmvp.Adapters.OrderItemsAdapter;
import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.models.orderItem;
import com.example.foush.otgmvp.otgStore;
import com.example.foush.otgmvp.ui.Base.BaseActivity;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foush on 09/02/18.
 */

public class NotificationActivity  extends BaseActivity  implements NotificationMvpView {
    DataManager mDataManager;
    NotificationPresenter mNotificationPresenter;
    RecyclerView recyclerView;
    public ArrayList<orderItem.itemsArray> Items;



    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactions_main);
        mDataManager = ((otgStore) getApplication()).getmDataManger();
        mNotificationPresenter = new NotificationPresenter(mDataManager);
        mNotificationPresenter.onAttach(this);

        initView();








    }

    @Override
    public void initView() {
        /** The View will be  RecyclerView and CardView to show products that the use toke **/

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view3);
        Log.d("recycler view", "initView: "+recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        //Don't forget to link the adapter to the RecyclerView
        mNotificationPresenter.updateUi();
    }

    @Override
    public void RequestUiInfo() {

        ApiHelper apiHelper = ServiceGenerator.createService(ApiHelper.class);
        Call<orderItem> call = apiHelper.UserItems(mDataManager.getOrderID(),mDataManager.getToken());

        progress();

        call.enqueue(new Callback<orderItem>() {

            @Override
            public void onResponse(Call<orderItem> call, Response<orderItem> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    //get the items number
                    int itemsNumber=response.body().items.size();

                    Items=new ArrayList<>();

                    for (int i = 0; i <itemsNumber ; i++) {
                        Toast.makeText(NotificationActivity.this, "quantity"+response.body().items.get(i).quantity, Toast.LENGTH_SHORT).show();
                        //remove the item from the notification activity if the quantity is zero
                        if (response.body().items.get(i).quantity==0){
                            Items.remove(i);
                        }
                        Items.add(new orderItem.itemsArray(
                                response.body().items.get(i).title,
                                response.body().items.get(i).price,
                                response.body().items.get(i).quantity)
                        );
                    }


                    mNotificationPresenter.UpdateRecyclerControler(Items);


                }
                else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(NotificationActivity.this, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(NotificationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dialog.dismiss();

            }
        });
    }

    @Override
    public void UpdateRecyclerView(ArrayList<orderItem.itemsArray> Items) {
        RecyclerView.Adapter adapter = new OrderItemsAdapter(Items);
        recyclerView.setAdapter(adapter);

    }

    public void progress(){

        dialog = new ProgressDialog(NotificationActivity.this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }






}

