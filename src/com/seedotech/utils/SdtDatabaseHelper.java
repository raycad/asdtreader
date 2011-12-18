package com.seedotech.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class SdtDatabaseHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION 		= 1;
	private static String 	DB_PATH					= "/data/data/";
	private static String	DB_FOLDER				= "databases/";
	private static String 	DB_NAME 				= "sdtreader.db";
	private Context			m_context				= null;
	private String			m_databaseFullPathName	= "";

	/**
	 * Constructor
	 * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
	 * @param context
	 */
	public SdtDatabaseHelper(final Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		m_context = context;
		m_databaseFullPathName = DB_PATH + m_context.getPackageName() + "/" + DB_FOLDER + DB_NAME;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own database.
	 * */
	public void createDataBase() throws IOException{

		boolean dbExist = checkDataBase();

		if(dbExist) {
			// Do nothing - database already exist
		} else {
			// By calling this method and empty database will be created into the default system path
			// of your application so we are gonna be able to overwrite that database with our database.
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each time you open the application.
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			checkDB = SQLiteDatabase.openDatabase(m_databaseFullPathName, null, SQLiteDatabase.OPEN_READWRITE);
		} catch(SQLiteException e) {
			//database does't exist yet.
		}

		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created empty database in the
	 * system folder, from where it can be accessed and handled.
	 * This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {
		// Open your local db as the input stream
		InputStream is = m_context.getAssets().open(DB_FOLDER + DB_NAME);

		// Open the empty db as the output stream
		OutputStream os = new FileOutputStream(m_databaseFullPathName);

		// Transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) > 0){
			os.write(buffer, 0, length);
		}

		//Close the streams
		os.flush();
		os.close();
		is.close();
	}

	public SQLiteDatabase openDataBase() throws SQLException {
		// Open the database
		return SQLiteDatabase.openDatabase(m_databaseFullPathName, null, SQLiteDatabase.OPEN_READWRITE);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}

