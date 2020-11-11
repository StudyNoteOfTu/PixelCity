package com.bingyan.pixcelcity.main.entrance;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bingyan.pixcelcity.R;

public class SplashActivity extends AppCompatActivity {


    Button mBtnlogin;
    Button mBtnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


    }
}
