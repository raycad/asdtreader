package com.seedotech.core;

import com.seedotech.R;

import android.app.Activity;
import android.os.Bundle;

public class RssFeedListActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_feed_list_view);
    }
}