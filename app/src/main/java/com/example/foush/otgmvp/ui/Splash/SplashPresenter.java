package com.example.foush.otgmvp.ui.Splash;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;
import com.example.foush.otgmvp.ui.Splash.SplashMvpPresenter;
import com.example.foush.otgmvp.ui.Splash.SplashMvpView;

/**
 * Created by foush on 09/02/18.
 */

public class SplashPresenter <V extends SplashMvpView>extends BasePresenter<V> implements SplashMvpPresenter<V> {
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void DecideNextActivity() {
        //check if the user is logged in OR Not

        //if logged in {open MainActivity}
        if(getDataManager().getLoggedInMode()){
            getMvpView().openMainActivity();

        }else {//if not logged in {open LoggInActivity}
            getMvpView().openLoginActivity();

        }







    }
}
