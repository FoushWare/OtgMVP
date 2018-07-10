package com.example.foush.otgmvp.ui.DetailHistory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.foush.otgmvp.*;
import com.example.foush.otgmvp.Adapters.DetailHistoryAdapter;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.models.Item;
import com.example.foush.otgmvp.ui.Base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by foush on 09/02/18.
 */

public class DetailHistoryActivity extends BaseActivity implements DetailHistoryMvpView {
    DataManager mDataManager;
    DetailHistoryPresenter mDetailHistoryPresenter;
    ArrayList<Item.SingleItem> items;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_history_main);

        mDataManager = ((otgStore) getApplication()).getmDataManger();
        mDetailHistoryPresenter = new DetailHistoryPresenter(mDataManager);
        mDetailHistoryPresenter.onAttach(this);

        initView();


    }


    @Override
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        Bundle bundleObject = getIntent().getExtras();
        items=(ArrayList<Item.SingleItem>)bundleObject.getSerializable("Detail_day_items");

        RecyclerView.Adapter adapter = new DetailHistoryAdapter(items);
        recyclerView.setAdapter(adapter);




    }
}