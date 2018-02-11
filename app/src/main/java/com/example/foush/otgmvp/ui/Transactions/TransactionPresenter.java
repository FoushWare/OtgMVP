package com.example.foush.otgmvp.ui.Transactions;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;
import com.example.foush.otgmvp.ui.Transactions.TransactionMvpView;

/**
 * Created by foush on 09/02/18.
 */

public class TransactionPresenter<V extends TransactionMvpView> extends BasePresenter<V> implements TransactionMvpPresenter<V> {
    public TransactionPresenter(DataManager dataManager) {
        super(dataManager);
    }



}
