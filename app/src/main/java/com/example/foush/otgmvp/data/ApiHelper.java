package com.example.foush.otgmvp.data;
import com.example.foush.otgmvp.models.Responses.MainResponse;
import com.example.foush.otgmvp.models.Responses.SignInResponse;
import com.example.foush.otgmvp.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by foush on 09/02/18.
 */

public interface ApiHelper {

    @POST("api/v1/users/signup")
    Call<MainResponse>SignUpUser(@Body User user);
    @POST("api/v1/users/signin")
    Call<SignInResponse>SignInUser(@Body User user);




}
