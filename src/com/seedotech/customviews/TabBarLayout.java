/**
 * File TitleBarLayout.java
 *
 * Brief 
 *
 * Copyright of Nomovok Ltd. All rights reserved.
 *
 * Contact: info@nomovok.com
 *
 * Author Nguyen Truong Duong <nguyentruong.duong@nomovok.com> 
 *
 * Created at 12:09:32 PM Dec 6, 2011
 * 
 * Any other legal text to be defined later
 */
package com.seedotech.customviews;

import com.seedotech.R;
import com.seedotech.common.Global;
import com.seedotech.common.StringKey;
import com.seedotech.controllers.SdtController;
import com.seedotech.customviews.SdtView;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class TabBarLayout extends RelativeLayout implements SdtView
{
	private Button 				m_rssFeedListButton		= null;
	private Button 				m_rssCategoryListButton	= null;
	
	private TabBarCallback		m_tabBarCallback 		= null;
	
	public void setTabBarCallback(TabBarCallback tabBarCallback) {
		m_tabBarCallback = tabBarCallback;
	}
	
	public TabBarLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.tab_bar_layout_item, this);
		
		initialize();
	}

	public boolean initialize() {
		// Initialize UI
		initUI();
		
		// Re-translate UI
	    retranslateUI();
	    
	    // Set login status
	    updateView();
	    
        return true;
	}
	
	@Override
	public boolean initUI()	{	
		m_rssFeedListButton	= (Button) findViewById(R.id.rssFeedListButton);
		m_rssFeedListButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			}
	    });
		
		m_rssCategoryListButton	= (Button) findViewById(R.id.rssCategoryListButton);
		m_rssCategoryListButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			}
	    });
		
		return true;
	}	
	
	@Override
	public void retranslateUI() {	
		String key = "", text = "";
		SdtController sdtController = SdtController.getInstance();
		
		key = StringKey.RSS_FEED_LIST_STRING_KEY;
		text = sdtController.getLanguageUtil().getTextByKey(key);
		m_rssFeedListButton.setText(text);
		
		key = StringKey.RSS_CATEGORY_LIST_STRING_KEY;
		text = sdtController.getLanguageUtil().getTextByKey(key);
		m_rssCategoryListButton.setText(text);
	}
	
	@Override
	public void updateView() {
	}
}

