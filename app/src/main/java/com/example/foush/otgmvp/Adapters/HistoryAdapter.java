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

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

   private ArrayList<String>date;

    public HistoryAdapter(ArrayList<String> date) {
        this.date = date;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_items_history_row, parent, false);
        return new ViewHolder(view);*/
       return null;
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
