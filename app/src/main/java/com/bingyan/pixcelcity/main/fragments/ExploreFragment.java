package com.bingyan.pixcelcity.main.fragments;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.ViewPager;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.base.NormalFragment;
import com.bingyan.pixcelcity.bean.Location;
import com.bingyan.pixcelcity.main.activities.PublishActivity;
import com.bingyan.pixcelcity.model.LocationModel;
import com.bingyan.pixcelcity.utils.HttpUtils;
import com.bingyan.pixcelcity.vies.card.CardItem;
import com.bingyan.pixcelcity.vies.card.CardPagerAdapter;
import com.bingyan.pixcelcity.vies.card.ShadowTransformer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.bingyan.pixcelcity.utils.HttpUtils.JSON;

public class ExploreFragment extends NormalFragment {
    private ViewPager viewPager;
    private ShadowTransformer shadowTransformer;
    private CardPagerAdapter cardPagerAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main_explore;
    }

    @Override
    protected void initViews(View mContentView) {
        initData();
        viewPager = mContentView.findViewById(R.id.viewPager);
        shadowTransformer = new ShadowTransformer(viewPager, cardPagerAdapter);
        viewPager.setAdapter(cardPagerAdapter);
        viewPager.setPageTransformer(false, shadowTransformer);
        viewPager.setOffscreenPageLimit(3);
        shadowTransformer.enableScaling(true);

        ImageView iv_publish = mContentView.findViewById(R.id.iv_publish);
        iv_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PublishActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void switchTitle(String title) {
        if (mActionBar != null) {
            mActionBar.setCustomView(R.layout.actionbar_explore);
            mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            View customView = mActionBar.getCustomView();
            ((TextView) customView.findViewById(R.id.tv_title)).setText("探索");
        }
    }

    private void initData() {

        cardPagerAdapter = new CardPagerAdapter(getContext());
        //获取location
        LocationModel locationModel = new LocationModel();
        locationModel.searchList(new HttpUtils.HttpCallback() {
            @Override
            public void onSending() {

            }

            @Override
            public void getResult(final String result) {
                ((Activity) myContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CardItem cardItem;
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray array = jsonObject.getJSONArray("data");

                            List<CardItem> list = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                cardItem = new CardItem();
                                cardItem.setLocation(new Location(object.getString("id"),
                                        object.getLong("created_at"),
                                        object.getLong("delete_at"),
                                        object.getInt("status"),
                                        object.getString("user_id"),
                                        object.getString("username"),
                                        object.getString("location_picture"),
                                        object.getString("text"),
                                        object.getString("country"),
                                        object.getString("city"),
                                        object.getString("location_name"),
                                        object.getDouble("longitude"),
                                        object.getDouble("latitude"),
                                        object.getInt("total_explored_count"),
                                        object.getInt("total_wanted_count")
                                ));
                                list.add(cardItem);
                            }
                            cardPagerAdapter.setData(list);
                            cardPagerAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onError(String error) {

            }
        }, 0, "", "", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MzUxNDU5MDcsImlkIjoiNWY5NGViYjA2MWI0NmY3YWZhNmMyNjA1In0.zo2tmKoybdZQxf6wlo1oZsewdbHtd5ff9KOJoSWw_i0");

    }

    @Override
    protected void refreshState(boolean isEdit, boolean isShow) {

    }
}
