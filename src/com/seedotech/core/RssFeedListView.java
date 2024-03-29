package com.seedotech.core;

import android.content.Context;
import android.graphics.Color;
import android.widget.ListView;
import android.widget.Toast;

import com.seedotech.R;
import com.seedotech.common.Global;
import com.seedotech.customviews.CustomButton;
import com.seedotech.customviews.CustomImageButton;
import com.seedotech.customviews.RssFeedListViewAdapter;
import com.seedotech.customviews.SdtView;
import com.seedotech.customviews.TabBarItem;

public class RssFeedListView implements SdtView {
	
	private RssFeedListActivity			m_rssFeedListActivity 		= null;
	private ListView					m_rssFeedListView			= null;
	private RssFeedListViewAdapter		m_rssFeedListViewAdapter	= null;
	
	private CustomImageButton			m_newButton					= null;
	private CustomImageButton			m_modeButton				= null;
	private CustomImageButton			m_searchButton				= null;
	
	private TabBarItem					m_tabBarItem				= null;
	
	public RssFeedListView(RssFeedListActivity rssFeedListActivity) {
		m_rssFeedListActivity = rssFeedListActivity;
	}
	
	@Override
	public boolean initUI() {
		/* get the list view from the layout */
		m_rssFeedListView = (ListView) m_rssFeedListActivity.findViewById(R.id.rssFeedListView);
    	
    	/* create an adapter */
    	this.m_rssFeedListViewAdapter = new RssFeedListViewAdapter(m_rssFeedListActivity, m_rssFeedListActivity.getFilterRssFeedModel());
    	
    	// set the list's adapter
    	m_rssFeedListView.setAdapter(this.m_rssFeedListViewAdapter);
    	
    	m_newButton = (CustomImageButton) m_rssFeedListActivity.findViewById(R.id.newButton);
    	m_newButton.setBackgroundId(R.drawable.gray_image_button);
    	m_newButton.setImageId(R.drawable.rss_feed_add);
    	m_newButton.setText("New");
    	m_newButton.setTextColor(Color.parseColor("#184357"));
    	m_newButton.setTextStyle(android.graphics.Typeface.BOLD);
    	
    	m_modeButton = (CustomImageButton) m_rssFeedListActivity.findViewById(R.id.modeButton);
    	m_modeButton.setBackgroundId(R.drawable.gray_image_button);
    	m_modeButton.setImageId(R.drawable.view_icon);
    	m_modeButton.setText("View Mode");
    	m_modeButton.setTextColor(Color.parseColor("#184357"));
    	m_modeButton.setTextStyle(android.graphics.Typeface.BOLD);
    	
    	m_modeButton.setCallback(new CustomImageButton.Callback() {
			@Override
			public void onClicked() {
				/*String text = String.format("%s button was clicked!", m_modeButton.getText());
				Context context = m_rssFeedListActivity.getApplicationContext();
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();*/
			}
		});
    	
    	m_searchButton = (CustomImageButton) m_rssFeedListActivity.findViewById(R.id.searchButton);
    	m_searchButton.setBackgroundId(R.drawable.gray_image_button);
    	m_searchButton.setImageId(R.drawable.search);
    	m_searchButton.setText("By Title");
    	m_searchButton.setTextColor(Color.parseColor("#184357"));
    	m_searchButton.setTextStyle(android.graphics.Typeface.BOLD);
    	
    	m_searchButton.setCallback(new CustomImageButton.Callback() {
			@Override
			public void onClicked() {
				/*String text = String.format("%s button was clicked!", m_searchButton.getText());
				Context context = m_rssFeedListActivity.getApplicationContext();
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();*/
			}
		});
    	
    	m_tabBarItem = (TabBarItem) m_rssFeedListActivity.findViewById(R.id.tabBarItem);
    	m_tabBarItem.setCallback(new TabBarItem.Callback() {
			
			@Override
			public void onClicked(CustomButton buttonClicked) {
				String text = String.format("Button %s; Id = %d", buttonClicked.getText(),
						m_tabBarItem.getButtonKeyFromButton(buttonClicked));
				Context context = m_rssFeedListActivity.getApplicationContext();
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		});
    	
    	// Create tab bar
    	CustomButton button = new CustomButton(m_rssFeedListActivity, null);
    	button.setText("Rss Feeds");
    	button.setTextColor(Color.WHITE);
    	m_tabBarItem.addTabBarButton(Global.RSS_FEED_LIST_VIEW_ID, button);
    	
    	button = new CustomButton(m_rssFeedListActivity, null);
    	button.setText("Rss Category");
    	button.setTextColor(Color.WHITE);
    	m_tabBarItem.addTabBarButton(Global.RSS_CATEGORY_LIST_VIEW_ID, button);
    	
    	m_tabBarItem.setSelectedButton(Global.RSS_FEED_LIST_VIEW_ID);
		return true;
	}

	@Override
	public void retranslateUI() {
		
	}

	@Override
	public void updateView() {
	}
}
