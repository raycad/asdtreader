/**
 * File TitleBarCallback.java
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
package com.seedotech.customviews;

public interface TabBarCallback {
	public void onLoggingIn(); 
	public void onLoggedIn();
	public void onLoggingOut();
	public void onLoggedOut();
}
