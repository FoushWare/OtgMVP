package com.example.foush.otgmvp.ui.Dataset;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;
import com.example.foush.otgmvp.ui.Dataset.DataSetMvpView;

/**
 * Created by foush on 09/02/18.
 */

public class DataSetPresenter<V extends DataSetMvpView> extends BasePresenter<V> implements DataSetMvpPresenter<V> {
    public DataSetPresenter(DataManager dataManager) {
        super(dataManager);
    }



}
