package com.bingyan.pixcelcity.main.entrance.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bingyan.pixcelcity.MainActivity;
import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.base.BaseImmerseActivity;
import com.bingyan.pixcelcity.main.entrance.signup.utils.TEditText;
import com.bingyan.pixcelcity.main.entrance.signup.utils.TInputConnection;
import com.bingyan.pixcelcity.model.BaseAPIModel;
import com.bingyan.pixcelcity.utils.HttpUtils;

public class VerifyCodeActivity extends BaseImmerseActivity {

    TEditText mEdtVerify1;
    TEditText mEdtVerify2;
    TEditText mEdtVerify3;
    TEditText mEdtVerify4;

    TEditText mCurrentFocusEdit;

    String telephone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifycode);
        Intent intent= getIntent();
        telephone = intent.getStringExtra("telephone");
        initViews();
    }

    private void initViews() {

        mEdtVerify1 = findViewById(R.id.edt_verifycode_1);
        mEdtVerify1.setBackSpaceLisetener(backspaceListener);
        mEdtVerify1.addTextChangedListener(watcher);

        mEdtVerify2 = findViewById(R.id.edt_verifycode_2);
        mEdtVerify2.setBackSpaceLisetener(backspaceListener);
        mEdtVerify2.addTextChangedListener(watcher);

        mEdtVerify3 = findViewById(R.id.edt_verifycode_3);
        mEdtVerify3.setBackSpaceLisetener(backspaceListener);
        mEdtVerify3.addTextChangedListener(watcher);

        mEdtVerify4 = findViewById(R.id.edt_verifycode_4);
        mEdtVerify4.setBackSpaceLisetener(backspaceListener);
        mEdtVerify4.addTextChangedListener(watcher);

        mCurrentFocusEdit = mEdtVerify1;
        mEdtVerify1.requestFocus();

        mEdtVerify1.setNext(mEdtVerify2);

        mEdtVerify2.setPrevious(mEdtVerify1);
        mEdtVerify2.setNext(mEdtVerify3);

        mEdtVerify3.setPrevious(mEdtVerify2);
        mEdtVerify3.setNext(mEdtVerify4);

        mEdtVerify4.setPrevious(mEdtVerify3);
    }

    TInputConnection.BackspaceListener backspaceListener = new TInputConnection.BackspaceListener() {
        @Override
        public boolean onBackspace() {
            Toast.makeText(VerifyCodeActivity.this, "back", Toast.LENGTH_SHORT).show();
            if (mCurrentFocusEdit != null) {
                //判断当前的这个是否为空
                if (TextUtils.isEmpty(mCurrentFocusEdit.getText().toString())) {
                    //如果是空的，那么说明要删掉上一个的edt，并且停留焦点在上一个edt
                    mCurrentFocusEdit.clearFocus();
                    if (mCurrentFocusEdit.getPrevious() != null) {
                        //清空
                        mCurrentFocusEdit.setText("");
                        //请求焦点
                        mCurrentFocusEdit.getPrevious().requestFocus();
                        mCurrentFocusEdit = mCurrentFocusEdit.getPrevious();
                    }
                    return true;
                }
            }
            return false;
        }
    };

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() >= 1) {
                //如果输入了内容，跳到下一个
                if (mCurrentFocusEdit != null) {
                    if (mCurrentFocusEdit != mEdtVerify4) {
                        mCurrentFocusEdit.clearFocus();
                    } else {
                        //如果是最后一个了，发送请求，如果不对，那么清空所有重新输入
                    }
                    if (mCurrentFocusEdit.getNext() != null) {
                        //如果有下一个，让下一个获取焦点
                        mCurrentFocusEdit.getNext().requestFocus();
                        mCurrentFocusEdit = mCurrentFocusEdit.getNext();
                    }
                }
            }

            //判断是不是四个都有数字了，如果是，那么登录
            if (!TextUtils.isEmpty(mEdtVerify1.getText().toString())
                    &&!TextUtils.isEmpty(mEdtVerify2.getText().toString())
                    &&!TextUtils.isEmpty(mEdtVerify3.getText().toString())
                    &&!TextUtils.isEmpty(mEdtVerify4.getText().toString())){
                String verifycode = mEdtVerify1.getText().toString()+mEdtVerify2.getText().toString()+mEdtVerify3.getText().toString()+mEdtVerify4.getText().toString();
                HttpUtils.HttpCallback callback = new HttpUtils.HttpCallback() {
                    @Override
                    public void onSending() {

                    }

                    @Override
                    public void getResult(String result) {
                        Intent intent = new Intent(VerifyCodeActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String error) {

                    }
                };
                BaseAPIModel.loginUser(callback,telephone, verifycode);
            }
        }
    };
}
