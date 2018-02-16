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

/**
 * Created by foush on 16/02/18.
 */

public class DetailHistoryAdapter extends RecyclerView.Adapter<DetailHistoryAdapter.ViewHolder> {

    @BindView(R.id.item_history_date)
    TextView itemHistoryDate;
    @BindView(R.id.item_history_name)
    TextView itemHistoryName;
    @BindView(R.id.item_history_quantity)
    TextView itemHistoryQuantity;
    @BindView(R.id.item_history_price)
    TextView itemHistoryPrice;
    private ArrayList<Item> items;

    public DetailHistoryAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_items_history_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       //Bind the item {name ,quantity,price}
        itemHistoryName.setText(items.get(position).history.items.name);
        itemHistoryPrice.setText(items.get(position).history.items.price);
        itemHistoryQuantity.setText(items.get(position).history.items.quantity);

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
