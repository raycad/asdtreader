package com.seedotech.models;

public class RssCategory {
	private int             m_rssCategoryId;
	private String        	m_title;
	private String        	m_description;

	private RssCategoryPK   m_rssCategoryPK;
    
	private int             m_totalRssFeeds; 	// The number os feeds it has
	
	public RssCategory(final RssCategoryPK rssCategoryPK) {
		m_rssCategoryPK 	= rssCategoryPK;
	}
	
	public RssCategoryPK getRssCategoryPK() {
		return m_rssCategoryPK;
	}
	
	public RssCategory(final RssCategory other) {
		RssCategoryPK otherPK 	= new RssCategoryPK(other.getRssCategoryPK());
		this.m_rssCategoryId	= other.getRssCategoryId();
		this.m_title 			= other.getTitle();
		this.m_description 		= other.getDescription();
		this.m_totalRssFeeds	= other.getTotalRssFeeds();
		this.m_rssCategoryPK	= otherPK;
	}
	
	public void setRssCategoryId(final int id) {
		m_rssCategoryId = id;
	}
	public int getRssCategoryId() {
		return m_rssCategoryId;
	}
	
	public void setTitle(final String title) {
		m_title = title;
	}
	public String getTitle() {
		return m_title;
	}

	public void setDescription(final String description) {
		m_description = description;
	}
	public String getDescription() {
		return m_description;
	}
	
	public void setTotalRssFeeds(final int totalRssFeeds) {
		m_totalRssFeeds = totalRssFeeds;
	}
	public int getTotalRssFeeds() {
		return m_totalRssFeeds;
	}
	
	public boolean isEqual(final RssCategory other) {
		if (other == this)
	        return true;
	    
	    if (other == null)
	        return false;
	    
	    String title = other.getTitle();
	    
	    if (m_title.equals(title))
	        return true;
	    
	    return false;
	}
}
