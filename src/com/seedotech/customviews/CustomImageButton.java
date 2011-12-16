package com.seedotech.customviews;

import com.seedotech.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomImageButton extends LinearLayout
{
	public interface Callback {
		public void onClicked();
	}

	private ImageView 		m_imageView			= null;
	private TextView		m_textView			= null;
	private LinearLayout	m_backgroundLayout	= null;
	private Callback		m_callback 			= null;

	public void setCallback(Callback callback) {
		m_callback = callback;
	}

	public CustomImageButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.image_button_item, this);

		initialize();
	}

	protected boolean initialize() {
		// Initialize UI
		initUI();

		return true;
	}

	protected boolean initUI()	{	
		m_backgroundLayout	= (LinearLayout) 	findViewById(R.id.backgroundLayout);
		m_imageView			= (ImageView) 		findViewById(R.id.imageView);
		m_textView			= (TextView) 		findViewById(R.id.textView);

		m_backgroundLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onClicked();
			}
		});

		m_imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onClicked();
			}
		});

		m_textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onClicked();
			}
		});

		return true;
	}	

	protected void onClicked() {
		if (m_callback != null)
			m_callback.onClicked();
	}

	public void setBackgroundColor(final int bkgColor) {
		m_backgroundLayout.setBackgroundColor(bkgColor);
	}

	public void setBackgroundId(final int bkgId) {
		Drawable d = this.getResources().getDrawable(bkgId);
		if (d != null) {
			m_backgroundLayout.setBackgroundDrawable(d);
		}
	}

	public void setImageId(final int imageId) {
		Drawable d = this.getResources().getDrawable(imageId);
		if (d != null) 
			m_imageView.setImageDrawable(d);
	}
	
	public void setText(final String text) {
		m_textView.setText(text);
	}
	
	public String getText() {
		return m_textView.getText().toString();
	}
	
	public void setTextColor(final int textColor) {
		m_textView.setTextColor(textColor);
	}
}
