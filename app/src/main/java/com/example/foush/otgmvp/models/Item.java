package com.example.foush.otgmvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by foush on 16/02/18.
 */

public class Item {
    @SerializedName("msg")
    public String msg;
    @SerializedName("error")
    public String error;
    @SerializedName("history")
    public HistoryArray history;


    public class HistoryArray{
    @SerializedName("id")
    public String id;
    @SerializedName("date")
    public String date;
    @SerializedName("items")
    public SingleItem items;
    }


    public class SingleItem {
        @SerializedName("name")
        public String name;
        @SerializedName("price")
        public String price;
        @SerializedName("quantity")
        public String quantity;

    }
}
