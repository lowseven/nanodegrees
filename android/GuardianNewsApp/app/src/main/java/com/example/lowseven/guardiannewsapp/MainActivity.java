package com.example.lowseven.guardiannewsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>>{

    private static final String API_URL = "https://content.guardianapis.com/search";
    private static final String API_KEY = "c14de8f4-ebe7-47fc-a928-fbed5c54e97b";
    private static final String PAGE_SIZE = "20";
    private static final int LOADER_ID = 1;

    private ProgressBar onOpenAppProgressbar;
    private ProgressBar onLoadContentProgressbar;
    private ArticleRecyclerViewAdapter adapter;
    private TextView errorTextView;
    private int pageIndex;
    private ArrayList<Article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.pageIndex = 1;

        onOpenAppProgressbar = (ProgressBar) findViewById(R.id.on_open_progress_bar);
        onLoadContentProgressbar = (ProgressBar) findViewById(R.id.on_load_content_progress_bar);
        errorTextView = (TextView) findViewById(R.id.error_text_view);
        onLoadContentProgressbar.setVisibility(View.GONE);


        if(!isConnectionAvailable()) {
            errorTextView.setText(R.string.error_connectivity);
            onOpenAppProgressbar.setVisibility(View.GONE);
        }
        else {
            errorTextView.setVisibility(View.GONE);
            getLoaderManager().initLoader(LOADER_ID, null, this);
        }
    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {
        Uri baseUrl= Uri.parse(API_URL);
        if(adapter != null && articles != null)
            onLoadContentProgressbar.setVisibility(View.VISIBLE);

        Uri.Builder urlRequest = baseUrl.buildUpon();
        urlRequest.appendQueryParameter("page-size", PAGE_SIZE);
        urlRequest.appendQueryParameter("show-fields", "thumbnail");
        urlRequest.appendQueryParameter("order-by", "newest");
        urlRequest.appendQueryParameter("page", Integer.toString(pageIndex));
        urlRequest.appendQueryParameter("api-key", API_KEY);

        pageIndex++;
        return new ArticleFetchLoader(this, urlRequest.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> data) {
        onLoadContentProgressbar.setVisibility(View.GONE);

        if(data == null) {
            errorTextView.setText(R.string.error_loading_content);
            errorTextView.setVisibility(View.VISIBLE);
        } else if (adapter == null){
            initRecycleList(data);
        } else if( articles != null) {
            articles.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        onLoadContentProgressbar.setVisibility(View.VISIBLE);
    }

    public void openTheBrowser(View view) {
        String webUrl = view.findViewById(R.id.title_text_view).getTag().toString();

        Uri webPage = Uri.parse(webUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);

        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    private void initRecycleList(List<Article> data) {
        articles = new ArrayList<>(data);
        adapter = new ArticleRecyclerViewAdapter(this, articles, R.layout.list_item);
        onOpenAppProgressbar.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        RecyclerView listRecycleView = (RecyclerView) findViewById(R.id.list_view);
        listRecycleView.setHasFixedSize(true);
        listRecycleView.setAdapter(adapter);
        listRecycleView.setLayoutManager(linearLayoutManager);
        listRecycleView.setItemAnimator(new DefaultItemAnimator());

        listRecycleView.addOnScrollListener(new EndlessScrollerListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount, RecyclerView view) {
                getLoaderManager().restartLoader(LOADER_ID, null, MainActivity.this);
            }
        });
    }

    private boolean isConnectionAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return info != null && info.isConnected();
    }
}
