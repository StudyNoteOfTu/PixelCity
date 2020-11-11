package com.bingyan.pixcelcity.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.bingyan.pixcelcity.R;


public abstract class NormalFragment extends Fragment {


    public Context myContext;
    public Activity myActivity;
    protected View mContentView;

    protected abstract @LayoutRes
    int setLayoutId();

    protected abstract void initViews(View mContentView);

    protected ActionBar mActionBar;
    public void setActionBar(ActionBar actionBar){
        mActionBar = actionBar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == mContentView) {
            mContentView = inflater.inflate(setLayoutId(),container,false);

            initViews(mContentView);

        }
//        if (mContentView != null) {
//            ViewGroup parent = (ViewGroup) mContentView.getParent();
//            if (parent != null) {
//                parent.removeView(mContentView);
//            }
//            return mContentView;
//        }
//        Log.d("2020413Fragment", "onCreateView2");
//        mContentView = inflater.inflate(setLayoutId(), container, false);
//        Log.d("2020413Fragment", "onCreateView3" + ((LinearLayout) mContentView).getChildCount());
//        initViews(mContentView);
//        Log.d("2020413Fragment", "onCreateView4");
        return mContentView;
    }

    public abstract void switchTitle(String title);

    @Override
    public void onResume() {
        Log.d("2020413Fragment", "onResume");
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("2020413Fragment", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        Log.d("2020413Fragment","onPause");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.d("2020413Fragment","onDestroy");
        super.onDestroy();
    }



    @Override
    public void onAttach(@NonNull Context context) {
        this.myContext = context;
        super.onAttach(context);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        this.myActivity = activity;
        super.onAttach(activity);
    }


    protected abstract void refreshState(boolean isEdit, boolean isShow);

}
