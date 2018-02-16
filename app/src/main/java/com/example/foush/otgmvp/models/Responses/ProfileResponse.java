package com.example.foush.otgmvp.models.Responses;

/**
 * Created by foush on 15/02/18.
 */


import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

/**Note
 *
 * i will merge all the reponses in one class response .... i don't know it's good practice or not
 * i will search about it at the end of the day
 *
 * */

public class ProfileResponse {

    @SerializedName("msg")
    public String msg;
    @SerializedName("error")
    public String error;
    @SerializedName("data")
    public DataResponse data;


    public class DataResponse {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("email")
        public String email;
        @SerializedName("status")
        public String status;
        @SerializedName("group_id")
        public int group_id;
        @SerializedName("balance")
        public String balance;
        @SerializedName("pics_uploaded")
        public String pics_uploaded;
        @SerializedName("phone")
        public String phone;
        @SerializedName("img")
        public String imgUrl;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("updated_at")
        public String updated_at;
    }
}
