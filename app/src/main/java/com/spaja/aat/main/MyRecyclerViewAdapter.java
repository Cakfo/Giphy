package com.spaja.aat.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spaja.aat.R;
import com.spaja.aat.model.GifData;
import com.spaja.aat.views.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

/**
 * Created by Spaja on 03-Nov-17.
 */

class MyRecyclerViewAdapter extends RealmRecyclerViewAdapter<GifData, MyRecyclerViewAdapter.MyViewHolder> {

    private RealmResults<GifData> mDataSet;

    MyRecyclerViewAdapter(@NonNull Context context, @Nullable RealmResults<GifData> data, boolean autoUpdate) {
        super(context, data, autoUpdate);
        this.mDataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final GifData gifData = mDataSet.get(position);

        if (gifData != null) {
            holder.gifTitle.setText(gifData.getTitle());
        }

        holder.position.setText(String.valueOf(position));

        Glide.with(context)
                .load(gifData.getImages().getOriginalStill().getUrl())
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_placeholder))
                .into(holder.gifPicture);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, GifActivity.class);
                i.putExtra("url", gifData.getImages().getOriginal().getUrl());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView (R.id.gif_title) CustomTextView gifTitle;
        @BindView (R.id.position) CustomTextView position;
        @BindView (R.id.gif_picture) ImageView gifPicture;

        MyViewHolder(android.view.View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
