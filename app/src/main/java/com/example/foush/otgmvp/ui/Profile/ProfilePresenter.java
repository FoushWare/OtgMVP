package com.example.foush.otgmvp.ui.Profile;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.ui.Base.BasePresenter;
import com.example.foush.otgmvp.ui.Profile.ProfileMvpPresenter;

/**
 * Created by foush on 09/02/18.
 */

public class ProfilePresenter<V extends ProfileMvpView> extends BasePresenter<V> implements ProfileMvpPresenter<V> {
    public ProfilePresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void updateUi() {
        getMvpView().RequestUiInfo();

    }

    @Override
    public void fillUiFields(User user,String picStatus) {
        getMvpView().setUiValues( user,picStatus);
    }
}
