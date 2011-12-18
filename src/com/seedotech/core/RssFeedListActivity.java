package com.seedotech.core;

import com.seedotech.R;
import com.seedotech.common.Global;
import com.seedotech.common.LanguageCode;
import com.seedotech.controllers.SdtController;
import com.seedotech.models.ReaderModel;
import com.seedotech.models.RssFeed;
import com.seedotech.models.RssFeedPK;
import com.seedotech.models.RssFeedModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class RssFeedListActivity extends Activity {
	private RssFeedListView 	m_ui	= new RssFeedListView(this);
	
	RssFeedModel    			m_filterRssFeedModel;
    RssFeedModel    			m_rssFeedModel;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_feed_list_view);
        
        initialize();
    }
    
    public boolean initialize() {
    	// Initialize data model
    	SdtController sdtController = SdtController.getInstance();
    	sdtController.getLanguageUtil().setData(getResources().getAssets(), LanguageCode.LC_ENGLISH);
        
    	ReaderModel readerModel = ReaderModel.getInstance();
    	readerModel.initialize(this);
    	
	    m_filterRssFeedModel = readerModel.getRssFeedModel();
	    
		// Initialize UI
    	m_ui.initUI();
		
		// Re-translate UI
    	m_ui.retranslateUI();
	    
	    // Set login status
	    m_ui.updateView();
	    
        return true;
	}
	
    public RssFeedModel getRssFeedModel() {
    	return m_rssFeedModel;
    }
    
    public RssFeedModel getFilterRssFeedModel() {
    	return m_filterRssFeedModel;
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