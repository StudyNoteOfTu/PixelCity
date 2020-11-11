package com.bingyan.pixcelcity.main.entrance.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.base.BaseImmerseActivity;
import com.bingyan.pixcelcity.main.entrance.signup.VerifyCodeActivity;
import com.bingyan.pixcelcity.model.BaseAPIModel;
import com.bingyan.pixcelcity.utils.HttpUtils;

public class LoginActivity extends BaseImmerseActivity {


    EditText mEdtTEL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        mEdtTEL = findViewById(R.id.edt_phone);
        findViewById(R.id.ll_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发出验证码
                BaseAPIModel.
                        getVerifyCode(new HttpUtils.HttpCallback() {
                    @Override
                    public void onSending() {

                    }

                    @Override
                    public void getResult(String result) {
                        Log.d("20201109TAG",result);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d("20201109TAG",error);
                    }
                },mEdtTEL.getText().toString());
                //跳到验证码界面
                Intent intent = new Intent(LoginActivity.this, VerifyCodeActivity.class);
                intent.putExtra("telephone",mEdtTEL.getText().toString());
                startActivity(intent);
            }
        });
    }
}
