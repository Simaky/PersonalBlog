package com.maximprytyka.personalblog.adapter;


import android.content.Context;
import android.content.Intent;
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
import com.maximprytyka.personalblog.activities.PostActivity;
import com.maximprytyka.personalblog.model.Posts;

import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    RequestOptions options;
    private Context mContext;
    private List<Posts> mData;

    public RecycleViewAdapter(Context mContext, List lst) {

        this.mContext = mContext;
        this.mData = lst;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.post_row_item, parent, false);
        // click listener here
        final MyViewHolder viewHolder = new MyViewHolder(view);


        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, PostActivity.class);
                i.putExtra("title", mData.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("text", mData.get(viewHolder.getAdapterPosition()).getText());
                i.putExtra("image", mData.get(viewHolder.getAdapterPosition()).getImage());
                i.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());

                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvCategorie.setText(category(mData.get(position).getCategorie_id()));
        holder.tvViews.setText(mData.get(position).getViews());
        holder.tvText.setText(mData.get(position).getText());

        // load image from the internet using Glide
        Glide.with(mContext).load(mData.get(position).getImage()).apply(options).into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvCategorie, tvViews, tvText;
        ImageView ivImage;
        CardView view_container;


        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tvTitle = itemView.findViewById(R.id.title);
            tvCategorie = itemView.findViewById(R.id.category);
            tvViews = itemView.findViewById(R.id.views);
            ivImage = itemView.findViewById(R.id.image);
            tvText = itemView.findViewById(R.id.text);
        }
    }

    private String category(String categoryId) {
        String categoryName = "";
//TODO category names to strigns
        switch (categoryId) {
            case "1":
                categoryName = "Sport";
                break;
            case "2":
                categoryName = "Nature";
                break;
            case "3":
                categoryName = "Programing";
                break;
            case "4":
                categoryName = "Gaming";
                break;
            case "5":
                categoryName = "Anime";
                break;
        }

        return categoryName;
    }

}