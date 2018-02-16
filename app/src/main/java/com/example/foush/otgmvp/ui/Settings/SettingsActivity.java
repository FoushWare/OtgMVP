package com.example.foush.otgmvp.ui.Settings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.models.Responses.ProfileResponse;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.otgMvp;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Profile.ProfileActivity;
import com.example.foush.otgmvp.ui.Splash.SplashActivity;
import com.example.foush.otgmvp.utils.CommonUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foush on 09/02/18.
 */

/**
 * TO DO:
 * i will use the same Methodology for fetch the user info as i do in ProfileActivity
 * i know this is bad as, i repeat myself ....but i will search how to solve this issue
 * and the
 * <p>
 * Response classes issue
 */


public class SettingsActivity extends BaseActivity implements SettingsMvpView {
    DataManager mDataManager;
    SettingsPresenter mSettingsPresenter;
    Menu menu;
    ProgressDialog dialog;
    User OldUserInfo;
    User userChange;

    @BindView(R.id.usertxt)
    MaterialEditText usertxt;
    @BindView(R.id.mailtxt)
    MaterialEditText mailtxt;
    @BindView(R.id.phonetxt)
    MaterialEditText phonetxt;
    @BindView(R.id.passwordtxt)
    TextInputEditText passwordtxt;
    @BindView(R.id.passwordConfirmtxt)
    TextInputEditText passwordConfirmtxt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        mDataManager = ((otgMvp) getApplication()).getmDataManger();
        mSettingsPresenter = new SettingsPresenter(mDataManager);
        mSettingsPresenter.onAttach(this);

        initView();
        mSettingsPresenter.updateUi();


        //check the change when done button  in the barTool clicked   :) below in the code


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        return showOverflowMenu(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.action_save:
                //save the changes

                userChange = new User();

                userChange.name = usertxt.getText().toString();
                userChange.email = mailtxt.getText().toString();
                userChange.phone = phonetxt.getText().toString();
                userChange.password = passwordtxt.getText().toString();
                userChange.password_confirm = passwordConfirmtxt.getText().toString();
                if (CommonUtils.isPasswordlValid(userChange.password) && userChange.password_confirm.equals(userChange.password)) {
                    mSettingsPresenter.checkChange(OldUserInfo, userChange);
                } else if (!userChange.password_confirm.equals(userChange.password)) {
                    passwordtxt.setError("password not exact to confirm password");
                    passwordConfirmtxt.setError("Confirm password not exact");

                } else {
                    Toast.makeText(this, "fuck you", Toast.LENGTH_SHORT).show();
                }


        }


        return super.onOptionsItemSelected(item);
    }

    //to show or hide some items in the menu.xml
    public boolean showOverflowMenu(boolean showMenu) {
        if (menu == null)
            return false;
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        menu.setGroupVisible(R.id.main_menu_group, showMenu);
        menu.setGroupVisible(R.id.settingActivity, !showMenu);
        return true;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);


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
                    mSettingsPresenter.fillUiFields(user);

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(SettingsActivity.this, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(SettingsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(SettingsActivity.this, "unknown problem", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void setUiValues(User user) {
        usertxt.setText(user.name);
        phonetxt.setText(user.phone);
        mailtxt.setText(user.email);
        passwordtxt.setText(mDataManager.getPassword());
        passwordConfirmtxt.setText(mDataManager.getPassword());
        fieldsOldValues();

    }

    @Override
    public void progress() {
        dialog = new ProgressDialog(SettingsActivity.this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void fieldsOldValues() {
        OldUserInfo = new User();
        OldUserInfo.name = usertxt.getText().toString();
        OldUserInfo.email = mailtxt.getText().toString();
        OldUserInfo.phone = phonetxt.getText().toString();
        OldUserInfo.password = passwordtxt.getText().toString();


    }

    @Override
    public void updateUserBasic(User user, final Boolean twoRequest, final User UserChange) {
        ApiHelper apiHelper = ServiceGenerator.createService(ApiHelper.class);
        Call<ProfileResponse> call = apiHelper.UpdateBasicInfo(user, mDataManager.getToken());

        progress();

        call.enqueue(new Callback<ProfileResponse>() {

            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    if (twoRequest == true) {
                        updateUserPassword(UserChange);
                    } else {
                        openProfileActivity();
                    }


                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(SettingsActivity.this, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(SettingsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(SettingsActivity.this, "unknown problem", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void updateUserPassword(User user) {
        ApiHelper apiHelper = ServiceGenerator.createService(ApiHelper.class);
        Call<ProfileResponse> call = apiHelper.UpdatePassword(user, mDataManager.getToken());

        progress();

        call.enqueue(new Callback<ProfileResponse>() {

            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    mDataManager.LogoutSession();
                    openSplashActivity();

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(SettingsActivity.this, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(SettingsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(SettingsActivity.this, "unknown problem", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void openProfileActivity() {
        Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void openSplashActivity() {
        Intent intent = new Intent(SettingsActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();
    }
}
