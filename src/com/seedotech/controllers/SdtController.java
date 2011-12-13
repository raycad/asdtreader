package com.seedotech.controllers;

import com.seedotech.customviews.DrawableBackgroudDownloader;
import com.seedotech.utils.LanguageUtil;

public class SdtController {
	private static final SdtController 	m_instance 		= new SdtController();
	
	//private RssFeed						m_rssFeedShared	= null;
	
	private LanguageUtil m_languageUtil = new LanguageUtil();
	
	private DrawableBackgroudDownloader m_drawableBackgroudDownloader = new DrawableBackgroudDownloader();
	
	private SdtController() {
		loadSettings();
	}
	
	public static SdtController getInstance() {
        return m_instance;
    }
	
	public LanguageUtil getLanguageUtil() {
		return m_languageUtil;
	}
	
	/*public void setRssFeedShared(final RssFeed rssFeed) {
		m_rssFeedShared = rssFeed;
	}
	public RssFeed getRssFeed() {
		return m_rssFeed;
	}*/
	
	public DrawableBackgroudDownloader getDrawableBackgroudDownloader() {
		return m_drawableBackgroudDownloader;
	}
	
	private void loadSettings() {
		//Properties properties = new Properties();        
	}
	
	private void saveSettings() {
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		saveSettings();
		super.finalize();
	}
}