package com.seedotech.customviews;

import com.seedotech.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

public class TabBarButton extends Button {
	public TabBarButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		m_selectedDrawable 	= context.getResources().getDrawable(R.drawable.green_button);
		m_normalDrawable 	= context.getResources().getDrawable(R.drawable.black_button);
		
		setButtonState(ButtonState.Normal);
	}

	private Drawable m_selectedDrawable 	= null;
	private Drawable m_normalDrawable 		= null;

	public enum ButtonState {
		Normal,
		Selected		
	}
	
	public void setButtonState(final ButtonState state) {
		if (state == ButtonState.Selected)
			this.setBackgroundDrawable(m_selectedDrawable);
		else
			this.setBackgroundDrawable(m_normalDrawable);
	}
}
