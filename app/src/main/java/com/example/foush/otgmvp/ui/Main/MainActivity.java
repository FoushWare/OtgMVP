package com.example.foush.otgmvp.ui.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foush.otgmvp.R;
import com.example.foush.otgmvp.ui.Base.BaseActivity;
import com.example.foush.otgmvp.ui.Dataset.DataSetActivity;
import com.example.foush.otgmvp.ui.Main.MainMvpView;
import com.flipboard.bottomsheet.BottomSheetLayout;

/**
 * Created by foush on 09/02/18.
 */

public class MainActivity extends BaseActivity implements MainMvpView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomSheetLayout bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheet.showWithSheetView(LayoutInflater.from(MainActivity.this).inflate(R.layout.my_sheet_layout, bottomSheet, false));


        Button button=(Button)findViewById(R.id.take_pics);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DataSetActivity.class);
                startActivity(intent);
                finish();
            }
        });






    }
}
