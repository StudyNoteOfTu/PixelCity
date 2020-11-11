package com.bingyan.pixcelcity.main.mine;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.base.BaseImmerseActivity;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

public class MineInfoActivity extends BaseImmerseActivity {

    RoundedImageView roundedImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);

        initViews();

        initData();
    }

    private void initData() {

    }

    private void initViews() {
        roundedImageView= findViewById(R.id.iv_head);
    }
}
