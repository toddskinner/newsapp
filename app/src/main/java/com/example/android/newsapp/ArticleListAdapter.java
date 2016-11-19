package com.example.android.newsapp;

/**
 * Created by toddskinner on 11/18/16.
 */

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ArticleListAdapter extends ArrayAdapter<Article> {

    private Context mCon;

    public ArticleListAdapter(Activity context, ArrayList<Article> articles){
        super(context, 0, articles);
        mCon = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article, parent, false);
        }

        final Article currentArticle = getItem(position);

        TextView articleTitleTextView = (TextView) convertView.findViewById(R.id.article_title);
        String articleTitle = currentArticle.getWebTitle();
        articleTitleTextView.setText(articleTitle);

        TextView sectionNameTextView = (TextView) convertView.findViewById(R.id.section_name);
        String sectionName = currentArticle.getSectionName();
        sectionNameTextView.setText(sectionName);

        TextView publicationDateTextView = (TextView) convertView.findViewById(R.id.publication_date);
        String publicationDate = currentArticle.getWebPublicationDate();
        publicationDateTextView.setText(publicationDate);

        return convertView;
    }
}
