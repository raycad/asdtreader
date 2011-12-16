package com.seedotech.customviews;

import com.seedotech.R;
import com.seedotech.customviews.CustomButton.ButtonState;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

public class TabBarButton extends CustomButton {
	public TabBarButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		m_selectedDrawable 	= context.getResources().getDrawable(R.drawable.green_button);
		m_normalDrawable 	= context.getResources().getDrawable(R.drawable.black_button);
		
		setButtonState(ButtonState.Normal);
	}
	
	public void setButtonState(final ButtonState state) {
		if (m_buttonState == state)
			return;
		
		m_buttonState = state;
		if (state == ButtonState.Selected) {
			if (m_selectedDrawable != null)
				this.setBackgroundDrawable(m_selectedDrawable);
			//this.setTextColor(Color.GRAY);
		} else {
			if (m_normalDrawable != null)
				this.setBackgroundDrawable(m_normalDrawable);
			//this.setTextColor(Color.WHITE);
		}
	}
}
