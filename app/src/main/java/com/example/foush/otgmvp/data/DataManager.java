package com.example.foush.otgmvp.data;

import com.example.foush.otgmvp.data.SharedPrefsHelper;

/**
 * Created by foush on 09/02/18.
 */

public class DataManager {
    SharedPrefsHelper msharedPrefsHelper;
    public DataManager(SharedPrefsHelper sharedPrefsHelper){
        msharedPrefsHelper=sharedPrefsHelper;
    }

    public void clear(){
        msharedPrefsHelper.clear();
    }
    public void saveEmail(String email){
        msharedPrefsHelper.putEmail(email);
    }
    public String getEmail(){
        return msharedPrefsHelper.getEmail();
    }
    public void setLoggedIn(){
        msharedPrefsHelper.setLoggedInMode(true);
    }
    public Boolean getLoggedInMode(){
        return msharedPrefsHelper.getLoggedInMode();
    }
    public void saveToken(String token){
        msharedPrefsHelper.putToken(token);
    }
    public String getToken(){
        return msharedPrefsHelper.getToken();
    }







}
