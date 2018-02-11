package com.example.foush.otgmvp.ui.Signup;

import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.ui.Base.MvpView;

/**
 * Created by foush on 09/02/18.
 */

public interface SignUpMvpView extends MvpView {
    void initView();
    void onSignUpClick();
    void RequestApi(User user);
    void openLogInActivity();

}
