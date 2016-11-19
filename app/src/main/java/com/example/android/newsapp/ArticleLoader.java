package com.example.android.newsapp;

/**
 * Created by toddskinner on 11/18/16.
 */

import android.content.Context;
import android.content.AsyncTaskLoader;
import java.util.List;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.util.Log;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {
    private String mUrl;

    /**
     * Constructs a new {@link ArticleLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public ArticleLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    //we override the onStartLoading() method to call forceLoad() which is a required step to actually trigger the loadInBackground() method to execute.
    @Override
    protected void onStartLoading() {
        //Log.e("onstart","Run onStartLoader");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Article> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        //Log.e("onloadbackground","Run onLoadInBackground");
        return QueryUtils.fetchArticlesData(mUrl);
    }
}
