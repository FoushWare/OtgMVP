package com.example.foush.otgmvp.ui.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Main.MainMvpView;

/**
 * Created by foush on 09/02/18.
 */

public class MainActivity extends BaseActivity implements MainMvpView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
