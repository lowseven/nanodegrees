package com.example.lowseven.guardiannewsapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public  abstract class EndlessScrollerListener extends RecyclerView.OnScrollListener {
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold;
    // The current offset index of data you have loaded
    private int currentPage;
    // The total number of items in the data set after the last load
    private int previousTotalItemCount;
    // True if we are still waiting for the last set of data to load.
    private boolean loading;
    // Sets the starting page index
    private int startingPageIndex;

    private LinearLayoutManager layoutManager;

    public EndlessScrollerListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        this.visibleThreshold = 5;
        this.currentPage = 0;
        this.previousTotalItemCount = 0;
        this.startingPageIndex = 0;
        this.loading = false;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int itemCount = layoutManager.getItemCount();
        int lastItemVisible = layoutManager.findLastVisibleItemPosition();

        if(!loading && itemCount < (visibleThreshold + lastItemVisible)) {
            //load more data
            loading = true;
            onLoadMore(currentPage, itemCount, recyclerView);
        }

        if(loading && itemCount > previousTotalItemCount) {
            loading = false;
            previousTotalItemCount = itemCount;
        }
    }

    public abstract void onLoadMore(int currentPage, int totalItemCount, RecyclerView view);
}
