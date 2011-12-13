package com.seedotech.customviews;

import com.seedotech.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class RssFeedItem extends RelativeLayout {

	public RssFeedItem(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.rss_feed_item, this);
		
		initialize();
	}

	public boolean initialize() {
	    
        return true;
	}
}
