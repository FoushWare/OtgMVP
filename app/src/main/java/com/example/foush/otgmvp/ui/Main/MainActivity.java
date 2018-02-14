package com.example.foush.otgmvp.ui.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.otgMvp;
import com.example.foush.otgmvp.ui.Balance.BalanceActivity;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Dataset.DataSetActivity;
import com.example.foush.otgmvp.ui.Login.LoginPresenter;
import com.flipboard.bottomsheet.BottomSheetLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.buttonSearch)
    LinearLayout buttonSearch;
    @BindView(R.id.buttonSignOut)
    LinearLayout buttonSignOut;
    @BindView(R.id.buttonShopping)
    LinearLayout buttonShopping;
    @BindView(R.id.btns)
    LinearLayout btns;
    @BindView(R.id.QRBtn)
    FloatingActionButton QRBtn;
    @BindView(R.id.bottomsheet)
    BottomSheetLayout bottomsheet;

    DataManager mDataManager;
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataManager = ((otgMvp) getApplication()).getmDataManger();
        mMainPresenter = new MainPresenter(mDataManager);
        mMainPresenter.onAttach(this);



        ButterKnife.bind(this);
        BottomSheetLayout bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheet.showWithSheetView(LayoutInflater.from(MainActivity.this).inflate(R.layout.my_sheet_layout, bottomSheet, false));


        Button button = (Button) findViewById(R.id.take_pics);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataSetActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Toast.makeText(this, ""+mDataManager.getToken(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, ""+mDataManager.getEmail(), Toast.LENGTH_LONG).show();


    }

    @OnClick({R.id.buttonProfile, R.id.buttonBalance, R.id.buttonHistory, R.id.buttonSearch, R.id.buttonSignOut, R.id.buttonShopping, R.id.btns, R.id.QRBtn, R.id.bottomsheet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonProfile:
                break;
            case R.id.buttonBalance:
                Intent intent=new Intent(MainActivity.this, BalanceActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.buttonHistory:
                break;
            case R.id.buttonSearch:
                break;
            case R.id.buttonSignOut:
                break;
            case R.id.buttonShopping:
                break;
            case R.id.btns:
                break;
            case R.id.QRBtn:
                break;
            case R.id.bottomsheet:
                break;
        }
    }
}
