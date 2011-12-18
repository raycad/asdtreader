package com.seedotech.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RssCategoryDAO {
	public RssCategoryModel getAllRssCategories() {
		Cursor cursor = null;
		try {
			ReaderModel readerModel = ReaderModel.getInstance();
			SQLiteDatabase db = readerModel.getReaderDatabase();

			cursor = db.rawQuery("select * from rssfeed", null);
			if (cursor.getCount() == 0)
				return null;

			RssCategoryModel rssCategoryModel = new RssCategoryModel();
			int             rssCategoryId = 0;
			String        	title, description;
			RssCategoryPK       rssCategoryPK;
			RssCategory         rssCategory;
			int rssCategoryIdIndex, titleIndex, descriptionIndex;
			
			// Get column indexes
			rssCategoryIdIndex 		= cursor.getColumnIndex("id");
			titleIndex 			= cursor.getColumnIndex("title");
			descriptionIndex	= cursor.getColumnIndex("description");

			cursor.moveToFirst();
			while(!cursor.isAfterLast()) {
				// Read the data from the result row
				rssCategoryId 	= cursor.getInt(rssCategoryIdIndex);
				rssCategoryId 	= cursor.getInt(rssCategoryIdIndex);
				title 			= cursor.getString(titleIndex);
				description 	= cursor.getString(descriptionIndex);

				rssCategoryPK      = new RssCategoryPK(title);
				rssCategory        = new RssCategory(rssCategoryPK);
				rssCategory.setRssCategoryId(rssCategoryId);
				rssCategory.setTitle(title);
				rssCategory.setDescription(description);
				if (rssCategoryModel.addRssCategory(rssCategory)) {
					Log.d(RssCategoryDAO.class.toString(), "Added rss feed sucessfully");
				}

				cursor.moveToNext();
			}

			cursor.close();
			return rssCategoryModel;
		} finally {
			if(cursor != null) {
				cursor.close();
			}
		}
	}
}
