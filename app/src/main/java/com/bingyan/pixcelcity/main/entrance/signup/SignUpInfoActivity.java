package com.bingyan.pixcelcity.main.entrance.signup;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.base.BaseImmerseActivity;

public class SignUpInfoActivity extends BaseImmerseActivity {

    EditText mEdtName;
    EditText mEdtCity;
    EditText mEdtPhone;

    TextView mTvDescription;
    ImageView mImgHead;

    ImageView mImgBack;

    LinearLayout mLlNext;
    ImageView mImgNext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();
    }

    private void initViews() {
        mEdtName = findViewById(R.id.edt_name);
        mEdtCity = findViewById(R.id.edt_city);
        mEdtPhone= findViewById(R.id.edt_phone);

        mTvDescription = findViewById(R.id.tv_click_to_update);
        mImgHead = findViewById(R.id.iv_head);

        mImgBack = findViewById(R.id.iv_back);
        mLlNext = findViewById(R.id.ll_next);
        mImgNext = findViewById(R.id.iv_next);
    }
}
