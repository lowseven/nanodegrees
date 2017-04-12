package com.example.lowseven.guardiannewsapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    public ImageView thumbnail;
    public TextView sectionTextView;
    public TextView titleTextView;

    public ArticleViewHolder(View parent) {
        super(parent);
        this.thumbnail = (ImageView) parent.findViewById(R.id.thumbnail);
        this.sectionTextView = (TextView) parent.findViewById(R.id.section_text_view);
        this.titleTextView = (TextView) parent.findViewById(R.id.title_text_view);
    }

}
