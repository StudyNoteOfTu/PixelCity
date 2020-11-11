package com.bingyan.pixcelcity.vies.card;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Parcelable;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.bean.Config;
import com.bingyan.pixcelcity.main.activities.LocationDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    private Context context;

    public CardPagerAdapter(Context context) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        this.context = context;
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
        Log.d("1023TAG","--------------addCardItem----------------");
        Log.d("1023TAG","addCardItem = "+mData.toString());
        Log.d("1023TAG","------------------------------");

    }

    public void setData(List<CardItem> cardItems) {
        this.mData.clear();
        this.mData.addAll(cardItems);
        for (int i = 0; i < cardItems.size(); i++) {
            mViews.add(null);
        }

        Log.d("1023TAG","--------------addCardItem----------------");
        Log.d("1023TAG","addCardItem = "+mData.toString());
        Log.d("1023TAG","------------------------------");
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        if (mViews.size() == 0) {
            return null;
        }
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.fragment_explore_overview2, container, false);
        container.addView(view);
        Log.d("1023TAG","------------------------------");
        Log.d("1023TAG","position = "+position);
        Log.d("1023TAG","data = "+mData.toString());
        Log.d("1023TAG","------------------------------");

        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardview);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(final CardItem item, View view) {
        CardView cardView = view.findViewById(R.id.cardview);

        TextView tv_locate = view.findViewById(R.id.tv_locate);
        tv_locate.setText(item.getLocation().getLocationName());

        ImageView iv_pic = view.findViewById(R.id.iv_pic);
        Glide.with(context).load(item.getLocation().getLocationPicture()).into(iv_pic);

        TextView tv_count_explore = view.findViewById(R.id.tv_count_explore);
        tv_count_explore.setText(item.getLocation().getTotalExploredCount()+"次被探索");

        TextView tv_count_want = view.findViewById(R.id.tv_count_want);
        tv_count_want.setText(item.getLocation().getTotalWantedCount()+"次想去");


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LocationDetailActivity.class);
                Config.location = item.getLocation();
                context.startActivity(intent);
            }
        });

    }


    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
    }

}