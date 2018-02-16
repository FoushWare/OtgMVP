package com.example.foush.otgmvp.ui.Profile;

import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.ui.Base.MvpView;

/**
 * Created by foush on 09/02/18.
 */

public interface ProfileMvpView extends MvpView {
    void initView();
    void ButtonEvents();
    void RequestUiInfo();
    void setUiValues(User user,String picStatus);
    void progress();




}
