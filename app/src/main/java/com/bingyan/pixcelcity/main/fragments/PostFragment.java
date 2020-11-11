package com.bingyan.pixcelcity.main.fragments;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.adapters.PostAdapter;
import com.bingyan.pixcelcity.base.NormalFragment;
import com.bingyan.pixcelcity.bean.PostMsg;
import com.bingyan.pixcelcity.vies.SpacesItemDecoration;
import com.bingyan.pixcelcity.vies.swipeViews.SwipeItemLayout;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends NormalFragment {

    RecyclerView recyclerView;


    PostAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main_post;
    }

    @Override
    protected void initViews(View mContentView) {
        recyclerView = mContentView.findViewById(R.id.recyclerView);
        adapter = new PostAdapter(getContext());
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getContext());
        recyclerView.addItemDecoration(new SpacesItemDecoration(40));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        recyclerView.setLayoutManager(linearLayoutManager);
        initData();

    }

    private void initData() {
        List<PostMsg> msgs = new ArrayList<>();
        PostMsg msg;
        msg = new PostMsg(R.drawable.oval,"Dominic Smith","dominic 想去","17:56");
        msgs.add(msg);
        msg = new PostMsg(R.drawable.oval,"Dominic Smith","探索完成","17:56");
        msgs.add(msg);
        msg = new PostMsg(R.drawable.oval3,"来华中科技大学","探索更多","17:56");
        msgs.add(msg);
        msg = new PostMsg(R.drawable.oval3,"来华中科技大学","探索更多","17:56");
        msgs.add(msg);
        msg = new PostMsg(R.drawable.oval3,"来华中科技大学","探索更多","17:56");
        msgs.add(msg);
        msg = new PostMsg(R.drawable.oval2,"新人徽章领取","","17:56");
        msgs.add(msg);
        msg = new PostMsg(R.drawable.oval2,"本地徽章","已完成60/100，继续完成获得徽章","17:56");
        msgs.add(msg);

        adapter.setData(msgs);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void switchTitle(String title) {

    }

    @Override
    protected void refreshState(boolean isEdit, boolean isShow) {

    }
}
