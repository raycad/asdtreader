package com.seedotech.models;

public class RssCategoryPK {
    String m_title;    

    public RssCategoryPK(final String title) {
		m_title = title;
	}
    
    public RssCategoryPK(final RssCategoryPK other) {
		m_title = other.getTitle();
	}
    
	public void setTitle(final String title) {
		m_title = title;
	}

	public String getTitle() {
		return m_title;
	}
	
	public boolean isEqual(final RssCategoryPK other) {
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
