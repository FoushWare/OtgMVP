package com.example.foush.otgmvp.ui.Base;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.MvpPresenter;
import com.example.foush.otgmvp.ui.Base.MvpView;

/**
 * Created by foush on 09/02/18.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    DataManager mDataManager;
    private V mMvpView;

    public BasePresenter(DataManager dataManager){
        mDataManager=dataManager;
    }
    @Override
    public void onAttach(V MvpView) {
        mMvpView=MvpView;

    }

    public V getMvpView(){
        return mMvpView;
    }
    public DataManager getDataManager(){
        return mDataManager;
    }

}
