package com.example.foush.otgmvp.ui.Settings;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.ui.Base.BasePresenter;

import static android.content.ContentValues.TAG;

/**
 * Created by foush on 09/02/18.
 */

public class SettingsPresenter<V extends SettingsMvpView>extends BasePresenter<V> implements SettingsMvpPresenter<V> {
    public SettingsPresenter(DataManager dataManager) {
        super(dataManager);
    }
    boolean twoRequests=false;



    @Override
    public void updateUi() {
        getMvpView().RequestUiInfo();
    }

    @Override
    public void fillUiFields(User user) {
        getMvpView().setUiValues( user);

    }

    @Override
    public void checkChange(User oldUser,User UserChange) {

        Boolean BasicRequest=!oldUser.email.equals(UserChange.email)||
                !oldUser.name.equals(UserChange.name)||
                !oldUser.phone.equals(UserChange.phone);

        Boolean PasswordRequest=!(oldUser.password.equals(UserChange.password));

        if( BasicRequest &&PasswordRequest ){
            // This will trigger   {post /api/v1/users/update?token=TOKEN} &&
            // This will trigger {post /api/v1/users/password?token=TOKEN}
            twoRequests=true;
            getMvpView().updateUserBasic(UserChange,true,UserChange);


        }else if (BasicRequest){
            // This will trigger   {post /api/v1/users/update?token=TOKEN}
            getMvpView().updateUserBasic(UserChange,false,UserChange);


        }else if (PasswordRequest){
            // This will trigger {post /api/v1/users/password?token=TOKEN}
            getMvpView().updateUserPassword(UserChange);


        }else {
        }



    }



    }


