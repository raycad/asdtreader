package com.seedotech.core;

import android.widget.ListView;

import com.seedotech.R;
import com.seedotech.customviews.RssFeedListViewAdapter;
import com.seedotech.customviews.SdtView;

public class RssFeedListView implements SdtView {
	
	private RssFeedListActivity			m_rssFeedListActivity 		= null;
	private ListView					m_rssFeedListView			= null;
	private RssFeedListViewAdapter		m_rssFeedListViewAdapter	= null;
	
	public RssFeedListView(RssFeedListActivity rssFeedListActivity) {
		m_rssFeedListActivity = rssFeedListActivity;
	}
	
	@Override
	public boolean initUI() {
		/* get the list view from the layout */
		m_rssFeedListView = (ListView) m_rssFeedListActivity.findViewById(R.id.rssFeedListView);
    	
    	/* create an adapter */
    	this.m_rssFeedListViewAdapter = new RssFeedListViewAdapter(m_rssFeedListActivity, null);
    	
    	// set the list's adapter
    	m_rssFeedListView.setAdapter(this.m_rssFeedListViewAdapter);
    	
		return true;
	}

	@Override
	public void retranslateUI() {
		
	}

	@Override
	public void updateView() {
	}
}
