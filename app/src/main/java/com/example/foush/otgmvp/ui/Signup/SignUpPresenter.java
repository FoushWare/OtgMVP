package com.example.foush.otgmvp.ui.Signup;

import android.widget.Toast;

import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.models.Responses.MainResponse;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.ui.Base.BasePresenter;
import com.example.foush.otgmvp.ui.Splash.SplashPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foush on 09/02/18.
 */

public class SignUpPresenter<V extends SignUpMvpView> extends BasePresenter<V> implements SignUpMvpPresenter<V> {
    public SignUpPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void startSignUp(String email) {
        getDataManager().saveEmail(email);
        getDataManager().setLoggedIn();
        getMvpView().openLogInActivity();


    }
}
