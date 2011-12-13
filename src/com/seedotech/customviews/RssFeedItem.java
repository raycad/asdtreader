package com.seedotech.customviews;

import java.util.ArrayList;

import com.seedotech.R;
import com.seedotech.models.RssFeed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RssFeedItem extends RelativeLayout {
	private static final int		RATE_BUTTON_NUMBER	= 5;
	private ArrayList<RateButton> 	m_rateButtonList 	= new ArrayList<RateButton>();
	private LinearLayout 			m_rateButtonLayout	= null;
	
	int								m_currentRateValue	= 0;
	RssFeed							m_rssFeed			= null;
	
	TextView						m_rssFeedTitleTextView;
	TextView						m_rssCategoryTextView;
	
	public RssFeedItem(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.rss_feed_item, this);
		m_rateButtonLayout = (LinearLayout) view.findViewById(R.id.rateButtonsLayout);
		
		initialize();
	}

	private boolean initialize() {
		m_rssFeedTitleTextView 		= (TextView) findViewById(R.id.rssFeedTitleTextView);
		m_rssCategoryTextView 		= (TextView) findViewById(R.id.rssCategoryTextView);

		// Create rate buttons
		for (int i = 0; i < RATE_BUTTON_NUMBER; i++) {
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LayoutParams.FILL_PARENT, 1.0f);
			lp.rightMargin 	= 8;
			
			RateButton rateButton = new RateButton(this.getContext(), RateButton.RateSize.Size24);
			rateButton.setData(i+1);
			rateButton.setLayoutParams(lp);
			m_rateButtonLayout.addView(rateButton);
			
			m_rateButtonList.add(rateButton);
			
			final int rateValue = rateButton.getData();
			rateButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					setRateValue(rateValue);
				}
		    });
		}
		
		m_currentRateValue = 0;
		setRateValue(m_currentRateValue);
		
        return true;
	}
	
	public void setRssFeed(final RssFeed rssFeed) {
		m_rssFeed = rssFeed;
	}
	
	public RssFeed getRssFeed() {
		return m_rssFeed;
	}
	
	public void setRateValue(int rateValue) {
		if (m_currentRateValue == rateValue) {
			// Set the current rateValue to 0
			m_currentRateValue = 0;
			for (int i = 0; i < m_rateButtonList.size(); i++) {
				RateButton rateButton = m_rateButtonList.get(i);
				rateButton.setRateState(RateButton.RateState.UnRate);
			}
		} else {
			m_currentRateValue = rateValue;
			for (int i = 0; i < rateValue; i++) {
				RateButton rateButton = m_rateButtonList.get(i);
				rateButton.setRateState(RateButton.RateState.Rate);
			}
			
			for (int i = rateValue; i < m_rateButtonList.size(); i++) {
				RateButton rateButton = m_rateButtonList.get(i);
				rateButton.setRateState(RateButton.RateState.UnRate);
			}
		}
	}
	
	public void updateView() {
		int currentRateValue = m_currentRateValue;
		m_currentRateValue = -1;
		setRateValue(currentRateValue);
	}
}
