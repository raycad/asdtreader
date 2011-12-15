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

import java.util.ArrayList;

import com.seedotech.R;
import com.seedotech.common.Global;
import com.seedotech.common.StringKey;
import com.seedotech.controllers.SdtController;
import com.seedotech.customviews.SdtView;
import com.seedotech.customviews.TabBarButton.ButtonState;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class TabBarLayout extends RelativeLayout implements SdtView
{
	private TabBarButton 			m_rssFeedListButton		= null;
	private TabBarButton			m_rssCategoryListButton	= null;
	
	private TabBarCallback			m_tabBarCallback 		= null;
	
	private ArrayList<TabBarButton> m_tabBarButtonList		= new ArrayList<TabBarButton>();
	
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
	    //retranslateUI();
	    
	    // Set login status
	    updateView();
	    
	    // Set RSS Feed be selected
	    m_rssFeedListButton.setButtonState(ButtonState.Selected);
	    
        return true;
	}
	
	@Override
	public boolean initUI()	{	
		m_rssFeedListButton		= (TabBarButton) findViewById(R.id.rssFeedListButton);
		m_rssCategoryListButton	= (TabBarButton) findViewById(R.id.rssCategoryListButton);
		
		m_tabBarButtonList.add(m_rssFeedListButton);
		m_tabBarButtonList.add(m_rssCategoryListButton);
		
		m_rssFeedListButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onButtonClicked(m_rssFeedListButton);
			}
	    });
		
		m_rssCategoryListButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onButtonClicked(m_rssCategoryListButton);
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
	
	protected void onButtonClicked(final TabBarButton button) {
		for (int i = 0; i < m_tabBarButtonList.size(); i++) {
			TabBarButton tbb = m_tabBarButtonList.get(i);
			if (button == tbb) 
				tbb.setButtonState(ButtonState.Selected);
			else 
				tbb.setButtonState(ButtonState.Normal);
		}
	}
}

