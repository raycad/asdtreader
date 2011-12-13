package com.seedotech.core;

import com.seedotech.R;
import com.seedotech.common.Global;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class RssFeedListActivity extends Activity {
	private RssFeedListView 	m_ui	= new RssFeedListView(this);
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_feed_list_view);
        
        initialize();
    }
    
    public boolean initialize() {
    	// Initialize data model
        
		// Initialize UI
    	m_ui.initUI();
		
		// Re-translate UI
    	m_ui.retranslateUI();
	    
	    // Set login status
	    m_ui.updateView();
	    
        return true;
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {	
    	if (requestCode == Global.RSS_CATEGORY_LIST_VIEW_REQUEST_CODE) {
    		if (resultCode == RESULT_OK) {
    			String extraData = data.getStringExtra("LOGIN");
    			extraData = "";
    			// Set login status
    			m_ui.updateView(); 
    		}
    	}
    }
}