/**
 * File SdtView.java
 *
 * Brief 
 *
 * Copyright of Nomovok Ltd. All rights reserved.
 *
 * Contact: seedotech@gmail.com
 *
 * Author Nguyen Truong Duong <seedotech@gmail.com> 
 *
 * Created at 12:09:32 PM Dec 6, 2011
 * 
 * Any other legal text to be defined later
 */
package com.seedotech.customviews;

public interface SdtView {
	boolean initUI();			// Create controls, set up connections
	void 	retranslateUI();	// Localization 
	void 	updateView();		// Update view: controls, positions
}