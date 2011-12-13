/**
 * File DrawableBackgroudDownloader.java
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

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class DrawableBackgroudDownloader {    
	private final Map<String, SoftReference<Drawable>> m_cache 	= new HashMap<String, SoftReference<Drawable>>();   
	private final LinkedList <Drawable> m_cacheController 		= new LinkedList <Drawable> ();
	private ExecutorService m_threadPool						= null;  
	private final Map<ImageView, String> m_imageViews 			= Collections.synchronizedMap(new WeakHashMap<ImageView, String>());  

	public static int MAX_CACHE_SIZE 							= 80; 
	public int THREAD_POOL_SIZE 								= 3;

	/**
	 * Constructor
	 */
	public DrawableBackgroudDownloader() {  
		m_threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);  
	}  

	/**
	 * Clears all instance data and stops running threads
	 */
	public void reset() {
		ExecutorService oldThreadPool = m_threadPool;
		m_threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		oldThreadPool.shutdownNow();

		m_cacheController.clear();
		m_cache.clear();
		m_imageViews.clear();
	}  

	public void loadDrawable(final String url, final ImageView imageView, Drawable placeholder) {  
		m_imageViews.put(imageView, url);
		
		Drawable drawable = getDrawableFromCache(url);  
		// Check in UI thread, so no concurrency issues  
		if (drawable != null) {  
			// Log.d(null, "Item loaded from mCache: " + url);  
			// Clone a new drawable
			Drawable clone = drawable.getConstantState().newDrawable();
			imageView.setImageDrawable(clone);  
		} else {  
			imageView.setImageDrawable(placeholder);  
			queueJob(url, imageView, placeholder);  
		}  
	} 

	private Drawable getDrawableFromCache(String url) {  
		if (m_cache.containsKey(url)) {  
			return m_cache.get(url).get();  
		}  

		return null;  
	}

	private synchronized void putDrawableInCache(String url, Drawable drawable) {  
		int cacheControllerSize = m_cacheController.size();
		if (cacheControllerSize > MAX_CACHE_SIZE) 
			m_cacheController.subList(0, MAX_CACHE_SIZE/2).clear();

		m_cacheController.addLast(drawable);
		m_cache.put(url, new SoftReference<Drawable>(drawable));
	}  

	private void queueJob(final String url, final ImageView imageView, final Drawable placeholder) {  
		/* Create handler in UI thread. */  
		final Handler handler = new Handler() {  
			@Override  
			public void handleMessage(Message msg) {  
				String tag = m_imageViews.get(imageView);  
				if (tag != null && tag.equals(url)) {
					// If the view is not visible anymore, the image will be ready for next time in cache
					/*if (imageView.isShown())*/ {
						if (msg.obj != null) {
							imageView.setImageDrawable((Drawable) msg.obj);  
						} else {  
							imageView.setImageDrawable(placeholder);  
						} 
					}
				}  
			}  
		};  

		m_threadPool.submit(new Runnable() {  
			@Override  
			public void run() {  
				final Drawable bmp = downloadDrawable(url);
				// If the view is not visible anymore, the image will be ready for next time in cache
				/*if (imageView.isShown())*/ {
					Message message = Message.obtain();  
					message.obj = bmp;
					handler.sendMessage(message);
				}
			}  
		});  
	}  

	private Drawable downloadDrawable(String url) {  
		try {  
			InputStream is = getInputStream(url);

			Drawable drawable = Drawable.createFromStream(is, url);
			putDrawableInCache(url, drawable);  
			return drawable;  
		} catch (MalformedURLException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  

		return null;  
	}  

	private InputStream getInputStream(String urlString) throws MalformedURLException, IOException {
		URL url = new URL(urlString);
		URLConnection connection;
		connection = url.openConnection();
		connection.setUseCaches(true); 
		connection.connect();
		InputStream response = connection.getInputStream();

		return response;
	}
}
