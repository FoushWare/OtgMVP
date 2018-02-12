package com.example.foush.otgmvp.ui.Dataset;

import android.content.Context;

import com.example.foush.otgmvp.ui.Base.MvpView;

/**
 * Created by foush on 09/02/18.
 */

public interface DataSetMvpView extends MvpView {

    void launchCamera(int flag);
    void prepareFaceDetector();
    void processImage(int flag);
    void buttonsGone(int flag);
    void noFace(int flag);

    void userZipPhotos();
    void uploadZipFile(String zipFile);



}
