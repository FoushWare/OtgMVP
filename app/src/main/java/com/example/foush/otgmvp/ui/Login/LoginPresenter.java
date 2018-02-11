package com.example.foush.otgmvp.ui.Login;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;

/**
 * Created by foush on 09/02/18.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void startSignIn(String email,String token) {
        getDataManager().saveEmail(email);
        getDataManager().setLoggedIn();
        getDataManager().saveToken(token);
        getMvpView().openMainActivity();


    }
}
