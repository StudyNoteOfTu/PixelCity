package com.bingyan.pixcelcity.main.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.adapters.BadgeAdapter;
import com.bingyan.pixcelcity.bean.Badge;

import java.util.ArrayList;
import java.util.List;

public class MyBadgeActivity extends AppCompatActivity {

    private GridView gridView;
    private BadgeAdapter adapter;

    private ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);
        initViews();
    }

    private void initViews() {
        gridView = findViewById(R.id.gridview);
        adapter = new BadgeAdapter(this);
        gridView.setAdapter(adapter);

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        List<Badge> badges = new ArrayList<>();
        badges.add(new Badge(R.drawable.badge,"本地徽章"));
        badges.add(new Badge(R.drawable.badge1,"本地徽章"));
        badges.add(new Badge(R.drawable.badge2,"本地徽章"));
        badges.add(new Badge(R.drawable.badge3,"本地徽章"));
        badges.add(new Badge(R.drawable.badge4,"本地徽章"));
        badges.add(new Badge(R.drawable.badge5,"本地徽章"));
        badges.add(new Badge(R.drawable.badge6,"本地徽章"));
        adapter.setData(badges);
        adapter.notifyDataSetChanged();
    }
}
