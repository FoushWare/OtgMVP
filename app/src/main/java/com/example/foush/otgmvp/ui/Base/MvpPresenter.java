package com.example.foush.otgmvp.ui.Base;

import com.example.foush.otgmvp.ui.Base.MvpView;

/**
 * Created by foush on 09/02/18.
 */

public interface MvpPresenter <V extends MvpView> {
    void onAttach(V MvpView);
}
