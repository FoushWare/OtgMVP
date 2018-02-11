package com.example.foush.otgmvp.ui.Login;

import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.ui.Base.MvpView;

/**
 * Created by foush on 09/02/18.
 */

public interface LoginMvpView extends MvpView {
    void onLoginClick();
    void onSignUpClick();
    void openMainActivity();
    void RequestApi(User user);

    void initView();
}
