package com.bingyan.pixcelcity.main.entrance.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bingyan.pixcelcity.MainActivity;
import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.base.BaseImmerseActivity;
import com.bingyan.pixcelcity.main.entrance.login.LoginActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloActivity extends BaseImmerseActivity implements ViewPager.OnPageChangeListener{

    ViewPager viewPager;
    ViewPagerAdapter adapter;

    ImageView img_current;
    TextView tvIntro;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_viewpager);

        initViews();
    }

    private void initViews() {
        img_current = findViewById(R.id.iv_current);
        tvIntro = findViewById(R.id.tv_intro);
        tvIntro.setText("给你一种新的体验世界的感觉");
        Glide.with(this).load(R.drawable.page_1).into(img_current);
        viewPager = findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelloActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                tvIntro.setText("给你一种新的体验世界的感觉");
                Glide.with(this).load(R.drawable.page_1).into(img_current);
                break;
            case 1:
                tvIntro.setText("遇到一些奇妙的角度");
                Glide.with(this).load(R.drawable.page_2).into(img_current);
                break;
            case 2:
                tvIntro.setText("尝试不一样的打卡体验");
                Glide.with(this).load(R.drawable.page_3).into(img_current);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ViewPagerAdapter extends PagerAdapter{

        List<Map<String,Object>> viewLists = new ArrayList<>();

        public ViewPagerAdapter(){
            Map<String, Object> map = new HashMap<>();
            map.put("url", R.drawable.page_pic_1);
            map.put("view", new ImageView(HelloActivity.this));
            viewLists.add(map);

            Map<String, Object> map1 = new HashMap<>();
            map1.put("url", R.drawable.page_pic_2);
            map1.put("view", new ImageView(HelloActivity.this));
            viewLists.add(map1);

            Map<String, Object> map2 = new HashMap<>();
            map2.put("url", R.drawable.page_pic_3);
            map2.put("view", new ImageView(HelloActivity.this));
            viewLists.add(map2);
        }
        @Override
        public int getCount() {
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ImageView x = (ImageView) viewLists.get(position).get("view");
            x.setScaleType(ImageView.ScaleType.FIT_CENTER);
            container.removeView(x);
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position){ //实例化Item
            ImageView imageView = (ImageView) viewLists.get(position).get("view");
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(HelloActivity.this)
                    .load(viewLists.get(position).get("url"))
                    .into(imageView);
            view.addView(imageView, 0);

            return viewLists.get(position).get("view");
        }
    }
}
