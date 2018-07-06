package com.example.foush.otgmvp.ui.Notification;

import com.example.foush.otgmvp.models.Item;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.models.orderItem;

import java.util.ArrayList;

/**
 * Created by foush on 09/02/18.
 */

public interface NotificationMvpPresenter<V extends NotificationMvpView> {

    void updateUi();
    void UpdateRecyclerControler(ArrayList<orderItem.itemsArray> Items);

}
