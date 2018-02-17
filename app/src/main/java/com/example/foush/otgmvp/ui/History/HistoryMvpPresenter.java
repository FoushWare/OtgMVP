package com.example.foush.otgmvp.ui.History;

import com.example.foush.otgmvp.models.Item;
import com.example.foush.otgmvp.ui.History.HistoryMvpView;

import java.util.ArrayList;

/**
 * Created by foush on 09/02/18.
 */

public interface HistoryMvpPresenter<V extends HistoryMvpView> {
    void updateUi();
    void UpdateRecyclerControler(ArrayList<String> shoppingDays, ArrayList<Item.SingleItem>items,ArrayList<Integer> itemNumberPerDay);
}
