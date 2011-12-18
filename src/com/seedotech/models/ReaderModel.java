package com.seedotech.models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.seedotech.utils.SdtDatabaseHelper;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class ReaderModel {

	private static final ReaderModel 	m_instance 				= new ReaderModel();
	private RssFeedModel				m_rssFeedModel			= null;
	private Context						m_context 				= null;
	private SQLiteDatabase 				m_readerDatabase		= null;

	private ReaderModel() {
	}

	public static ReaderModel getInstance() {
		return m_instance;
	}

	public SQLiteDatabase getReaderDatabase() {
		return m_readerDatabase;
	}

	public RssFeedModel getRssFeedModel() {
		return m_rssFeedModel;
	}
	
	public void initialize(final Context contex) {
		try {
			m_context = contex;
			SdtDatabaseHelper dbHelper = new SdtDatabaseHelper(m_context);
			try {
				dbHelper.createDataBase();
			} catch (IOException ioe) {
				throw new Error("Unable to create database");
			}

			try {
				m_readerDatabase = dbHelper.openDataBase();
				dbHelper.close();
			} catch(SQLException sqle){
				throw sqle;
			}

			// Get data
			RssFeedDAO rssFeedDAO = new RssFeedDAO();
			m_rssFeedModel = rssFeedDAO.getAllRssFeeds();
		} catch (Exception e) {
			Log.d(ReaderModel.class.toString(), e.toString());
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		if (m_readerDatabase != null)
			m_readerDatabase.close();
		
		super.finalize();
	}
}
