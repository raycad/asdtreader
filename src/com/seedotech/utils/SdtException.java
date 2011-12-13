/**
 * File FchkException.java
 *
 * Brief 
 *
 * Copyright of Nomovok Ltd. All rights reserved.
 *
 * Contact: info@nomovok.com
 *
 * Author Nguyen Truong Duong <nguyentruong.duong@nomovok.com> 
 *
 * Created at 3:24:52 PM Dec 6, 2011
 * 
 * Any other legal text to be defined later
 */

package com.seedotech.utils;

public class SdtException extends Exception {
	private int			m_errorCode;
	private String 		m_errorString;
	
	public SdtException(final int errorCode, final String errorString) {
		super(errorString);
		
		m_errorCode 	= errorCode;
		m_errorString	= errorString;
	}
	
	public int getErrorCode() {
		return m_errorCode;
	}
	
	public String getErrorString() {
		switch (m_errorCode) {
		case EC_UNKNOWN:
			return m_errorString;
		case EC_LOST_NETWORKING:
			return "Lost networking";
		case EC_FAILED_TO_DOWNLOAD_FILE:
			return "Failed to download file";
		case EC_PARSING_DATA:
			return "Error parsing data";
		}
		
		return "";
	}
	
	public static final int EC_UNKNOWN					= -1;
	public static final int EC_LOST_NETWORKING			= 0;
	public static final int EC_FAILED_TO_DOWNLOAD_FILE	= 1;
	public static final int EC_PARSING_DATA				= 2;
}
