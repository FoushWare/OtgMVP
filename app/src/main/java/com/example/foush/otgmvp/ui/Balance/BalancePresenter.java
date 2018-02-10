package com.example.foush.otgmvp.ui.Balance;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;

/**
 * Created by foush on 09/02/18.
 */

public class BalancePresenter<V extends BalanceMvpView> extends BasePresenter<V> implements BalanceMvpPresenter<V> {
    public BalancePresenter(DataManager dataManager) {
        super(dataManager);
    }



}
