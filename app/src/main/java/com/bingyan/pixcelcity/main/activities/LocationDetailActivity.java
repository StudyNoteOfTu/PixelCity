package com.bingyan.pixcelcity.main.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.base.BaseImmerseActivity;
import com.bingyan.pixcelcity.bean.Config;
import com.bingyan.pixcelcity.bean.Location;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

public class LocationDetailActivity extends BaseImmerseActivity {

    TextView tvLocation;
    ImageView ivPic;
    TextView tvLocate;//具体位置
    TextView tvCountExplore;
    TextView tvCountWant;
    RoundedImageView imgHead;
    TextView tvName;
    TextView tvIntro;

    Location location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        location = Config.location;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        initViews();

        LinearLayout ll=findViewById(R.id.ll_gotoexplore);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationDetailActivity.this,TakePhotoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        TextView tvLocation = findViewById(R.id.tv_location);
        tvLocation.setText(location.getLocationName());
        ImageView ivPic = findViewById(R.id.iv_pic);
        Glide.with(this).load(location.getLocationPicture()).into(ivPic);
        TextView tvLocate = findViewById(R.id.tv_locate);
        tvLocate.setText(location.getLocationName());
        TextView tvCountExplore = findViewById(R.id.tv_count_explore);
        tvCountExplore.setText(location.getTotalExploredCount()+"次被探索");
        TextView tvCountWant = findViewById(R.id.tv_count_want);
        tvCountWant.setText(location.getTotalWantedCount()+"次被想去");
        RoundedImageView imgHead = findViewById(R.id.img_head);
        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(location.getUsername());
        TextView tvIntro = findViewById(R.id.tv_intro);
        tvIntro.setText(location.getText());
    }
}
