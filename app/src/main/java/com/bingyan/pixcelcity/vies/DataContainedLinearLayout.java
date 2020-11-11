package com.bingyan.pixcelcity.vies;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bingyan.pixcelcity.vies.swipeViews.OnSwipeItemOpenListener;


public class DataContainedLinearLayout extends LinearLayout {


    public boolean isOpen = false;

    private OnSwipeItemOpenListener l;

    private boolean isShowingPicture = false;

    public void setOnSwipeItemOpenListener(OnSwipeItemOpenListener l){
        this.l = l;
    }

    private int indexInAdapterList = -1;


    public DataContainedLinearLayout(Context context) {
        super(context);
    }

    public DataContainedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DataContainedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DataContainedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public int getIndexInAdapterList() {
        return indexInAdapterList;
    }

    public void setIndexInAdapterList(int indexInAdapterList) {
        this.indexInAdapterList = indexInAdapterList;
    }


    public void onOpenStateChanged(boolean isOpen) {
        if (l!=null){
            l.onOpenStateChanged(isOpen);
            this.isOpen = isOpen;
        }
    }
}

