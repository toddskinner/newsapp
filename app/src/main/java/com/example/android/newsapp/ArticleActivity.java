package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class ArticleActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>>{

    public static final String LOG_TAG = ArticleActivity.class.getName();
    private ArticleListAdapter adapter;
    private TextView emptyTextView;
    private ProgressBar progressBar;

    /** URL for book data from the Google Books API */
    private String GUARDIAN_API_REQUEST_URL =
            "http://content.guardianapis.com/search?&api-key=test";

    /** to be combined with the search term provided by the editText box *//*
    private static final String GOOGLE_BOOKS_REQUEST_URL_PART1 =
            "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String GOOGLE_BOOKS_REQUEST_URL_PART2 =
            "&maxResults=10";*/

    /**
     * Constant value for the loader ID.
     */
    private static final int ARTICLE_LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articles_activity);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ListView listView = (ListView) findViewById(R.id.list);
        emptyTextView = (TextView) findViewById(R.id.empty_list);
        listView.setEmptyView(emptyTextView);
        progressBar = (ProgressBar) findViewById(R.id.loading_indicator);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //if there is a network connection, fetch data
        if(networkInfo != null && networkInfo.isConnected()){
            //get a reference to the LoaderManager, in order to interact with loaders
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText(R.string.no_connection_message);
        }

        adapter = new ArticleListAdapter(this, new ArrayList<Article>());

        listView.setAdapter(adapter);

        /*Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                counter++;
                TextView searchText = (TextView) findViewById(R.id.search_editText);
                String searchTerm = searchText.getText().toString();
                GOOGLE_BOOKS_API_REQUEST_URL = GOOGLE_BOOKS_REQUEST_URL_PART1 + searchTerm + GOOGLE_BOOKS_REQUEST_URL_PART2;
                getLoaderManager().restartLoader(ARTICLE_LOADER_ID, null, ArticleActivity.this);
                searchText.setText("");
            }
        });*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                // Find the current book that was clicked on
                Article currentArticle = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri articleUri = Uri.parse(currentArticle.getWebUrl());

                // Create a new intent to view the book URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {
        //Log.e("oncreate","Run onCreateLoader");
        return new ArticleLoader(this, GUARDIAN_API_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> data) {
        adapter.clear();
        progressBar.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
            Log.e("onloadfinished","Run onLoadFinished");
        }
        emptyTextView.setText(R.string.empty_list);
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        adapter.clear();
        //Log.e("onreset","Run onResetLoader");
    }
}
