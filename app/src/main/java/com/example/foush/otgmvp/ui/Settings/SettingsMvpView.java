package com.example.foush.otgmvp.ui.Settings;

import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.ui.Base.MvpView;

/**
 * Created by foush on 09/02/18.
 */

public interface SettingsMvpView extends MvpView{
    void initView();
    void RequestUiInfo();
    void setUiValues(User user);
    void progress();
    void fieldsOldValues();
    void updateUserBasic(User user,Boolean twoRequest,User userChange);
    void updateUserPassword(User user);
    void openProfileActivity();
    void openSplashActivity();



}
