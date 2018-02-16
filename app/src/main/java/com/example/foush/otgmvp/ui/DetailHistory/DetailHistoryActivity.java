package com.example.foush.otgmvp.ui.DetailHistory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.foush.otgmvp.*;
import com.example.foush.otgmvp.Adapters.DetailHistoryAdapter;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.models.Item;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Login.LoginActivity;
import com.example.foush.otgmvp.ui.Main.MainActivity;
import com.example.foush.otgmvp.ui.Splash.SplashPresenter;

import java.util.ArrayList;

/**
 * Created by foush on 09/02/18.
 */

public class DetailHistoryActivity extends BaseActivity implements DetailHistoryMvpView {
    DataManager mDataManager;
    DetailHistoryPresenter mDetailHistoryPresenter;
    private ArrayList items;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_main);

        mDataManager = ((otgMvp) getApplication()).getmDataManger();
        mDetailHistoryPresenter = new DetailHistoryPresenter(mDataManager);
        mDetailHistoryPresenter.onAttach(this);

        initView();


    }


    @Override
    public void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        Item item=new Item();
        item.history.items.name="coffe";
        item.history.items.price="20 L.E";
        item.history.items.quantity="3 Item";


        items = new ArrayList<Item>();
        items.add(item);

        RecyclerView.Adapter adapter = new DetailHistoryAdapter(items);
        recyclerView.setAdapter(adapter);


    }
}
