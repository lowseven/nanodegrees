package com.example.lowseven.guardiannewsapp;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ArticleRecyclerViewAdapter
        extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article> items;
    private int itemLayout;
    private Context context;

    public ArticleRecyclerViewAdapter(Context context, ArrayList<Article> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ArticleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = items.get(position);

        holder.sectionTextView.setText(article.getSectionName());
        holder.titleTextView.setText(article.getWebTitle());
        holder.titleTextView.setTag(article.getWebUrl());
        Picasso
                .with(context)
                .load(Uri.parse(article.gethumbnail()))
                .into(holder.thumbnail);
//        holder.thumbnail.setImageURI(Uri.parse(article.gethumbnail()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
