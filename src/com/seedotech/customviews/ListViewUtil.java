package com.seedotech.customviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import com.seedotech.R;
import com.seedotech.utils.SdtAnimationUtils;

public class ListViewUtil {
	// Current data
	private ImageView	m_currentDeleteIndicatorImageView 	= null;
	private Button		m_currentDeleteButton 				= null;
	private int			m_currentRow 						= -1;	// The position that the delete button is shown at

	// Last data
	private ImageView	m_lastDeleteIndicatorImageView 		= null;
	private Button		m_lastDeleteButton 					= null;
	private int			m_lastRow 							= -1;	// The position that the delete button is shown at

	private Context		m_context;
	private Drawable	m_defaultDeleteIndicatorDrawable	= null;

	public ListViewUtil(final Context context) {
		m_context = context;
		m_defaultDeleteIndicatorDrawable = m_context.getResources().getDrawable(R.drawable.remove_icon);
	}

	public int getCurrentRow() {
		return m_currentRow;
	}

	public void updateData(final ImageView currentDeleteIndicatorImageView, final Button currentDeleteButton,
			final int currentRow) {
		// Update the last data
		m_lastDeleteIndicatorImageView		= m_currentDeleteIndicatorImageView;
		m_lastDeleteButton					= m_currentDeleteButton;
		m_lastRow							= m_currentRow;

		// Update the current data
		m_currentDeleteIndicatorImageView	= currentDeleteIndicatorImageView;
		m_currentDeleteButton				= currentDeleteButton;
		m_currentRow						= currentRow;
	}

	public void onDeleteIndicatorClicked() {
		if (m_currentDeleteButton == m_lastDeleteButton) {
			if (m_currentDeleteButton == null)
				return;

			int visible = m_currentDeleteButton.getVisibility();
			boolean show = true;
			boolean clockwise = false;
			if (visible == View.VISIBLE) {
				show = false;
				clockwise = false;
			} else {
				show = true;
				clockwise = true;
			}

			startRotationAnimation(m_currentDeleteIndicatorImageView, clockwise);

			showCurrentDeleteButton(show);
		} else {		
			setDefaultDeleteIndicator(m_lastDeleteIndicatorImageView);
			// Hide the last delete button
			showLastDeleteButton(false);

			startRotationAnimation(m_currentDeleteIndicatorImageView, true);
			// Show the current delete button
			showCurrentDeleteButton(true);
		}
	}

	public void onRowClicked() {
		// Hide the shown delete button
		if (m_currentDeleteButton != null)
			m_currentDeleteButton.setVisibility(View.GONE);

		setDefaultDeleteIndicator(m_currentDeleteIndicatorImageView);
		
		setDefaultData();
	}

	public void setDefaultData() {
		m_currentDeleteIndicatorImageView 	= null;
		m_currentDeleteButton 				= null;
		m_currentRow 						= -1;
	}

	public void showCurrentDeleteButton(final boolean show) {
		if (m_currentDeleteButton == null)
			return;

		if (show == true) {
			SdtAnimationUtils.setViewAnimationSlideFromRightToLeft(m_currentDeleteButton, m_currentDeleteButton.getContext());
			m_currentDeleteButton.setVisibility(View.VISIBLE);
		} else {
			SdtAnimationUtils.setViewAnimationSlideFromLeftToRight(m_currentDeleteButton, m_currentDeleteButton.getContext());
			//m_currentDeleteButton.setVisibility(View.GONE);
			// Reset data
			setDefaultData();
		}
	}

	public void showLastDeleteButton(final boolean show) {
		if (m_lastDeleteButton == null)
			return;

		if (show == true) {
			SdtAnimationUtils.setViewAnimationSlideFromRightToLeft(m_lastDeleteButton, m_lastDeleteButton.getContext());
			m_lastDeleteButton.setVisibility(View.VISIBLE);
		} else {
			SdtAnimationUtils.setViewAnimationSlideFromLeftToRight(m_lastDeleteButton, m_lastDeleteButton.getContext());
			//m_lastDeleteButton.setVisibility(View.GONE);
		}
	}

	public void setDefaultDeleteIndicator(final ImageView imageView) {
		if (imageView == null)
			return;
		// Clear animation
		imageView.clearAnimation();
		
		imageView.setImageDrawable(m_defaultDeleteIndicatorDrawable);
	}
	
	public void startRotationAnimation(final ImageView imageView, boolean clockwise) {
		if (imageView == null)
			return;

		SdtAnimationUtils.startRotationAnimation(imageView, imageView.getContext(), clockwise);
	}
}
