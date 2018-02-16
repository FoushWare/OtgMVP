package com.example.foush.otgmvp.ui.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.models.Responses.ProfileResponse;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.otgMvp;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Settings.SettingsActivity;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foush on 09/02/18.
 */

public class ProfileActivity extends BaseActivity implements ProfileMvpView {
    DataManager mDataManager;
    ProfilePresenter mProfilePresenter;
    Menu menu;

    ProgressDialog dialog;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.phonenumber)
    TextView phonenumber;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.picState)
    TextView picState;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        mDataManager = ((otgMvp) getApplication()).getmDataManger();
        mProfilePresenter = new ProfilePresenter(mDataManager);
        mProfilePresenter.onAttach(this);

        initView();
        ButtonEvents();
        mProfilePresenter.updateUi();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        //not show the done button
       return showOverflowMenu(false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;


        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @Override
    public void ButtonEvents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcome to OTG store :) ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public void RequestUiInfo() {
        //Request the user info from  [--------- user/profile?token=TOKEN ----- ]  route
        //After getting the Response send it to mProfileMvpPresenter.fillUiFields() to
        //inform the view to be updated by SetUiValues Method
        ApiHelper apiHelper = ServiceGenerator.createService(ApiHelper.class);
        Call<ProfileResponse> call = apiHelper.GetRequestProfile(mDataManager.getToken());

        progress();

        call.enqueue(new Callback<ProfileResponse>() {

            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    User user = new User();
                    user.name = response.body().data.name;
                    user.phone = response.body().data.phone;
                    user.email = response.body().data.email;
                    user.balance = response.body().data.balance;
                    String picStatus=response.body().data.pics_uploaded;

                    mProfilePresenter.fillUiFields(user,picStatus);

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ProfileActivity.this, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ProfileActivity.this, "unknown problem", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void setUiValues(User user,String picStatus) {

        username.setText(user.name);
        email.setText(user.email);
        phonenumber.setText(user.phone);
        balance.setText(user.balance);

        if (picStatus.equals("1")){
            picState.setText("let's Shopping");
        }else {
            picState.setText("upload your Pics");
        }





    }

    @Override
    public void progress() {
        dialog = new ProgressDialog(ProfileActivity.this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    public boolean showOverflowMenu(boolean showMenu){
        if(menu == null)
            return false;
        getMenuInflater().inflate(R.menu.menu_profile,menu);
        menu.setGroupVisible(R.id.main_menu_group, showMenu);
        return true;
    }
}
