package com.example.foush.otgmvp.ui.Notification;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.models.Item;
import com.example.foush.otgmvp.models.orderItem;
import com.example.foush.otgmvp.ui.Base.BasePresenter;

import java.util.ArrayList;

/**
 * Created by foush on 09/02/18.
 */

public class NotificationPresenter<V extends NotificationMvpView> extends BasePresenter<V> implements NotificationMvpPresenter<V> {
    public NotificationPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void updateUi() {
        getMvpView().RequestUiInfo();
    }
    @Override
    public void UpdateRecyclerControler(ArrayList<orderItem.itemsArray> Items) {
        getMvpView().UpdateRecyclerView(Items);
    }

}
