package com.example.foush.otgmvp.ui.Dataset;

import android.graphics.Bitmap;

import com.example.foush.otgmvp.ui.Dataset.DataSetMvpView;

/**
 * Created by foush on 09/02/18.
 */

public interface DataSetMvpPresenter<V extends DataSetMvpView> {
    void savingAndZipping(String mTempPhotoPath, Bitmap croppedBitmap,int flag,int count);
}
