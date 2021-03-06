package com.example.foush.otgmvp.ui.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.foush.otgmvp.*;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Base.MvpView;
import com.example.foush.otgmvp.ui.Login.LoginActivity;
import com.example.foush.otgmvp.ui.Main.MainActivity;
import com.example.foush.otgmvp.ui.Settings.SettingsMvpView;
import com.example.foush.otgmvp.ui.Settings.SettingsPresenter;

/**
 * Created by foush on 09/02/18.
 */

public class SplashActivity extends BaseActivity implements SplashMvpView {
    DataManager mDataManager;
    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);

        mDataManager=((otgMvp)getApplication()).getmDataManger();
        mSplashPresenter=new SplashPresenter(mDataManager);
        mSplashPresenter.onAttach(this);
        mSplashPresenter.DecideNextActivity();





    }

    @Override
    public void openMainActivity() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void openLoginActivity() {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}