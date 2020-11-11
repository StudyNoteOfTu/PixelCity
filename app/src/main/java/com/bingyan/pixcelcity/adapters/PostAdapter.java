package com.bingyan.pixcelcity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bingyan.pixcelcity.R;
import com.bingyan.pixcelcity.bean.PostMsg;
import com.bingyan.pixcelcity.holder.PostMsgHolder;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<PostMsg> msgs = new ArrayList<>();


    public void setData(List<PostMsg> list){
        this.msgs.clear();
        this.msgs.addAll(list);
    }

    public PostAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_msg,null,false);
        RecyclerView.ViewHolder holder = new PostMsgHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        PostMsgHolder msgHolder = (PostMsgHolder)holder;
        PostMsg item = msgs.get(position);

        msgHolder.imgIcon.setBackgroundResource(item.getImg_url());
        msgHolder.tvTitle.setText(item.getTitle());
        msgHolder.tvDetail.setText(item.getDetail());
        msgHolder.swipeItemLayout.setOpen(msgHolder.dataContainedLinearLayout.isOpen);

        msgHolder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgs.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return msgs.size();
    }

    public int getItemViewType(int position){
        return position;
    }
}
