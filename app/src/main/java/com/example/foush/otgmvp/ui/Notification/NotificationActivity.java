package com.example.foush.otgmvp.ui.Notification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.otgMvp;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by foush on 09/02/18.
 */

public class NotificationActivity  extends BaseActivity  implements NotificationMvpView {
    DataManager mDataManager;
    NotificationPresenter mNotificationPresenter;

    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDataManager = ((otgMvp) getApplication()).getmDataManger();
        mNotificationPresenter = new NotificationPresenter(mDataManager);
        mNotificationPresenter.onAttach(this);

        initView();








    }

    @Override
    public void initView() {
      /** The View will be  RecyclerView and CardView to show products that the use toke **/


    }
    public void progress(){

        dialog = new ProgressDialog(NotificationActivity.this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }






}

