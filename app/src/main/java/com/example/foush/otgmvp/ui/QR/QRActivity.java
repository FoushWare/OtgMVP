package com.example.foush.otgmvp.ui.QR;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.otgStore;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.File;

import static android.content.ContentValues.TAG;

/**
 * Created by foush on 09/02/18.
 */

public class QRActivity extends BaseActivity implements QRMvpView {
    WebView QRImage;
    DataManager mDataManager;
    QRPresenter mQRPresenter;
    ProgressDialog dialog;
    int count=0;
    File QR;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_qrcode);

        mDataManager = ((otgStore) getApplication()).getmDataManger();
        mQRPresenter = new QRPresenter(mDataManager,QRActivity.this);
        mQRPresenter.onAttach(this);

        initView();
        Log.e("Token is", FirebaseInstanceId.getInstance().getToken());

        if (count==0) {
            mQRPresenter.StoreFirebaseToken(FirebaseInstanceId.getInstance().getToken());
        }
        QRImage = (WebView) findViewById(R.id.QRImage);
        Log.d(TAG,"firebase token  "+mDataManager.getFireBaseToken());
        mQRPresenter.RequestOrderRegistration(mDataManager.getFireBaseToken());
        QR= new File(Environment.getExternalStorageDirectory() + "/otgStore/QRcode.html");
        Log.d(TAG, "QR Activity: "+Environment.getExternalStorageDirectory() + "/otgStore/QRcode.html");


        QRImage.loadUrl("file:///"+QR.getAbsolutePath());
        Log.d(TAG, "App Token: "+mDataManager.getToken());


    }
    @Override
    public void initView() {
//        QRImage = (WebView) findViewById(R.id.QRImage);

    }

    @Override
    public void getFirebaseToken() {

    }

    @Override
    public void LoadQR(String QR) {

    }



}