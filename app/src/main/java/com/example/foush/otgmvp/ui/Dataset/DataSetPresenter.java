package com.example.foush.otgmvp.ui.Dataset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.ui.Base.BasePresenter;
import com.example.foush.otgmvp.ui.Dataset.DataSetMvpView;
import com.example.foush.otgmvp.ui.Main.MainActivity;
import com.example.foush.otgmvp.utils.BitmapUtils;

import static android.content.ContentValues.TAG;

/**
 * Created by foush on 09/02/18.
 */

public class DataSetPresenter<V extends DataSetMvpView> extends BasePresenter<V> implements DataSetMvpPresenter<V> {
    public DataSetPresenter(DataManager dataManager) {
        super(dataManager);
    }




    @Override
    public void savingAndZipping(String mTempPhotoPath, Bitmap croppedBitmap, int flag,int count) {
        Log.d(TAG, "savingAndZipping: count is ->"+count);

        //save the cropped image and delete the temp image
        getMvpView().saveImageAndPath(mTempPhotoPath,croppedBitmap);

        //the life is successful for this button make it's visibility gone
        getMvpView().buttonsGone(flag);
        if (count == 2) {
           getMvpView().userZipPhotos();
          // getMvpView().uploadZipFile(BitmapUtils.storageDir + ".zip");

        }

    }
}
