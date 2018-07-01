package com.example.foush.otgmvp.ui.Login;

import com.example.foush.otgmvp.models.User;

/**
 * Created by foush on 09/02/18.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> {
    void startSignIn(User user, String token);
}
