package com.bingyan.pixcelcity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bingyan.pixcelcity.base.BaseImmerseActivity;
import com.bingyan.pixcelcity.base.NormalFragment;
import com.bingyan.pixcelcity.main.fragments.ExploreFragment;
import com.bingyan.pixcelcity.main.fragments.MapFragment;
import com.bingyan.pixcelcity.main.fragments.MineFragment;
import com.bingyan.pixcelcity.main.fragments.PostFragment;

public class MainActivity extends BaseImmerseActivity {


    private RadioGroup radioGroup;
    private RadioButton rbMap;
    private RadioButton rbExplore;
    private RadioButton rbPost;
    private RadioButton rbMine;

    private MapFragment mapFragment;
    private ExploreFragment exploreFragment;
    private PostFragment postFragment;
    private MineFragment mineFragment;

    private NormalFragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //初始化控件
        initViews();
        //初始化fragments
        initFragment();
        //设置切换监听器
        setListener();

        request();
    }

    private void request() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbMap:
                        switchFragment(mapFragment);
                        break;
                    case R.id.rbExplore:
                        switchFragment(exploreFragment);
                        break;
                    case R.id.rbPost:
                        switchFragment(postFragment);
                        break;
                    case R.id.rbMine:
                        switchFragment(mineFragment);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initFragment(){
        mapFragment = new MapFragment();
//        mapFragment.setActionBar(getSupportActionBar());

        exploreFragment = new ExploreFragment();
//        exploreFragment.setActionBar(getSupportActionBar());

        postFragment = new PostFragment();
//        postFragment.setActionBar(getSupportActionBar());

        mineFragment = new MineFragment();
//        mineFragment.setActionBar(getSupportActionBar());

        mFragment = mapFragment;
        getSupportFragmentManager().beginTransaction().add(R.id.container,mFragment).commit();
//        mFragment.switchTitle("首页");
    }


    private void switchFragment(NormalFragment fragment) {
//        if (fragment instanceof MapFragment){
//            fragment.switchTitle("首页");
//        }else if (fragment instanceof ExploreFragment){
//            fragment.switchTitle("记背本");
//        }else if (fragment instanceof PostFragment){
//            fragment.switchTitle("我的");
//        }else if (fragment instanceof MineFragment){
//            fragment.switchTitle("我的");
//        }
//        if (fragment instanceof MapFragment){
//            findViewById(R.id.statusbar).setVisibility(View.GONE);
//        }else{
//            findViewById(R.id.statusbar).setVisibility(View.VISIBLE);
//        }
        //判断当前显示的Fragment是不是切换的Fragment
        if (mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.container, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

    private void initViews() {

        radioGroup = findViewById(R.id.radioGroup);
        rbMap = findViewById(R.id.rbMap);
        rbExplore = findViewById(R.id.rbExplore);
        rbPost = findViewById(R.id.rbPost);
        rbMine = findViewById(R.id.rbMine);

        setStyle(R.drawable.selector_main_rb_map,rbMap);
        setStyle(R.drawable.selector_main_rb_explore,rbExplore);
        setStyle(R.drawable.selector_main_rb_post,rbPost);
        setStyle(R.drawable.selector_main_rb_mine,rbMine);
    }


    private void setStyle(int selector,RadioButton rb){
        Drawable drawable = getResources().getDrawable(selector);
        drawable.setBounds(0,0,80,80);
        rb.setCompoundDrawables(null,drawable,null,null);
    }
}
