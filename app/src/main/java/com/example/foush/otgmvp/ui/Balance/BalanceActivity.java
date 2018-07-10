package com.example.foush.otgmvp.ui.Balance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.data.ApiHelper;
import com.example.foush.otgmvp.data.DataManager;
import com.example.foush.otgmvp.data.ServiceGenerator;
import com.example.foush.otgmvp.models.Responses.BalanceResponse;
import com.example.foush.otgmvp.otgStore;
import com.example.foush.otgmvp.ui.Base.BaseActivity;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by foush on 09/02/18.
 */

public class BalanceActivity extends BaseActivity implements BalanceMvpView {
    DataManager mDataManager;
    BalancePresenter mBalancePresenter;

    EditText cardNumbertxt;
    TextView currentCredit;
    Button   submitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        mDataManager = ((otgStore) getApplication()).getmDataManger();
        mBalancePresenter = new BalancePresenter(mDataManager);
        mBalancePresenter.onAttach(this);

        //animation to the window of the Activity
        mBalancePresenter.slideAnimation();

        initView();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardNumber=cardNumbertxt.getText().toString();
                mBalancePresenter.requestCredit(cardNumber);
            }
        });


    }

    @Override
    public void initView() {
        cardNumbertxt      =     findViewById(R.id.cardNumbertxt);
        currentCredit      =     findViewById(R.id.currentCredit);
        submitButton       =     findViewById(R.id.submit);

    }

    @Override
    public void setUpWindowAnimations() {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Log.i("ANIM", "slide called");
            Slide slide = new Slide(Gravity.LEFT);
            slide.setDuration(200);
            getWindow().setEnterTransition(slide);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onSubmit(String carNumber) {

        ApiHelper apiHelper= ServiceGenerator.createService(ApiHelper.class);
        Call<BalanceResponse> call =apiHelper.CreditRequest(mDataManager.getToken(),carNumber);
        call.enqueue(new Callback<BalanceResponse>() {
            @Override
            public void onResponse(retrofit2.Call<BalanceResponse> call, Response<BalanceResponse> response) {
                if (response.isSuccessful()) {
                    // Do your success stuff...
                    mBalancePresenter.updateUI(response.body().credits);


                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(BalanceActivity.this, jObjError.getString("msg"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(BalanceActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<BalanceResponse> call, Throwable t) {
                //DO NETWORK ERROR HANDLING HERE
            }
        });
    }

    @Override
    public void updateUI(String credit) {
        currentCredit.setText(credit);
    }

    @Override
    public void Error() {
        cardNumbertxt.setError("Enter correct card number .. Null is denied");
    }


}
