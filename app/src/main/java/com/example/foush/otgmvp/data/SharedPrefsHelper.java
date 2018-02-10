package com.example.foush.otgmvp.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by foush on 09/02/18.
 */

public class SharedPrefsHelper {

    //name the sharedPreference
    public static final String MY_PREFS="MY_APP";
    public static final String MY_EMAIL="EMAIL";



    SharedPreferences msharedPreferences;

    public SharedPrefsHelper(Context context){
        msharedPreferences=context.getSharedPreferences(MY_PREFS,Context.MODE_PRIVATE);

    }
    public void clear(){
        msharedPreferences.edit().clear();
    }
    public void putEmail(String email){
        msharedPreferences.edit().putString(MY_EMAIL,email);
    }
    public String getEmail(){
        return msharedPreferences.getString(MY_EMAIL,null);
    }
    public void setLoggedInMode(Boolean loggedInMode){
        msharedPreferences.edit().putBoolean("IS_LOGEED_IN",loggedInMode);
    }
    public Boolean getLoggedInMode(){
        return msharedPreferences.getBoolean("IS_LOGGED_IN",false);
    }


















}
