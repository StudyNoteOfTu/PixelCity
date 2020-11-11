package com.bingyan.pixcelcity.base;

import android.util.Log;

import androidx.appcompat.app.ActionBar;

public abstract class ActionBarFragment extends NormalFragment {

    protected ActionBar mActionBar;

    public void setActionBar(ActionBar actionBar){
        mActionBar = actionBar;
    }

    public abstract void switchTitle(String title);

    @Override
    protected void refreshState(boolean isEdit, boolean isShow) {}

    public interface OnMenuSelectListener{
        void selectMenuItem(ActionBarFragment fromFragment, int menu_id);
    }

    private OnMenuSelectListener mOnMenuSelectListener;

    public final void setOnMenuSelectListener(OnMenuSelectListener listener){
        mOnMenuSelectListener = listener;
    }

    public void onMenuSelect(int menu_id){
        if (mOnMenuSelectListener != null){
            mOnMenuSelectListener.selectMenuItem(this,menu_id);
        }else{
            Log.d("OnMenuSelect","listener is null");
        }
    }
}
