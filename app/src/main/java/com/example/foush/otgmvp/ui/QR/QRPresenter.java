package com.example.foush.otgmvp.ui.QR;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import org.apache.http.NameValuePair;


import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.data.ServiceHandler;
import com.example.foush.otgmvp.models.Responses.MainResponse;
import com.example.foush.otgmvp.ui.Base.BasePresenter;
import com.example.foush.otgmvp.ui.Main.MainActivity;
import com.example.foush.otgmvp.ui.Signup.SignUpActivity;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by foush on 09/02/18.
 */

public class QRPresenter<V extends QRMvpView> extends BasePresenter<V> implements QRMvpPresenter<V> {
    private static Context context;
    ProgressDialog dialog;
    public static  File QRIMAGE=null;


    public QRPresenter(DataManager dataManager,Context c) {
        super(dataManager);
        context=c;
    }


    @Override
    public void StoreFirebaseToken(String firebasetoken) {
        getDataManager().saveFireBaseToken(firebasetoken);
       // RequestOrderRegistration(firebasetoken);
    }

    @Override
    public File RequestOrderRegistration(String firebasetoken) {

        new GetQR().execute(getDataManager().getFireBaseToken());
        Log.d(TAG, "onRequest: "+QRIMAGE);
        return QRIMAGE;


    }

    @Override
    public void LoadQR_TO_Activity() {

    }


    /**Helper Method**/
    public void progress(){

        dialog = new ProgressDialog(context); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private class GetQR extends AsyncTask<String, Void, Void> {

        String json="", error="", message="";

        @Override
        protected void onPreExecute() {
         progress();
        }

        @Override
        protected Void doInBackground(String... arg) {
            // TODO Auto-generated method stub
            String firebaseToken = arg[0];


            // Preparing post params
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("firebase_token", getDataManager().getFireBaseToken()));

            ServiceHandler serviceClient = new ServiceHandler();

            json = serviceClient.makeServiceCall("http://www.mommmmom.esy.es/api/v1/orders/registration?token="+getDataManager().getToken(),
                    ServiceHandler.POST, params);



            Log.d("CreatePredictionRequest", "> " + json);


            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);


            QRIMAGE=saveQROnSD(json);

            getDataManager().saveQR_Path(""+QRIMAGE);
            Log.d(TAG, "onPostExecute: "+QRIMAGE);
            dialog.dismiss();



        }
    }
    public File saveQROnSD( String code){
        try
        {
            File root = new File(Environment.getExternalStorageDirectory(), "otgMvp");
            if (!root.exists()) {
                root.mkdirs();
            }

            File gpxfile = new File(root, "QRcode.html");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(code);
            writer.flush();
            writer.close();
            //QRImage.loadUrl(Uri.fromFile(gpxfile).toString());
            Log.d(TAG,"gpxfile"+gpxfile);
            return gpxfile;


        }
        catch(IOException e)
        {
            return null;
        }


    }



}


//    //request the api with the firebase token ..and get Response as xml
//
//    progress();
//
//    //Retrofit part to send request
//    ApiHelper api= ServiceGenerator.createService(ApiHelper.class);
//
//    Call<String> call=api.getQR(getDataManager().getToken(),getDataManager().getFireBaseToken());
//
//        call.enqueue(new Callback<String>() {
//@Override
//public void onResponse(Call<String> call, Response<String> response) {
//        dialog.dismiss();
//
//        if(response.isSuccessful()) {
////                    ServiceGenerator.changeConverter(SimpleXmlConverterFactory.create());
//
//        Toast.makeText(context,"QR successfully"+response.body().toString(), Toast.LENGTH_LONG).show();
//        //save the user's session
//        //mSignUpPresenter.startSignUp(user.email);
//        Toast.makeText(context, ""+response.body(), Toast.LENGTH_SHORT).show();
//
//
//        }else {
//        try {
//        JSONObject jObjError = new JSONObject(response.errorBody().string());
//        Toast.makeText(context, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
//        } catch (Exception e) {
//        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//        }
//
//
//        }
//
//@Override
//public void onFailure(Call<String> call, Throwable t) {
//        dialog.dismiss();
//        Toast.makeText(context, "The returned response is xml", Toast.LENGTH_SHORT).show();
//
//
//        }
//        });
//








