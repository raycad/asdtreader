package com.seedotech.models;

public class RssFeed {
	private int         m_rssFeedId;
	private String    	m_title;
	private String    	m_link;
	private String    	m_website;
	private String    	m_description;
	private int         m_rate; 		// From 0 to 4
	private RssCategory m_rssCategory;
	private RssFeedPK   m_rssFeedPK;
	
	public RssFeed(final RssFeedPK rssFeedPK) {
		m_rssFeedPK = rssFeedPK;
	}
	
	public RssFeedPK getRssFeedPK() {
		return m_rssFeedPK;
	}
	
	public RssFeed(final RssFeed other) {
		RssFeedPK otherPK 	= new RssFeedPK(other.getRssFeedPK());
		this.m_rssFeedId 	= other.getRssFeedId();
		this.m_title 		= other.getTitle();
		this.m_link 		= other.getLink();
		this.m_website 		= other.getWebsite();
		this.m_description 	= other.getDescription();
		this.m_rate 		= other.getRate();
		this.m_rssCategory 	= other.getRssCategory();
		this.m_rssFeedPK 	= otherPK;
	}
	
	public void setRssFeedId(final int id) {
		m_rssFeedId = id;
	}
	public int getRssFeedId() {
		return m_rssFeedId;
	}
	
	public void setTitle(final String title) {
		m_title = title;
	}
	public String getTitle() {
		return m_title;
	}
	
	public void setLink(final String link) {
		m_link = link;
	}
	public String getLink() {
		return m_link;
	}
	
	public void setWebsite(final String website) {
		m_website = website;
	}
	public String getWebsite() {
		return m_website;
	}
	
	public void setDescription(final String description) {
		m_description = description;
	}
	public String getDescription() {
		return m_description;
	}
	
	public void setRate(final int rate) {
		m_rate = rate;
	}
	public int getRate() {
		return m_rate;
	}
	
	public void setRssCategory(final RssCategory rssCategory) {
		m_rssCategory = rssCategory;
	}
	public RssCategory getRssCategory() {
		return m_rssCategory;
	}
	
	public boolean isEqual(final RssFeed other) {
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
