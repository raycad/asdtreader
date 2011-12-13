package com.seedotech.customviews;

import com.seedotech.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class RateButton extends ImageView {
	public RateButton(Context context, RateButton.RateSize rateSize) {
		super(context);
		setRateSize(rateSize);
	}

	public enum RateState {
	    Rate, 
	    UnRate
	};

	public enum RateSize {
	    Size16,
	    Size24,
	    Size32, 
	    Size48
	} 
	
	private Drawable	m_rateDrawable		= null;
	private Drawable	m_unrateDrawable	= null;
	
	private int			m_data				= 0;
	
	public void setData(final int data) {
		m_data = data;
	}
	public final int getData() {
		return m_data;
	}
	
	public void setRateSize(RateSize rateSize) {
		int rateImageId, unrateImageId;
		if (rateSize == RateSize.Size24) {
			rateImageId = R.drawable.star_gold24;
			unrateImageId = R.drawable.star_white24;
	    } else if (rateSize == RateSize.Size32) {
	    	rateImageId = R.drawable.star_gold32;
	    	unrateImageId = R.drawable.star_white32;
	    } else if (rateSize == RateSize.Size48) {
	    	rateImageId = R.drawable.star_gold48;
	    	unrateImageId = R.drawable.star_white48;
	    } else {
	    	rateImageId = R.drawable.star_gold16;
	    	unrateImageId = R.drawable.star_white16;
	    }
		
		m_rateDrawable 		= this.getContext().getResources().getDrawable(rateImageId);
		m_unrateDrawable 	= this.getContext().getResources().getDrawable(unrateImageId);
	}
	
	public void setRateState(RateState rateState) {
		Drawable drawable = null;
		if (rateState == RateState.Rate)
			drawable = m_rateDrawable;
		else 
			drawable = m_unrateDrawable;
			
		if (drawable != null)
			this.setImageDrawable(drawable);
	}
}
