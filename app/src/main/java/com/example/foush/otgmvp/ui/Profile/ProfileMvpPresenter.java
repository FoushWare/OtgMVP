package com.example.foush.otgmvp.ui.Profile;

import com.example.foush.otgmvp.models.User;

/**
 * Created by foush on 09/02/18.
 */

public interface ProfileMvpPresenter<V extends ProfileMvpView> {
    void updateUi();
    void fillUiFields(User user,String picStatus);

}
