package com.example.foush.otgmvp.ui.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.otgMvp;
import com.example.foush.otgmvp.ui.Balance.BalancePresenter;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Main.*;
import com.example.foush.otgmvp.ui.Settings.SettingsActivity;
import com.example.foush.otgmvp.utils.CommonUtils;
import android.support.v7.widget.Toolbar;

/**
 * Created by foush on 09/02/18.
 */

public class ProfileActivity extends BaseActivity implements ProfileMvpView {
    DataManager mDataManager;
    ProfilePresenter mProfilePresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mDataManager = ((otgMvp) getApplication()).getmDataManger();
        mProfilePresenter= new ProfilePresenter(mDataManager);
        mProfilePresenter.onAttach(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcome to OTG store :) ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){

            case R.id.action_settings:
                Intent intent= new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;


        }






        return super.onOptionsItemSelected(item);
    }







}
