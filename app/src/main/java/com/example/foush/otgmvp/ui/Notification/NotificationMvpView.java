package com.example.foush.otgmvp.ui.Notification;

import com.example.foush.otgmvp.models.Item;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.models.orderItem;
import com.example.foush.otgmvp.ui.Base.MvpView;

import java.util.ArrayList;

/**
 * Created by foush on 09/02/18.
 */

public interface NotificationMvpView extends MvpView {


    void initView();
    void RequestUiInfo();
    void UpdateRecyclerView(ArrayList<orderItem.itemsArray> Items);
}
