package com.maximprytyka.personalblog.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maximprytyka.personalblog.R;
import com.maximprytyka.personalblog.model.Comments;

import java.util.List;


public class RecycleViewAdapterComments extends RecyclerView.Adapter<RecycleViewAdapterComments.MyViewHolder> {

    RequestOptions options;
    private Context mContext;
    private List<Comments> mData;

    public RecycleViewAdapterComments(Context mContext, List lst) {

        this.mContext = mContext;
        this.mData = lst;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.comment_row_item, parent, false);
        // click listener here

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvNickname.setText(mData.get(position).getNickname());
        holder.tvText.setText(mData.get(position).getText());

        String IMG_URL = "https://www.gravatar.com/avatar/b83a886a5c437ccd9ac15473fd6f1788?s=125";
        // load image from the internet using Glide
        Glide.with(mContext).load(IMG_URL).apply(options).into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNickname, tvText;
        ImageView ivImage;
        CardView view_container;


        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.comcontainer);
            ivImage = itemView.findViewById(R.id.comimage);
            tvText = itemView.findViewById(R.id.comtext);
            tvNickname = itemView.findViewById(R.id.comnickname);
        }
    }
}