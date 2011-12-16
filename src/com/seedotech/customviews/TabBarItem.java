/**
 * File TabBarItem.java
 *
 * Brief 
 *
 * Copyright of Nomovok Ltd. All rights reserved.
 *
 * Contact: info@nomovok.com
 *
 * Author Nguyen Truong Duong <nguyentruong.duong@nomovok.com> 
 *
 * Created at 12:09:32 PM Dec 6, 2011
 * 
 * Any other legal text to be defined later
 */
package com.seedotech.customviews;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.seedotech.R;
import android.widget.LinearLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

public class TabBarItem extends LinearLayout
{
	private Callback				m_callback 				= null;
	
	private CustomButton			m_currentSelectButton	= null;
	
	private Map<Integer, CustomButton> m_tabBarButtonMap	= new HashMap<Integer, CustomButton>();
	
	protected LinearLayout	m_tabBarItemLayout				= null;
	
	public interface Callback {
		public void onClicked(final CustomButton buttonClicked);
	}
	
	public void setCallback(final Callback callback) {
		m_callback = callback;
	}
	
	public TabBarItem(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.tab_bar_layout_item, this);
		
		initialize();
	}

	public boolean initialize() {
		m_tabBarItemLayout = (LinearLayout) findViewById(R.id.tabBarItemLayout);
        return true;
	}
	
	public void updateView() {
		int totalTabBarButtons = m_tabBarButtonMap.size();
		float weight = 100/totalTabBarButtons;
		Iterator it = m_tabBarButtonMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry) it.next();
	        CustomButton tbb = (CustomButton) pairs.getValue();
	        LayoutParams params = (LinearLayout.LayoutParams) tbb.getLayoutParams();
	        params.width 	= 0;
	        params.height 	= LayoutParams.FILL_PARENT;
	        params.gravity	= Gravity.CENTER;	        
	        // update weight
	        params.weight 	= weight;
	        params.setMargins(1, 1, 1, 1);
	        tbb.setLayoutParams(params);	        
	    }
	}
	
	protected void onButtonClicked(final CustomButton buttonClicked) {
		if (m_currentSelectButton == buttonClicked)
			return;
		
		// Update the current selected button
		m_currentSelectButton = buttonClicked;
		
		setSelectedButton(buttonClicked);
		if (m_callback != null)
			m_callback.onClicked(buttonClicked);
	}
	
	public void addTabBarButton( final Integer key, final CustomButton button) {
		m_tabBarButtonMap.put(key, button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onButtonClicked(button);
			}
	    });
		
		// Reset button states 
		button.setNormalBackgroundId(R.drawable.black_button);
		button.setSelectedBackgroundId(R.drawable.yellow_button);
		
		// Add view
		m_tabBarItemLayout.addView(button);
		
		updateView();
	}
	
	public void removeTabBarButton(final Integer key) {
		m_tabBarButtonMap.remove(key);
		updateView();
	}
	
	public void setBackgroundColor(int colorId) {
		this.setBackgroundColor(colorId);
	}
	
	public void setSelectedButton(final CustomButton button) {
		// Update the current selected button
		m_currentSelectButton = button;
		
		Iterator it = m_tabBarButtonMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry) it.next();
	        CustomButton tbb = (CustomButton) pairs.getValue();
	        if (button == tbb) 
				tbb.setButtonState(CustomButton.ButtonState.Selected);
			else 
				tbb.setButtonState(CustomButton.ButtonState.Normal);
	    }
	}
	
	public void setSelectedButton(final int buttonId) {
		Iterator it = m_tabBarButtonMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry) it.next();
	        Integer id = (Integer) pairs.getKey();
	        CustomButton tbb = (CustomButton) pairs.getValue();
	        if (buttonId == id.intValue()) {
	        	// Update the current selected button
	    		m_currentSelectButton = tbb;
				tbb.setButtonState(CustomButton.ButtonState.Selected);
	        } else 
				tbb.setButtonState(CustomButton.ButtonState.Normal);
	    }
	}
	
	public CustomButton getButtonByKey(final int buttonId) {
		Iterator it = m_tabBarButtonMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry) it.next();
	        Integer id = (Integer) pairs.getKey();
	        CustomButton tbb = (CustomButton) pairs.getValue();
	        if (buttonId == id.intValue()) 
				return tbb;
	    }
	    
	    return null;
	}
	
	public int getButtonKeyFromButton(final CustomButton button) {
		Iterator it = m_tabBarButtonMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry) it.next();
	        Integer id = (Integer) pairs.getKey();
	        CustomButton tbb = (CustomButton) pairs.getValue();
	        if (button == tbb) 
				return id;
	    }
	    
	    return -1;
	}
}

