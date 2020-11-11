package com.bingyan.pixcelcity.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.vies.DataContainedLinearLayout;
import com.bingyan.pixcelcity.vies.swipeViews.SwipeItemLayout;

public class PostMsgHolder extends RecyclerView.ViewHolder{
    public ImageView imgIcon;
    public TextView tvTitle;
    public TextView tvDetail;
    public TextView tvDate;

    public ImageView img_ok;
    public ImageView img_top;
    public ImageView img_delete;

    public DataContainedLinearLayout dataContainedLinearLayout;

    public SwipeItemLayout swipeItemLayout;

    public PostMsgHolder(@NonNull View itemView) {
        super(itemView);
        imgIcon = itemView.findViewById(R.id.img_icon);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDetail = itemView.findViewById(R.id.tv_detail);
        tvDate = itemView.findViewById(R.id.tv_date);

        img_ok = itemView.findViewById(R.id.img_ok);
        img_top = itemView.findViewById(R.id.img_top);
        img_delete = itemView.findViewById(R.id.img_delete);

        dataContainedLinearLayout = itemView.findViewById(R.id.datacontained_linearlayout);

        swipeItemLayout = itemView.findViewById(R.id.swipeItemLayout);
    }

}
