package com.example.foush.otgmvp.models.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by foush on 11/02/18.
 */

public class SignInResponse {
    /**
     *    "token": "__TOKEN__ IF SUCCESS",
     *     "msg": "__MESSAGE_TEXT__",
     *     "error": "___ERROR_VALUE__"
     *
     *             error:0  => No Error
     *             error:1  =>There is an Error
     *
     * */

    @SerializedName("token")
    public String token;
    @SerializedName("msg")
    public String msg;
    @SerializedName("error")
    public String error;
}
