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


    @Override
    public void slideAnimation() {
        getMvpView().setUpWindowAnimations();
    }

    @Override
    public void requestCredit(String CardNumber) {
        if (!CardNumber.isEmpty()&& !(CardNumber ==null)) {
            getMvpView().onSubmit(CardNumber);
        }else {
            getMvpView().Error();
        }

    }

    @Override
    public void updateUI(String currentCredit) {
        getMvpView().updateUI(currentCredit);
    }
}
