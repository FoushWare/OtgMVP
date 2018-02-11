package com.example.foush.otgmvp.ui.Signup;

import com.example.foush.otgmvp.models.User;

/**
 * Created by foush on 09/02/18.
 */

public interface SignUpMvpPresenter<V extends SignUpMvpView> {
    void startSignUp(String email);
}
