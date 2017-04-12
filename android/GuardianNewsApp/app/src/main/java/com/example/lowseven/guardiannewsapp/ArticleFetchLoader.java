package com.example.lowseven.guardiannewsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class ArticleFetchLoader extends AsyncTaskLoader<List<Article>> {

    private String urlRequest;

    public ArticleFetchLoader(Context context, String UrlRequest) {
        super(context);
        this.urlRequest = UrlRequest;
    }

    @Override
    public List<Article> loadInBackground() {
        return (urlRequest != null) ? FetchDataHelper.fetchContent(urlRequest) : null ;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
