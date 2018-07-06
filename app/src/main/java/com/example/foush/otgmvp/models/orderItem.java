package com.example.foush.otgmvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by foush on 16/02/18.
 */

public class orderItem {
    @SerializedName("msg")
    public String msg;
    @SerializedName("error")
    public String error;
    @SerializedName("items")
    public ArrayList<itemsArray> items;


    public static class itemsArray {
        public itemsArray(String title,Float price,int quantity) {
            this.title = title;
            this.price = price;
            this.quantity = quantity;
        }

        @SerializedName("title")
        public String title;
        @SerializedName("price")
        public Float price;
        @SerializedName("quantity")
        public int quantity;
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