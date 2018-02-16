package com.example.foush.otgmvp.ui.Login;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.ui.Base.BasePresenter;

/**
 * Created by foush on 09/02/18.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void startSignIn(User user, String token) {
        getDataManager().saveEmail(user.email);
        getDataManager().savePassword(user.password);
        getDataManager().setLoggedIn();
        getDataManager().saveToken(token);
        getMvpView().openMainActivity();


    }
}
