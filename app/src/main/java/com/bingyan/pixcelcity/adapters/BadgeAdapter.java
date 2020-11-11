package com.bingyan.pixcelcity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.bean.Badge;

import java.util.ArrayList;
import java.util.List;

public class BadgeAdapter extends BaseAdapter {

    private Context mContext;

    private List<Badge> badgeList = new ArrayList<>();

    public void setData(List<Badge> passFolders){
        badgeList.clear();
        badgeList.addAll(passFolders);
    }

    public BadgeAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return badgeList.size();
    }

    @Override
    public Object getItem(int position) {
        return badgeList.get(position);
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
        BadgeAdapter.ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_badge,parent,false);
            viewHolder = new BadgeAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (BadgeAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setBackgroundResource(badgeList.get(position).getUrl());
        viewHolder.textView.setText(badgeList.get(position).getName());


        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView){
            this.imageView = itemView.findViewById(R.id.img_badge);
            this.textView = itemView.findViewById(R.id.tv_name_badge);
        }
    }
}
