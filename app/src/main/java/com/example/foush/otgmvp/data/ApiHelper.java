package com.example.foush.otgmvp.data;
import com.example.foush.otgmvp.models.Item;
import com.example.foush.otgmvp.models.Responses.BalanceResponse;
import com.example.foush.otgmvp.models.Responses.MainResponse;
import com.example.foush.otgmvp.models.Responses.ProfileResponse;
import com.example.foush.otgmvp.models.Responses.SignInResponse;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.models.XMLString;
import com.example.foush.otgmvp.models.orderItem;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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

    /**********************************  Upload zip file of photos to the server   **************** */


    //http://ahmedfouad.esy.es/computerVision/upload_zipfile.php
    @Multipart
    @POST("upload_zipfile.php")
    Call<ResponseBody>upload(
            @Part ("description")RequestBody description,
            @Part MultipartBody.Part file
            );


    /********************************** QR   **************** */
//    @FormUrlEncoded
//    @POST("api/v1/orders/registration")
//    @AnnotatedConverterFactory.Xml
//    Call<String>getQR(
//            @Query("token") String TOKEN,
//            @Field("firebase_token") String FirebaseToken
//    );


    /**********************************  OrderItems Part [transaction](Notification Activity)    **************** */

    @GET("/api/v1/orders/{order_id}")
    Call<orderItem>UserItems(
            @Path(value = "order_id", encoded = true) int order_id,
            @Query("token") String TOKEN
    );






}
