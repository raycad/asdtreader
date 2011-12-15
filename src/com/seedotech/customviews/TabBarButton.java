package com.seedotech.customviews;

import com.seedotech.R;

import android.content.Context;
import android.util.AttributeSet;

public class TabBarButton extends CustomButton {
	public TabBarButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		m_selectedDrawable 	= context.getResources().getDrawable(R.drawable.green_button);
		m_normalDrawable 	= context.getResources().getDrawable(R.drawable.black_button);
		
		setButtonState(ButtonState.Normal);
	}
}
