package com.bandilepdlamini.bibooks.datasource;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CreateUpdateDatabase {
	// Database creation SQL statement

	// User file
	private static final String USERDETAIL_CREATE = "CREATE TABLE USERDETAIL ("
		+ " _id INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ " USERNAME                TEXT, "
		+ " BESTSCORE				TEXT, "
		+ " BESTTIME				TEXT, "
		+ " ACTIVE		    		TEXT"
	+ ");";

	// Achievements file
	private static final String ACHIEVEMENTS_CREATE = "CREATE TABLE ACHIEVEMENTS ("
		+ " _id INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ " USERNAME                TEXT, "
		+ " ACHIEVEMENT				TEXT, "
		+ " VALUE					TEXT"
	+ ");";


	// -------------
	public static void onCreate(SQLiteDatabase database) {
		try {
			database.execSQL(USERDETAIL_CREATE);
			database.execSQL(ACHIEVEMENTS_CREATE);

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}// onCreate

	// --------------------------------------------------------------------
	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(CreateUpdateDatabase.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");

		database.execSQL("DROP TABLE IF EXISTS USERDETAIL");
		database.execSQL("DROP TABLE IF EXISTS ACHIEVEMENTS_CREATE");

		onCreate(database);
	}

}
