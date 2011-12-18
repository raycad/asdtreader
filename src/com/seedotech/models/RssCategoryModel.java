package com.seedotech.models;

import java.util.ArrayList;

public class RssCategoryModel {
private ArrayList<RssCategory>	m_rssCategoryList = new ArrayList<RssCategory>();
	
	public boolean addRssCategory(final RssCategory rssCategory) {
		if (getRssCategoryByPK(rssCategory.getRssCategoryPK()) != null)
	        return false; // The category is existing
	    
		m_rssCategoryList.add(rssCategory);
	    
	    return true;
	}
	
	public RssCategory getRssCategoryByPK(final RssCategoryPK rssCategoryPK) {
		if (rssCategoryPK == null)
	        return null;
	    
	    for (int i = 0; i < m_rssCategoryList.size(); i++) {
	        RssCategory rssCategory = m_rssCategoryList.get(i);
	        if (rssCategoryPK.isEqual(rssCategory.getRssCategoryPK()))
	            return rssCategory;
	    }
	    
	    return null;
	}
	
	public RssCategory getRssCategoryAtIndex(final int index) {
		if (index < 0 || index >= count())
	        return null;
	    
	    return m_rssCategoryList.get(index);
	}
	
	public RssCategory getCategoryById(final int categoryId) {
		for (int i = 0; i < m_rssCategoryList.size(); i++) {
	        RssCategory rssCategory = m_rssCategoryList.get(i);
	        if (rssCategory.getRssCategoryId() == categoryId)
	        	return rssCategory;
	    }
		
		return null;
	}
	
	public boolean removeRssCategoryByPK(final RssCategoryPK rssCategoryPK) {
		for (int i = 0; i < m_rssCategoryList.size(); i++) {
	        RssCategory rssCategory = m_rssCategoryList.get(i);
	        if (rssCategoryPK.isEqual(rssCategory.getRssCategoryPK())) {
	        	m_rssCategoryList.remove(i);
	            return true;
	        }
	    }
	    
	    return false;
	}
	
	public boolean removeRssCategoryByIndex(int index) {
		m_rssCategoryList.remove(index);
		return true;
	}
	
	public int count() {
		return m_rssCategoryList.size();
	}
}
