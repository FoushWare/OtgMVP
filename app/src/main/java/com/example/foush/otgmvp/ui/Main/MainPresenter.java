package com.example.foush.otgmvp.ui.Main;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;
import com.example.foush.otgmvp.ui.Main.MainMvpPresenter;
import com.example.foush.otgmvp.ui.Main.MainMvpView;

/**
 * Created by foush on 09/02/18.
 */

public class MainPresenter <V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter {
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
