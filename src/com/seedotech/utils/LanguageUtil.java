/**
 * File LanguageUtil.java
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
package com.seedotech.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.seedotech.common.Global;

import android.content.res.AssetManager;

public class LanguageUtil {
	private static final String KEY_VALUE_SEPARATOR 	= "=";
	private String				m_currentLanguageCode	= new String();	
	private ArrayList<String> 	m_textLines 			= new ArrayList<String>();
	
	public LanguageUtil() {		
	}
	
	public LanguageUtil(AssetManager asset, String lc) {
		setData(asset, lc);
	}
	
	public String getCurrentLanguageCode() {
		return m_currentLanguageCode;
	}
	
	public boolean setData(AssetManager asset, String lc) {
		if (m_currentLanguageCode.equals(lc))
			return false; // Nothing to update
		
		// Update the new language code
		m_currentLanguageCode = lc;
		
		m_textLines.clear();
		
		String languagePath = Global.LANGUAGES_DIR + 
			Global.LANGUAGE_PREFIX + m_currentLanguageCode + Global.LANGUAGE_SUFFIX;
		
		try {
			final InputStream is = asset.open(languagePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			String line = "";
			while ((line = br.readLine()) != null) {
			    m_textLines.add(line);
			}
			
			is.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getTextByKey(String key) {
		key += KEY_VALUE_SEPARATOR;
		
		String line = "", text = "";
		for (int i = 0; i < m_textLines.size(); i++) {
			line = m_textLines.get(i); 
			if (line.startsWith(key)) {
		    	text = line.substring(key.length());
		    	return text;
		    }
		}			
		
		return "";
	}
}
