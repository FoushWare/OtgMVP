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
    public ArrayList<HistoryArray> history;


    public static class HistoryArray {
        public HistoryArray(String id, String date, ArrayList<SingleItem> items) {
            this.id = id;
            this.date = date;
            this.items = items;
        }

        @SerializedName("id")
        public String id;
        @SerializedName("date")
        public String date;
        @SerializedName("items")
        public ArrayList<SingleItem> items;
    }


    public static class SingleItem implements java.io.Serializable {
        public SingleItem(String name, String price, String quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        @SerializedName("name")
        public String name;
        @SerializedName("price")
        public String price;
        @SerializedName("quantity")
        public String quantity;


    }


}








   /*
    @SerializedName("msg")
    public String msg;
    @SerializedName("error")
    public String error;
    @SerializedName("history")
    public ArrayList<HistoryArray> history;


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
    */