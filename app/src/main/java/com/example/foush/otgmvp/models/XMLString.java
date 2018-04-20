package com.example.foush.otgmvp.models;

import org.simpleframework.xml.Root;

/**
 * Created by foush on 20/04/18.
 */

@Root(name="string", strict = false)
public class XMLString {


    private XMLString reponse;


//    @SerializedName("error")
//    public String error;
//    @SerializedName("msg")
//    public String msg;



//    public String getError(){
//        return error;
//    }
//    public String getMsg(){
//        return msg;
//    }

    public XMLString getReponse() {
        return reponse;
    }

    public void setReponse(XMLString reponse) {
        this.reponse = reponse;
    }
}