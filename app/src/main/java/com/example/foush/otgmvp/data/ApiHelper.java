package com.example.foush.otgmvp.data;
import com.example.foush.otgmvp.models.Item;
import com.example.foush.otgmvp.models.Responses.BalanceResponse;
import com.example.foush.otgmvp.models.Responses.MainResponse;
import com.example.foush.otgmvp.models.Responses.ProfileResponse;
import com.example.foush.otgmvp.models.Responses.SignInResponse;
import com.example.foush.otgmvp.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by foush on 09/02/18.
 */

public interface ApiHelper {

    /**********************************  SigIN/SignUp Activity Part    **************** */

    @POST("api/v1/users/signup")
    Call<MainResponse>SignUpUser(@Body User user);
    @POST("api/v1/users/signin")
    Call<SignInResponse>SignInUser(@Body User user);

    /**********************************  Balance Activity Part    **************** */

    @POST("api/v1/users/credits")
    Call<BalanceResponse>CreditRequest(
            @Query("token") String TOKEN,
            @Body String hashcardNumber
    );
    /**********************************  Profile Activity Part    **************** */

    @GET("api/v1/users/profile")
    Call<ProfileResponse>GetRequestProfile(
            @Query("token") String TOKEN
    );
    /**********************************  Settings Activity Part    **************** */

    @POST("api/v1/users/update")
    Call<ProfileResponse>UpdateBasicInfo(@Body User user,
                                         @Query("token") String TOKEN);

    @POST("api/v1/users/password")
    Call<ProfileResponse>UpdatePassword(@Body User user,
                                         @Query("token") String TOKEN);


    /**********************************  Logout  Part    **************** */

    @GET("api/v1/users/logout")
    Call<MainResponse>Logout(@Query("token")String TOKEN);

    /**********************************  History Part    **************** */

    @GET("api/v1/users/history")
    Call<Item>UserHistory(
            @Query("token") String TOKEN
    );



}
