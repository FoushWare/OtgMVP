package com.example.foush.otgmvp.ui.Balance;

import com.example.foush.otgmvp.ui.Balance.BalanceMvpView;

/**
 * Created by foush on 09/02/18.
 */

public interface BalanceMvpPresenter<V extends BalanceMvpView> {
    void  slideAnimation();
    void  requestCredit(String cardNumber);
    void updateUI(String currentCredit);
}
