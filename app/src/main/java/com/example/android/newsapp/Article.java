package com.example.android.newsapp;

import static android.R.attr.author;
import static android.R.attr.description;

/**
 * Created by toddskinner on 11/18/16.
 */

public class Article {
    private String mSectionName;
    private String mWebPublicationDate;
    private String mWebTitle;
    private String mWebUrl;

    public Article (String sectionName, String webPublicationDate, String webTitle, String webUrl){
        mSectionName = sectionName;
        mWebPublicationDate = webPublicationDate;
        mWebTitle = webTitle;
        mWebUrl = webUrl;
    }

    public String getSectionName(){
        return mSectionName;
    }

    //public String getAuthor(){
      //  String authorLessQuotations = mAuthor.replace("\"", " ");
      //  String authorLessCommas = authorLessQuotations.replace(",", "&");
      //  return "By" + " " + authorLessCommas.substring(2 , authorLessCommas.length()-2);
    //}

    public String getWebPublicationDate(){
        return mWebPublicationDate;
    }

    public String getWebTitle() { return mWebTitle; }

    public String getWebUrl() { return mWebUrl; }
}
