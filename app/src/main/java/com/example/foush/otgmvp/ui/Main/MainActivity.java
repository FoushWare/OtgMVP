package com.example.foush.otgmvp.ui.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.models.Responses.MainResponse;
import com.example.foush.otgmvp.otgStore;
import com.example.foush.otgmvp.ui.Balance.BalanceActivity;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.History.HistoryActivity;
import com.example.foush.otgmvp.ui.Notification.NotificationActivity;
import com.example.foush.otgmvp.ui.Profile.ProfileActivity;
import com.example.foush.otgmvp.ui.QR.QRActivity;
import com.example.foush.otgmvp.ui.Splash.SplashActivity;
import com.flipboard.bottomsheet.BottomSheetLayout;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.foush.otgmvp.ui.Notification.MyFCMService.MY_PREFS_NAME;

/**
 * Created by foush on 09/02/18.
 */

public class MainActivity extends BaseActivity implements MainMvpView {
    @BindView(R.id.buttonProfile)
    LinearLayout buttonProfile;
    @BindView(R.id.buttonBalance)
    LinearLayout buttonBalance;
    @BindView(R.id.buttonHistory)
    LinearLayout buttonHistory;
    @BindView(R.id.buttoncart)
    LinearLayout buttoncart;
    @BindView(R.id.buttonSignOut)
    LinearLayout buttonSignOut;
//    @BindView(R.id.buttonShopping)
//    LinearLayout buttonShopping;
    @BindView(R.id.btns)
    LinearLayout btns;
    @BindView(R.id.QRBtn)
    ImageView QRBtn;
    @BindView(R.id.bottomsheet)
    BottomSheetLayout bottomsheet;

    DataManager mDataManager;
    MainPresenter mMainPresenter;
    BottomSheetLayout bottomSheet;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataManager = ((otgStore) getApplication()).getmDataManger();
        mMainPresenter = new MainPresenter(mDataManager);
        mMainPresenter.onAttach(this);
        bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
//        FloatingActionButton scanQR =  findViewById(R.id.QRBtn);
//        QRBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottomSheet.showWithSheetView(LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_view, bottomSheet, false));
//            }
//        });



        ButterKnife.bind(this);
//        BottomSheetLayout bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
//        bottomSheet.showWithSheetView(LayoutInflater.from(MainActivity.this).inflate(R.layout.my_sheet_layout, bottomSheet, false));


//        Button button = (Button) findViewById(R.id.take_pics);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, DataSetActivity.class);
//                startActivity(intent);
//
//            }
//        });


//        Toast.makeText(this, ""+mDataManager.getToken(), Toast.LENGTH_LONG).show();
//        Toast.makeText(this, ""+mDataManager.getEmail(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, ""+ Environment.getExternalStorageDirectory(), Toast.LENGTH_LONG).show();

//        Log.d("test saved order id", "orderID: "+mDataManager.getOrderID());

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

            int orderID = prefs.getInt("orderID", 0); //0 is the default value.

//        Log.d("test saved order id", "orderID: "+orderID);

            mDataManager.saveOrderID(orderID);


    }

    @OnClick({R.id.buttoncart,R.id.buttonProfile, R.id.buttonBalance, R.id.buttonHistory, R.id.buttonSignOut, R.id.btns, R.id.QRBtn, R.id.bottomsheet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonProfile:
                Intent intent=new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);

                break;
            case R.id.buttonBalance:
                intent = new Intent(MainActivity.this, BalanceActivity.class);
                startActivity(intent);
                
                break;
            case R.id.buttonHistory:
                intent=new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.buttoncart:
                intent=new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonSignOut:
                ApiHelper apiHelper = ServiceGenerator.createService(ApiHelper.class);
                Call<MainResponse> call = apiHelper.Logout(mDataManager.getToken());
                call.enqueue(new Callback<MainResponse>() {
                    @Override
                    public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                        if (response.isSuccessful()) {
                            mDataManager.LogoutSession();
                            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(MainActivity.this, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }


                    @Override
                    public void onFailure(Call<MainResponse> call, Throwable t) {

                    }
                });


                break;
//            case R.id.buttonShopping:
//                break;
            case R.id.btns:
                break;
            case R.id.QRBtn:
                intent=new Intent(MainActivity.this, QRActivity.class);
                startActivity(intent);
                break;
            case R.id.bottomsheet:
                break;
        }
    }
}
