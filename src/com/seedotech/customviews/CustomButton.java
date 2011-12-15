package com.seedotech.customviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {
	protected ButtonState	m_buttonState 		= ButtonState.None;
	
	protected Drawable 		m_selectedDrawable 	= null;
	protected Drawable 		m_normalDrawable 	= null;

	public enum ButtonState {
		None,
		Normal,
		Selected		
	}
	
	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setButtonState(final ButtonState state) {
		if (m_buttonState == state)
			return;
		
		m_buttonState = state;
		if (state == ButtonState.Selected) {
			if (m_selectedDrawable != null)
				this.setBackgroundDrawable(m_selectedDrawable);
		} else {
			if (m_normalDrawable != null)
				this.setBackgroundDrawable(m_normalDrawable);
		}
	}
}
