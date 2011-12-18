package com.seedotech.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RssFeedDAO {
	public RssFeedModel getAllRssFeeds() {
		Cursor cursor = null;
		try {
			ReaderModel readerModel = ReaderModel.getInstance();
			SQLiteDatabase db = readerModel.getReaderDatabase();
			
			RssFeedModel rssFeedModel = new RssFeedModel();
			
			cursor = db.rawQuery("select * from rssfeed", null);
			
			int             rssFeedId = 0;
	        int             rssCategoryId = 0;
	        String        	title;
	        String        	link;
	        String        	website;
	        String        	description;   
	        RssFeedPK       rssFeedPK;
	        RssFeed         rssFeed;
	        int             rate = 0;
	        
	        int rssFeedIdIndex, rssCategoryIdIndex, titleIndex, linkIndex, websiteIndex, descriptionIndex, rateIndex;
			if(cursor.getCount() > 0) {
				// Get column indexes
				rssFeedIdIndex 		= cursor.getColumnIndex("id");
				rssCategoryIdIndex 	= cursor.getColumnIndex("category_id");
				titleIndex 			= cursor.getColumnIndex("title");
				linkIndex 			= cursor.getColumnIndex("link");
				websiteIndex 		= cursor.getColumnIndex("website");
				descriptionIndex	= cursor.getColumnIndex("description");
				rateIndex 			= cursor.getColumnIndex("rate");
                
				cursor.moveToFirst();
				while(!cursor.isAfterLast()) {
					// Read the data from the result row
	                rssFeedId 		= cursor.getInt(rssFeedIdIndex);
	                rssCategoryId 	= cursor.getInt(rssCategoryIdIndex);
	                title 			= cursor.getString(titleIndex);
	                link 			= cursor.getString(linkIndex);
	                website 		= cursor.getString(websiteIndex);
	                description 	= cursor.getString(descriptionIndex);
	                rate 			= cursor.getInt(rateIndex);
	                
	                rssFeedPK      = new RssFeedPK(title);
	                rssFeed        = new RssFeed(rssFeedPK);
	                rssFeed.setRssFeedId(rssFeedId);
	                rssFeed.setTitle(title);
	                rssFeed.setLink(link);
	                rssFeed.setWebsite(website);
	                rssFeed.setDescription(description);
	                //rssFeed.setRssCategory     = [rssCategoryModel getCategoryById:rssCategoryId];
	                rssFeed.setRate(rate);
	                if (rssFeedModel.addRssFeed(rssFeed)) {
	                    Log.d(RssFeedDAO.class.toString(), "Added rss feed sucessfully");
	                }
	                
					cursor.moveToNext();
				}
			}
			
			cursor.close();
			return rssFeedModel;
		} finally {
			if(cursor != null) {
				cursor.close();
			}
		}
	}
}
