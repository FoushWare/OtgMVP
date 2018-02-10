package com.example.foush.otgmvp;

import android.app.Application;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.SharedPrefsHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.SharedPrefsHelper;

/**
 * Created by foush on 09/02/18.
 */

public class otgMvp extends Application {
    SharedPrefsHelper  mSharedPrefsHelper;
    DataManager         mDataManger;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPrefsHelper=new SharedPrefsHelper(getApplicationContext());
        mDataManger=new DataManager(mSharedPrefsHelper);
    }
    public DataManager getmDataManger(){return mDataManger;}
}
