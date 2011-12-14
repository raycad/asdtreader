package com.seedotech.customviews;

import java.util.ArrayList;

import com.seedotech.R;
import com.seedotech.models.RssFeed;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RssFeedItem extends RelativeLayout {
	private static final int		RATE_BUTTON_NUMBER	= 5;
	private ArrayList<RateButton> 	m_rateButtonList 	= new ArrayList<RateButton>();
	private LinearLayout 			m_rateButtonLayout	= null;

	private int						m_currentRateValue	= 0;
	private RssFeed					m_rssFeed			= null;

	private TextView				m_rssFeedTitleTextView;
	private TextView				m_rssCategoryTextView;

	private Button					m_deleteButton;

	private ImageView				m_deleteIndicatorImageView;

	private RssFeedListViewAdapter	m_rssFeedListViewAdapter;
	private int						m_row;

	public RssFeedItem(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.rss_feed_item, this);
		m_rateButtonLayout = (LinearLayout) view.findViewById(R.id.rateButtonsLayout);

		initialize();
	}

	public void setRssFeedListViewAdapter(final RssFeedListViewAdapter rssFeedListViewAdapter) {
		m_rssFeedListViewAdapter = rssFeedListViewAdapter;
	}

	public void setRow(int row) {
		m_row = row;
	}

	private boolean initialize() {
		m_rssFeedTitleTextView 		= (TextView) 	findViewById(R.id.rssFeedTitleTextView);
		m_rssCategoryTextView 		= (TextView) 	findViewById(R.id.rssCategoryTextView);

		m_deleteButton		 		= (Button) 		findViewById(R.id.deleteButton);
		m_deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (m_rssFeedListViewAdapter == null)
					return;
				
				m_rssFeedListViewAdapter.deleteItemAtRow(m_row);
			}
		});

		m_deleteIndicatorImageView	= (ImageView) 	findViewById(R.id.deleteIndicatorImageView);
		m_deleteIndicatorImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (m_rssFeedListViewAdapter == null)
					return;
				
				m_rssFeedListViewAdapter.getListViewUtil().updateData(m_deleteIndicatorImageView, 
						m_deleteButton, m_row);
				m_rssFeedListViewAdapter.getListViewUtil().onDeleteIndicatorClicked();
			}
		});

		this.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				m_rssFeedListViewAdapter.getListViewUtil().onRowClicked();
			}
		});

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

		// Hide the delete button
		m_deleteButton.setVisibility(View.GONE);

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

		// Update rate to the model
		if (m_rssFeed != null) 
			m_rssFeed.setRate(m_currentRateValue);
	}

	public void updateView() {
		// Reset the current rate value so that the function does not set wrong value
		int currentRateValue = m_currentRateValue;
		m_currentRateValue = -1;

		if (m_rssFeed != null) {
			m_rssFeedTitleTextView.setText(m_rssFeed.getTitle());
			setRateValue(m_rssFeed.getRate());
		}
		
		// Check update the current row
		if ((m_rssFeedListViewAdapter != null) &&
				(m_rssFeedListViewAdapter.getListViewUtil().getCurrentRow() == m_row)) {
			// Update current data since the view data has been changed
			m_rssFeedListViewAdapter.getListViewUtil().updateData(m_deleteIndicatorImageView,
					m_deleteButton, m_row);
			
			// Set indicator
			m_rssFeedListViewAdapter.getListViewUtil().setDefaultDeleteIndicator(m_deleteIndicatorImageView);
			m_rssFeedListViewAdapter.getListViewUtil().startRotationAnimation(m_deleteIndicatorImageView, true);
			
			showDeleteButton(true);
		} else {
			m_rssFeedListViewAdapter.getListViewUtil().setDefaultDeleteIndicator(m_deleteIndicatorImageView);
			showDeleteButton(false);
		}
	}
	
	public void showDeleteButton(final boolean show) {
		if (show == true) {
			m_deleteButton.setVisibility(View.VISIBLE);
		} else {
			m_deleteButton.setVisibility(View.GONE);
		}
	}
}
