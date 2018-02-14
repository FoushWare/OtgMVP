package com.example.foush.otgmvp.ui.Dataset;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.foush.otgmvp.ui.Base.MvpView;

/**
 * Created by foush on 09/02/18.
 */

public interface DataSetMvpView extends MvpView {
    void initView();
    void launchCamera(int flag);
    void prepareFaceDetector();
    void processImage(int flag);
    void buttonsGone(int flag);
    void noFace(int flag);
    void saveImageAndPath(String mTempPhotoPath, Bitmap croppedBitmap);

    void userZipPhotos();
    void uploadZipFile(String zipFile);



}
