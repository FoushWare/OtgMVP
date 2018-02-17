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

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    @BindView(R.id.tvNumber)
    TextView shoppingDay;
    private ArrayList<String> date;
    private ArrayList<Item.SingleItem> items;
    private ArrayList<Integer> itemNumberPerDay;

    public HistoryAdapter(ArrayList<String> date, ArrayList<Item.SingleItem> items,ArrayList<Integer> itemNumberPerDay) {
        this.date = date;
        this.items=items;
        this.itemNumberPerDay=itemNumberPerDay;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_card_row, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Bind the item {date}

    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

    }
}
