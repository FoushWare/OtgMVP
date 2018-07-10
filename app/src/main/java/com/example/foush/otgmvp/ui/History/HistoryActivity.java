package com.example.foush.otgmvp.ui.History;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.foush.otgmvp.Adapters.HistoryAdapter;
import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.models.Item;

import com.example.foush.otgmvp.otgStore;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.DetailHistory.DetailHistoryActivity;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foush on 09/02/18.
 */

public class HistoryActivity extends BaseActivity implements HistoryMvpView {
    ProgressDialog dialog;
    DataManager mDataManager;
    HistoryPresenter mHistoryPresenter;
    public ArrayList<String> shoppingDays;
    public ArrayList<Item.SingleItem> shoppingDayItems;
    public ArrayList<Integer> itemsNumberPerDay;
    public ArrayList<Item.SingleItem> DetailHistoryItems;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_main);
        mDataManager = ((otgStore) getApplication()).getmDataManger();
        mHistoryPresenter = new HistoryPresenter(mDataManager);
        mHistoryPresenter.onAttach(this);

        initView();


    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    Toast.makeText(getApplicationContext(), ""+itemsNumberPerDay.get(position), Toast.LENGTH_SHORT).show();
                    Toast.makeText(HistoryActivity.this, "postion is"+position, Toast.LENGTH_SHORT).show();
                    DetailHistoryItems=new ArrayList<>();
                    for (int i = 0; i < itemsNumberPerDay.get(position); i++) {
                        DetailHistoryItems.add(shoppingDayItems.get(i));
                    }


                    Intent intent=new Intent(HistoryActivity.this, DetailHistoryActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("Detail_day_items",DetailHistoryItems);
                    intent.putExtras(bundle);
                    startActivity(intent);



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
        mHistoryPresenter.updateUi();

    }

    @Override
    public void RequestUiInfo() {

        ApiHelper apiHelper = ServiceGenerator.createService(ApiHelper.class);
        Call<Item> call = apiHelper.UserHistory(mDataManager.getToken());

        progress();

        call.enqueue(new Callback<Item>() {

            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {


                    //for this Activity to get the date of each shopping day
                    int ShoppingDaysNumber=response.body().history.size();
                        shoppingDays=new ArrayList<>();
                        shoppingDayItems=new ArrayList<>();
                        itemsNumberPerDay=new ArrayList<Integer>();

                    for (int i = 0; i <ShoppingDaysNumber ; i++) {
                        shoppingDays.add(response.body().history.get(i).date);

                        int shoppingDayItemsNumber=response.body().history.get(i).items.size();
                        itemsNumberPerDay.add(shoppingDayItemsNumber);
                        for (int j = 0; j <shoppingDayItemsNumber ; j++) {

                            shoppingDayItems.add(new Item.SingleItem(
                                    response.body().history.get(i).items.get(j).name,
                                    response.body().history.get(i).items.get(j).price,
                                    response.body().history.get(i).items.get(j).quantity)
                                    );

                        }


                    }







                    mHistoryPresenter.UpdateRecyclerControler(shoppingDays,shoppingDayItems,itemsNumberPerDay);





                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(HistoryActivity.this, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(HistoryActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(HistoryActivity.this, "unknown problem", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void UpdateRecyclerView(ArrayList<String> shoppingDays,ArrayList< Item.SingleItem> items,ArrayList<Integer> itemNumberPerDay) {
        RecyclerView.Adapter adapter = new HistoryAdapter(shoppingDays,items,itemNumberPerDay);
        recyclerView.setAdapter(adapter);

    }

    public void progress() {
        dialog = new ProgressDialog(HistoryActivity.this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


}
