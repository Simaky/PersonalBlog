package com.maximprytyka.personalblog.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maximprytyka.personalblog.R;
import com.maximprytyka.personalblog.model.Posts;

import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    RequestOptions options;
    private Context mContext;
    private List<Posts> mData;
    private final String IMG_URL = "http://testblog.epizy.com/static/images/";


    public RecycleViewAdapter(Context mContext, List lst) {


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
        view = mInflater.inflate(R.layout.post_row_item, parent, false);
        // click listener here
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvCategorie.setText(mData.get(position).getCategorie_id());
        holder.tvViews.setText(mData.get(position).getViews());

        String glideUrl = mData.get(position).getImage();
        String test22 = "https://static.gamespot.com/uploads/scale_super/1575/15759911/3295836-saitama-protagonista-one-punch-man_lncima20170629_0043_3.jpg";
        // load image from the internet using Glide
        Glide.with(mContext).load(test22).apply(options).into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvCategorie, tvViews;
        ImageView ivImage;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvCategorie = itemView.findViewById(R.id.category);
            tvViews = itemView.findViewById(R.id.views);
            ivImage = itemView.findViewById(R.id.image);
        }
    }


}