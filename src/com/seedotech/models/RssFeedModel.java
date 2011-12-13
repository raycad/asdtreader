package com.seedotech.models;

import java.util.ArrayList;

import com.seedotech.models.RssFeed.RssFeedPK;

public class RssFeedModel {
	ArrayList<RssFeed>	m_rssFeedList = new ArrayList<RssFeed>();
	
	public boolean addRssFeed(final RssFeed rssFeed) {
		if (getRssFeedByPK(rssFeed.getRssFeedPK()) != null)
	        return false; // The feed is existing
	    
		m_rssFeedList.add(rssFeed);
	    
	    return true;
	}
	
	public RssFeed getRssFeedByPK(final RssFeedPK rssFeedPK) {
		if (rssFeedPK == null)
	        return null;
	    
	    for (int i = 0; i < m_rssFeedList.size(); i++) {
	        RssFeed rssFeed = m_rssFeedList.get(i);
	        if (rssFeedPK.isEqual(rssFeed.getRssFeedPK()))
	            return rssFeed;
	    }
	    
	    return null;
	}
	
	public RssFeed getRssFeedAtIndex(final int index) {
		if (index < 0 || index >= count())
	        return null;
	    
	    return m_rssFeedList.get(index);
	}
	
	public boolean removeRssFeedByPK(final RssFeedPK rssFeedPK) {
		for (int i = 0; i < m_rssFeedList.size(); i++) {
	        RssFeed rssFeed = m_rssFeedList.get(i);
	        if (rssFeedPK.isEqual(rssFeed.getRssFeedPK())) {
	        	m_rssFeedList.remove(i);
	            return true;
	        }
	    }
	    
	    return false;
	}
	
	public boolean removeRssFeedByIndex(int index) {
		m_rssFeedList.remove(index);
		return true;
	}
	
	public int count() {
		return m_rssFeedList.size();
	}
}
