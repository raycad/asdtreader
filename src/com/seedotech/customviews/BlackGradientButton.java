package com.seedotech.customviews;

import android.content.Context;
import android.util.AttributeSet;

import com.seedotech.R;

public class BlackGradientButton extends CustomButton {
	public BlackGradientButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		m_selectedDrawable 	= context.getResources().getDrawable(R.drawable.button_pressed);
		m_normalDrawable 	= context.getResources().getDrawable(R.drawable.black_gradient_button);
		
		setButtonState(ButtonState.Normal);
	}
}