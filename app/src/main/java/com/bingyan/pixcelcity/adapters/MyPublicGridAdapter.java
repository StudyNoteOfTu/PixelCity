package com.bingyan.pixcelcity.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bingyan.pixcelcity.R;

import java.util.ArrayList;
import java.util.List;

public class MyPublicGridAdapter extends BaseAdapter {

    private Context mContext;

    private List<Integer> picList = new ArrayList<>();

    public void setData(List<Integer> passFolders){
        picList.clear();
        picList.addAll(passFolders);
    }

    public MyPublicGridAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return picList.size();
    }

    @Override
    public Object getItem(int position) {
        return picList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_imageview,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setBackgroundResource(picList.get(position));
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup();
            }
        });

        return convertView;
    }

    private void popup() {
        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);

        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        View inflate = View.inflate(mContext, R.layout.popup_big_detail,null);

        popupWindow.setContentView(inflate);

        popupWindow.showAsDropDown(inflate);

        popupWindow.setBackgroundDrawable(new ColorDrawable());

        popupWindow.setOutsideTouchable(true);

        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }

    class ViewHolder{
        ImageView imageView;

        ViewHolder(View itemView){
            this.imageView = itemView.findViewById(R.id.imageview);
        }
    }
}
