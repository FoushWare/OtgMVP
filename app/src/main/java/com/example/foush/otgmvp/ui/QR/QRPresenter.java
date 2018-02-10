package com.example.foush.otgmvp.ui.QR;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;

/**
 * Created by foush on 09/02/18.
 */

public class QRPresenter<V extends QRMvpView> extends BasePresenter<V> implements QRMvpPresenter<V> {
    public QRPresenter(DataManager dataManager) {
        super(dataManager);
    }



}
