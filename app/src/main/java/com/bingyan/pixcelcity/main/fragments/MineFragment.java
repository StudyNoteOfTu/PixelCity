package com.bingyan.pixcelcity.main.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.adapters.MyPublicGridAdapter;
import com.bingyan.pixcelcity.base.NormalFragment;
import com.bingyan.pixcelcity.main.activities.MyBadgeActivity;
import com.bingyan.pixcelcity.main.activities.SettingActivity;
import com.bingyan.pixcelcity.main.mine.MineInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class MineFragment extends NormalFragment {
//    private GridView mGridView;
//    private MyPublicGridAdapter mAdapter;
//
//    private TextView tvIntentBadge;
//
//    private ImageView imgSetting;

    CardView cardView;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main_mine_2;
    }

    @Override
    protected void initViews(View mContentView) {
        cardView = mContentView.findViewById(R.id.cardview);
//        tvIntentBadge = mContentView.findViewById(R.id.tv_intent_badge);
//        imgSetting = mContentView.findViewById(R.id.img_setting);
//        mGridView = mContentView.findViewById(R.id.gridview);
//        mAdapter = new MyPublicGridAdapter(getContext());
//        mGridView.setAdapter(mAdapter);
        initData();

        initEvents();
    }

    private void initEvents() {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MineInfoActivity.class);
                startActivity(intent);
            }
        });
//        tvIntentBadge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MyBadgeActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        imgSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SettingActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void initData() {
//        List<Integer> pics = new ArrayList<>();
//        pics.add(R.drawable.pic1);
//        pics.add(R.drawable.pic2);
//        pics.add(R.drawable.pic3);
//        pics.add(R.drawable.pic4);
//        pics.add(R.drawable.pic5);
//        pics.add(R.drawable.pic6);
//        pics.add(R.drawable.pic3);
//        pics.add(R.drawable.pic1);
//        mAdapter.setData(pics);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void switchTitle(String title) {

    }

    @Override
    protected void refreshState(boolean isEdit, boolean isShow) {

    }
}
