package com.example.foush.otgmvp.ui.QR;

import java.io.File;

/**
 * Created by foush on 09/02/18.
 */

public interface QRMvpPresenter<V extends QRMvpView> {
    void StoreFirebaseToken(String firebasetoken);
    File RequestOrderRegistration(String firebasetoken);
    void LoadQR_TO_Activity();
}
