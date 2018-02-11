package com.example.foush.otgmvp.ui.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.models.Responses.MainResponse;
import com.example.foush.otgmvp.models.Responses.SignInResponse;
import com.example.foush.otgmvp.models.User;
import com.example.foush.otgmvp.otgMvp;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Main.MainActivity;
import com.example.foush.otgmvp.ui.Signup.SignUpActivity;
import com.example.foush.otgmvp.utils.CommonUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONObject;

import java.lang.annotation.Annotation;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by foush on 09/02/18.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView {
    DataManager mDataManager;
    LoginPresenter mLoginPresenter;
    TextView signup;
    Button login;
    MaterialEditText mailtxt,passwordtxt;
    User userObject;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDataManager = ((otgMvp) getApplication()).getmDataManger();
        mLoginPresenter = new LoginPresenter(mDataManager);
        mLoginPresenter.onAttach(this);

        initView();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpClick();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginClick();
            }
        });





    }


    @Override
    public void onLoginClick() {
        String mail, password;
        mail = mailtxt.getText().toString();
        password = passwordtxt.getText().toString();
        //check Email and password
        if (!CommonUtils.isEmailValid(mail)){
            Toast.makeText(this, "Enter correct Email", Toast.LENGTH_LONG).show();
            mailtxt.setError("please Enter correct Email");
            return;
        }
        if (password == null || password.isEmpty()||!CommonUtils.isPasswordlValid(password)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_LONG).show();
            passwordtxt.setError("Please Enter valid password");
            return;
        }
        //make object of the user
        userObject=new User();
        userObject.email=mail;
        userObject.password=password;
        userObject.phone="";
        userObject.name="";
        RequestApi(userObject);



    }

    @Override
    public void onSignUpClick() {
        Intent intent=new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }

    @Override
    public void openMainActivity() {
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void RequestApi(final User user) {
        //request the api with the info ..and get Response
        //Response  .. if(error==0){ 1-save 2-login 3-Open MainActivity

        progress();
        //Retrofit part to send request
        ApiHelper api= ServiceGenerator.createService(ApiHelper.class);
        Call<SignInResponse> call=api.SignInUser(user);


        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.isSuccessful()) {
                    // Do your success stuff...
                    Toast.makeText(LoginActivity.this, "login successfully" , Toast.LENGTH_SHORT).show();
                    //save the user's session and save token to the session
                    mLoginPresenter.startSignIn(user.email, response.body().token);



                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(LoginActivity.this, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                //DO NETWORK ERROR HANDLING HERE

            }
        });


        /*
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {

                        Toast.makeText(LoginActivity.this, "Signed up successfully" , Toast.LENGTH_SHORT).show();
                        //save the user's session and save token to the session
                        mLoginPresenter.startSignIn(user.email, response.body().token);

                }else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(LoginActivity.this, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                dialog.dismiss();
                // NETWORK ERROR HANDLING HERE
                Toast.makeText(LoginActivity.this, "unknown problem", Toast.LENGTH_SHORT).show();

            }
        });

*/


    }


    @Override
    public void initView() {
         signup=findViewById(R.id.signup);
         login=findViewById(R.id.login);
         mailtxt=findViewById(R.id.mailtxt);
        passwordtxt=findViewById(R.id.passwordtxt);

    }
    public void progress(){

        dialog = new ProgressDialog(LoginActivity.this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
