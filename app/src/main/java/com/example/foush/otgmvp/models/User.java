package com.example.foush.otgmvp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by foush on 11/02/18.
 */

public class User {
    //"username":"ahmed","password":"123456","email":"ahmedfouad@yahoo.com}
    //serializeName is the key in the json

    @SerializedName("name")
    public String name;
    @SerializedName("password")
    public String password;
    @SerializedName("email")
    public String email;
    @SerializedName("phone")
    public String phone;

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }




}
