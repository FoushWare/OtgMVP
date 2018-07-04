package com.example.foush.otgmvp.ui.Notification;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;

/**
 * Created by foush on 09/02/18.
 */

public class NotificationPresenter<V extends NotificationMvpView> extends BasePresenter<V> implements NotificationMvpPresenter<V> {
    public NotificationPresenter(DataManager dataManager) {
        super(dataManager);
    }



}
