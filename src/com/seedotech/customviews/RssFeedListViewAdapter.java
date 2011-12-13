package com.seedotech.customviews;

import java.util.ArrayList;

import com.seedotech.models.RssFeed;
import com.seedotech.models.RssFeedModel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.seedotech.R;

public class RssFeedListViewAdapter extends BaseAdapter {
	private Activity 		m_activity;
	private LayoutInflater 	m_inflater;
	private	RssFeedModel 	m_rssFeedModel;

	public RssFeedListViewHolder	m_rssFeedListViewHolder = new RssFeedListViewHolder();
	
	public RssFeedListViewAdapter(Activity activity, final RssFeedModel rssFeedModel) {
		super();

		this.m_activity 	= activity;
		this.m_rssFeedModel	= rssFeedModel;

		this.m_inflater 	= (LayoutInflater) m_activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return m_rssFeedModel.count();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public static class ViewHolder {
		ImageView		m_rssFeedImageView;
		RssFeedItem 	m_rssFeedItem;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		try {
			if(convertView == null) {
				convertView = new RssFeedItem(m_activity, null);
				holder = new ViewHolder();
				holder.m_rssFeedItem 		= (RssFeedItem) convertView;
				holder.m_rssFeedImageView 	= (ImageView) 	convertView.findViewById(R.id.rssFeedThumbnailImageView);
				
				convertView.setTag(holder);
			} else
				holder = (ViewHolder)convertView.getTag();

			if (m_rssFeedModel != null) {
				// Get the current RSS feed
				RssFeed rssFeed = m_rssFeedModel.getRssFeedAtIndex(position);
				if (rssFeed != null && convertView != null) {
					holder.m_rssFeedItem.setRssFeed(rssFeed);
					holder.m_rssFeedItem.setPosition(position);
					holder.m_rssFeedItem.setRssFeedListViewAdapter(this);
				}
			}
			
			// Set background color
			if (position%2 == 0)
				holder.m_rssFeedItem.setBackgroundColor(Color.argb(255, 250, 255, 240));
			else 
				holder.m_rssFeedItem.setBackgroundColor(Color.argb(255, 200, 200, 200));
			
			int imageId;
			if (position%3 == 0) 
				imageId = R.drawable.rss_yellow;
			else if (position%3 == 1)
				imageId = R.drawable.rss_green;
			else 
				imageId = R.drawable.rss_blue;
			
			holder.m_rssFeedImageView.setImageDrawable(m_activity.getResources().getDrawable(imageId));
			
			// Update view
			holder.m_rssFeedItem.updateView();			
		} catch (Exception e) {
			// Show errors
			String text = e.toString();
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(m_activity, text, duration);
			toast.show();
		}

		return convertView;
	}
}
