package com.example.foush.otgmvp.ui.History;

import com.example.foush.otgmvp.models.Item;
import com.example.foush.otgmvp.ui.Base.MvpView;

import java.util.ArrayList;

/**
 * Created by foush on 09/02/18.
 */

public interface HistoryMvpView extends MvpView {
    void initView();
    void RequestUiInfo();
    void UpdateRecyclerView(ArrayList<String> shoppingDays, ArrayList< Item.SingleItem>items,ArrayList<Integer> itemNumberPerDay);


}
