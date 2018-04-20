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
    public void savePassword(String password){
        msharedPrefsHelper.putPassword(password);
    }
    public String getPassword(){
        return msharedPrefsHelper.getPassword();
    }
    public void LogoutSession(){
        msharedPrefsHelper.setLoggedInMode(false);
    }

/** Firebase Token*/


    public void saveFireBaseToken(String token){
    msharedPrefsHelper.putFireBaseToken(token);
}
    public String getFireBaseToken(){
        return msharedPrefsHelper.getFireBaseToken();
    }

    public void saveQR_Path(String path){
    msharedPrefsHelper.putQR(path);
}
    public String getQR_Path(){
        return msharedPrefsHelper.getQR();
    }





}
