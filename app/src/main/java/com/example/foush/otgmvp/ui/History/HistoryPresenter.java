package com.example.foush.otgmvp.ui.History;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;

/**
 * Created by foush on 09/02/18.
 */

public class HistoryPresenter<V extends HistoryMvpView> extends BasePresenter<V> implements HistoryMvpPresenter<V> {
    public HistoryPresenter(DataManager dataManager) {
        super(dataManager);
    }



}
