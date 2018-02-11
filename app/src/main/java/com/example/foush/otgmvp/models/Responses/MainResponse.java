package com.example.foush.otgmvp.models.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by foush on 11/02/18.
 */

public class MainResponse {
    /**
     *   "msg":"__MESSAGE__",
     *   "  error":"__ERROR_VALUE__"
     *
     *   error:0  => No Error
     *   error:1  =>There is an Error
     *
     * */

    @SerializedName("error")
    public String error;
    @SerializedName("msg")
    public String msg;



    public String getError(){
        return error;
    }
    public String getMsg(){
        return msg;
    }

}
