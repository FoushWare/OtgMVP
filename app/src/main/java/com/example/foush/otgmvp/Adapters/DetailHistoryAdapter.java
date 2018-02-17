package com.example.foush.otgmvp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.models.Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by foush on 16/02/18.
 */

public class DetailHistoryAdapter extends RecyclerView.Adapter<DetailHistoryAdapter.ViewHolder> {


    @BindView(R.id.tvNumber)
    TextView itemName;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.quantity)
    TextView quantity;
    private ArrayList<Item.SingleItem> items;

    public DetailHistoryAdapter(ArrayList<Item.SingleItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_history_card_row, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Bind the item {name ,quantity,price}
        itemName.setText(items.get(position).name);
        price.setText(items.get(position).price);
        quantity.setText(items.get(position).quantity);



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }


    }
}
