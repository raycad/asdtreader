package com.seedotech.core;

import android.content.Context;
import android.graphics.Color;
import android.widget.ListView;
import android.widget.Toast;

import com.seedotech.R;
import com.seedotech.customviews.CustomImageButton;
import com.seedotech.customviews.RssFeedListViewAdapter;
import com.seedotech.customviews.SdtView;

public class RssFeedListView implements SdtView {
	
	private RssFeedListActivity			m_rssFeedListActivity 		= null;
	private ListView					m_rssFeedListView			= null;
	private RssFeedListViewAdapter		m_rssFeedListViewAdapter	= null;
	
	private CustomImageButton			m_modeButton				= null;
	
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
    	
    	m_modeButton = (CustomImageButton) m_rssFeedListActivity.findViewById(R.id.modeButton);
    	//m_modeButton.setBackgroundId(R.drawable.gray_gradient_image_button);
    	m_modeButton.setImageId(R.drawable.view_icon);
    	m_modeButton.setText("Raycad");
    	m_modeButton.setTextColor(Color.BLUE);
    	
    	m_modeButton.setCallback(new CustomImageButton.Callback() {
			@Override
			public void onClicked() {
				String text = String.format("%s button was clicked!", m_modeButton.getText());
				Context context = m_rssFeedListActivity.getApplicationContext();
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		});
    	
		return true;
	}

	@Override
	public void retranslateUI() {
		
	}

	@Override
	public void updateView() {
	}
}
