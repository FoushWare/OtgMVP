package com.example.foush.otgmvp.data;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class ServiceGenerator {

   public static String BASE_URL = "http://www.mommmmom.esy.es/";
   private  static Converter.Factory BASE_Converter=GsonConverterFactory.create();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

                    //BASE_Converter
//                    .addConverterFactory(SimpleXmlConverterFactory.create());//BASE_Converter

/** *************** Change API Base Url at Runtime **************/
    public static void changeApiBaseUrl(String newApiBaseUrl) {
        BASE_URL = newApiBaseUrl;

        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL);
    }

    /** *************** Change Converter at Runtime **************/

    public static void changeConverter(Converter.Factory factory) {
        BASE_Converter = factory;

        builder = new Retrofit.Builder()
                .addConverterFactory(BASE_Converter)
                .baseUrl(BASE_URL);
    }


    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS);

    public static <S> S createService(
            Class<S> serviceClass) {
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }




//    public static abstract class MixedConverter implements Converter  {
//        private Converter mSerializer;
//        private Converter mDeserializer;
//
//        public MixedConverter(Converter serializer, Converter deserializer) {
//            mSerializer = serializer;
//            mDeserializer = deserializer;
//        }
//
//
//        @Override
//        public Object fromBody(TypedInput body, Type type) throws ConversionException {
//            return mDeserializer.fromBody(body, type);
//        }
//
//        @Override
//        public TypedOutput toBody(Object object) {
//            return mSerializer.toBody(object);
//        }
//    }



}
