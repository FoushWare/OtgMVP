package com.example.foush.otgmvp.ui.Settings;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Settings.SettingsMvpView;
import com.example.foush.otgmvp.ui.Settings.SettingsPresenter;

/**
 * Created by foush on 09/02/18.
 */

public class SettingsActivity extends BaseActivity implements SettingsMvpView {
    DataManager mDataManager;
    SettingsPresenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_settings);







    }


}
