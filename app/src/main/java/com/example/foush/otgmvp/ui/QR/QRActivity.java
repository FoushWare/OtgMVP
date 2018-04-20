package com.example.foush.otgmvp.ui.QR;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.otgMvp;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Main.*;
import com.example.foush.otgmvp.ui.Signup.SignUpPresenter;
import com.example.foush.otgmvp.utils.CommonUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.rengwuxian.materialedittext.MaterialEditText;

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

        mDataManager = ((otgMvp) getApplication()).getmDataManger();
        mQRPresenter = new QRPresenter(mDataManager,QRActivity.this);
        mQRPresenter.onAttach(this);

        initView();
        Log.e("Token is", FirebaseInstanceId.getInstance().getToken());

        if (count==0) {
            mQRPresenter.StoreFirebaseToken(FirebaseInstanceId.getInstance().getToken());
        }

         mQRPresenter.RequestOrderRegistration(mDataManager.getFireBaseToken());
        QR= new File(mDataManager.getQR_Path());
        Log.d(TAG, "QR Activity: "+mDataManager.getQR_Path());


       QRImage.loadUrl(Uri.fromFile(QR).toString());


    }
    @Override
    public void initView() {
        QRImage = (WebView) findViewById(R.id.QRImage);

    }

    @Override
    public void getFirebaseToken() {

    }

    @Override
    public void LoadQR(String QR) {

    }



}
