package com.example.foush.otgmvp.ui.Signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.models.Responses.MainResponse;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.otgMvp;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Login.LoginActivity;
import com.example.foush.otgmvp.ui.Main.MainActivity;
import com.example.foush.otgmvp.utils.CommonUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foush on 09/02/18.
 */

public class SignUpActivity extends BaseActivity implements SignUpMvpView {
    DataManager mDataManager;
    SignUpPresenter mSignUpPresenter;
    EditText userName;
    MaterialEditText userEmail;
    MaterialEditText userPassword;
    MaterialEditText userPhone;
    Button signup;
    User userObject;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mDataManager = ((otgMvp) getApplication()).getmDataManger();
        mSignUpPresenter = new SignUpPresenter(mDataManager);
        mSignUpPresenter.onAttach(this);

        initView();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpClick();
            }
        });



    }


    @Override
    public void initView() {
        userName=findViewById(R.id.usertxt);
        userEmail=findViewById(R.id.mailtxt);
        userPassword=findViewById(R.id.passwordtxt);
        userPhone=findViewById(R.id.phonetxt);
         signup=findViewById(R.id.signup);

    }

    @Override
    public void onSignUpClick() {
        //check and go to presenter StartSign up Method
        String user, mail, phone, password;

        user= userName.getText().toString();
        mail= userEmail.getText().toString();
        phone= userPhone.getText().toString();
        password= userPassword.getText().toString();

        if (!CommonUtils.isEmailValid(mail)) {
            Toast.makeText(this, "Enter correct Email", Toast.LENGTH_LONG).show();
            userEmail.setError("Please Enter your email");
            return;
        }

        if (password == null || password.isEmpty()||!CommonUtils.isPasswordlValid(password)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_LONG).show();
            userPassword.setError("Please enter your password");
            return;
        }
        if(user==null||user.isEmpty()){
            Toast.makeText(this, "Enter userName", Toast.LENGTH_LONG).show();
            userName.setError("Please Enter your name");
            return;
        }
        if(phone==null||phone.isEmpty()){
            Toast.makeText(this, "Enter phone Number", Toast.LENGTH_LONG).show();
            userPhone.setError("Please Enter your phone number");
            return;
        }
        //make object of the user
        userObject=new User();
        userObject.email=mail;
        userObject.password=password;
        userObject.phone=phone;
        userObject.name=user;

        RequestApi(userObject);
        //mSignUpPresenter.startSignUp(userObject);


    }

    @Override
    public void RequestApi(final User user) {
        //request the api with the info ..and get Response
        //Response  .. if(error==0){ 1-save 2-login 3-OpenSignUPActivity

        progress();
        //Retrofit part to send request
        ApiHelper api= ServiceGenerator.createService(ApiHelper.class);
        Call<MainResponse> call=api.SignUpUser(user);

        call.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                 dialog.dismiss();
                if(response.body().error=="0"){

                    Toast.makeText(SignUpActivity.this,"Signed up successfully"+response.body().msg, Toast.LENGTH_LONG).show();
                    //save the user's session
                    mSignUpPresenter.startSignUp(user.email);
                }
                else if(response.body().error=="1"){
                    dialog.dismiss();
                    Toast.makeText(SignUpActivity.this,"Server Error:"+response.body().msg, Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(SignUpActivity.this, "unknown problem", Toast.LENGTH_SHORT).show();

            }
        });




    }

    @Override
    public void openLogInActivity() {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
    public void progress(){

        dialog = new ProgressDialog(SignUpActivity.this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
