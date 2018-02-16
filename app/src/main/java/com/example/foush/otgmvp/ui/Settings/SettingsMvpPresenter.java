package com.example.foush.otgmvp.ui.Settings;

import com.example.foush.otgmvp.models.User;

/**
 * Created by foush on 09/02/18.
 */

public interface SettingsMvpPresenter<V extends SettingsMvpView> {

    void updateUi();
    void fillUiFields(User user);
    void checkChange(User oldUser,User UserChange);





}
