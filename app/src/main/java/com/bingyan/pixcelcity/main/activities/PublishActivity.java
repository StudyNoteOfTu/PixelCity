package com.bingyan.pixcelcity.main.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.base.BaseImmerseActivity;
import com.bingyan.pixcelcity.bean.currentUser.Publish;
import com.bingyan.pixcelcity.temp.Config;
import com.bumptech.glide.Glide;

public class PublishActivity extends BaseImmerseActivity {

    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublishActivity.this,TakePhotoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("KKKTAG","resume"+Config.takePic+"Config.pic = null"+(Config.pic==null));
        if (Config.takePic && Config.pic!=null){
            image.setImageBitmap(Config.pic);
            Config.takePic   = false;
        }
    }
}
