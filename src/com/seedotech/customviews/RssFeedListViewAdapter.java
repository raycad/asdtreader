package com.seedotech.customviews;

import java.util.ArrayList;

import com.seedotech.models.RssFeed;
import com.seedotech.models.RssFeedModel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.seedotech.R;

public class RssFeedListViewAdapter extends BaseAdapter {
	private Activity 		m_activity;
	private LayoutInflater 	m_inflater;
	private	RssFeedModel 	m_rssFeedModel;

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
		ImageView 	m_rssFeedImageView;
		TextView	m_rssFeedTitleTextView;
		TextView	m_rssCategoryTextView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		try {
			if(convertView == null) {
				holder 		= new ViewHolder();
				convertView = new RssFeedItem(m_activity, null);
				//convertView = m_inflater.inflate(R.layout.vote_result_item, null);

				holder.m_rssFeedImageView 			= (ImageView) 	convertView.findViewById(R.id.thumbnailImageView);
				holder.m_rssFeedTitleTextView 		= (TextView) 	convertView.findViewById(R.id.rssFeedTitleTextView);
				holder.m_rssCategoryTextView 		= (TextView) 	convertView.findViewById(R.id.rssCategoryTextView);

				convertView.setTag(holder);
			} else
				holder = (ViewHolder)convertView.getTag();

			// Get the current RSS feed
			RssFeed rssFeed = m_rssFeedModel.getRssFeedAtIndex(position);
			if (rssFeed != null) {
				holder.m_rssFeedTitleTextView.setText(rssFeed.getTitle());
				holder.m_rssCategoryTextView.setText(rssFeed.getRssCategory().getTitle());
			}
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
