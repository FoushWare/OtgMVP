package com.example.foush.otgmvp.ui.Signup;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;

/**
 * Created by foush on 09/02/18.
 */

public class SignUpPresenter<V extends SignUpMvpView> extends BasePresenter<V> implements SignUpMvpPresenter<V> {
    public SignUpPresenter(DataManager dataManager) {
        super(dataManager);
    }



}
