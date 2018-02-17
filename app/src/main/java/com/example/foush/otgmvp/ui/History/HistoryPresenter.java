package com.example.foush.otgmvp.ui.History;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.models.Item;
import com.example.foush.otgmvp.ui.Base.BasePresenter;

import java.util.ArrayList;

/**
 * Created by foush on 09/02/18.
 */

public class HistoryPresenter<V extends HistoryMvpView> extends BasePresenter<V> implements HistoryMvpPresenter<V> {
    public HistoryPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void updateUi() {
        getMvpView().RequestUiInfo();
    }

    @Override
    public void UpdateRecyclerControler(ArrayList<String> shoppingDays,ArrayList< Item.SingleItem> items,ArrayList<Integer> itemNumberPerDay) {
        getMvpView().UpdateRecyclerView(shoppingDays,items,itemNumberPerDay);
    }


}
