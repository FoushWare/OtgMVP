package com.example.foush.otgmvp.data;

import com.example.foush.otgmvp.data.SharedPrefsHelper;

/**
 * Created by foush on 09/02/18.
 */

public class DataManager {
    SharedPrefsHelper mhsaredPrefsHelper;
    public DataManager(SharedPrefsHelper sharedPrefsHelper){
        mhsaredPrefsHelper=sharedPrefsHelper;
    }

    public void clear(){
        mhsaredPrefsHelper.clear();
    }
    public void saveEmail(String email){
        mhsaredPrefsHelper.putEmail(email);
    }
    public String getEmail(){
        return mhsaredPrefsHelper.getEmail();
    }
    public void setLoggedIn(){
        mhsaredPrefsHelper.setLoggedInMode(true);
    }
    public Boolean getLoggedInMode(){
        return mhsaredPrefsHelper.getLoggedInMode();
    }







}
