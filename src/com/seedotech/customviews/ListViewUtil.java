package com.seedotech.customviews;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ListViewUtil {
	// Current data
	private ImageView	m_currentDeleteIndicatorImageView 	= null;
	private Button		m_currentDeleteButton 				= null;
	private int			m_currentRow 						= -1;	// The position that the delete button is shown at

	// Last data
	private ImageView	m_lastDeleteIndicatorImageView 		= null;
	private Button		m_lastDeleteButton 					= null;
	private int			m_lastRow 							= -1;	// The position that the delete button is shown at

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
		startDeleteIndicatorAnimation();

		if (m_currentDeleteButton == m_lastDeleteButton) {
			int visible = m_currentDeleteButton.getVisibility();
			boolean show = true;
			if (visible == View.VISIBLE)
				show = false;
			showCurrentDeleteButton(show);
		} else {		
			// Hide the last delete button
			showLastDeleteButton(false);
			
			// Show the current delete button
			showCurrentDeleteButton(true);
		}
	}

	public void onRowClicked() {
		// Hide the shown delete button
		if (m_currentDeleteButton != null)
			m_currentDeleteButton.setVisibility(View.GONE);

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
			m_currentDeleteButton.setVisibility(View.VISIBLE);
		} else {
			m_currentDeleteButton.setVisibility(View.GONE);
			// Reset data
			setDefaultData();
		}
	}

	public void showLastDeleteButton(final boolean show) {
		if (m_lastDeleteButton == null)
			return;

		if (show == true) {
			m_lastDeleteButton.setVisibility(View.VISIBLE);
		} else {
			m_lastDeleteButton.setVisibility(View.GONE);
		}
	}

	private void startDeleteIndicatorAnimation() {

	}
}
