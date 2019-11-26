package com.bandilepdlamini.bibooks.datasource;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseSetup  extends SQLiteOpenHelper  {
	private static final String DATABASE_NAME = "UserDetail.db";
	private static final int DATABASE_VERSION = 3;

	//-----------
	public DataBaseSetup(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//File f = context.getDatabasePath(DATABASE_NAME);
		//long dbSize = f.length();
		//Log.v("-----DB size-MB -----"," " + (dbSize/1024)/1024);
	}

	//---------------------
	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		CreateUpdateDatabase.onCreate(database);
	}

	//-------------------------
	// Method is called during an upgrade of the database,
	// e.g. if you increase the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,int newVersion) {
		CreateUpdateDatabase.onUpgrade(database, oldVersion, newVersion);
	}
	//-----------------------------------------------
	public ArrayList<Cursor> getData(String Query){
		//get writable database
		SQLiteDatabase sqlDB = this.getWritableDatabase();
		String[] columns = new String[] { "message" };
		//an array list of cursor to save two cursors one has results from the query
		//other cursor stores error message if any errors are triggered
		ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
		MatrixCursor Cursor2= new MatrixCursor(columns);
		alc.add(null);
		alc.add(null);


		try{
			String maxQuery = Query ;
			//execute the query results will be save in Cursor c
			Cursor c = sqlDB.rawQuery(maxQuery, null);

			//add value to cursor2
			Cursor2.addRow(new Object[] { "Success" });
			alc.set(1,Cursor2);
			if (null != c && c.getCount() > 0) {
				alc.set(0,c);
				c.moveToFirst();
				return alc ;
			}
			return alc;
		} catch(SQLException sqlEx){
			Log.d("printing exception", sqlEx.getMessage());
			//if any exceptions are triggered save the error message to cursor an return the arraylist
			Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
			alc.set(1,Cursor2);
			return alc;
		} catch(Exception ex){
			Log.d("printing exception", ex.getMessage());
			//if any exceptions are triggered save the error message to cursor an return the arraylist
			Cursor2.addRow(new Object[] { ""+ex.getMessage() });
			alc.set(1,Cursor2);
			return alc;
		}

	}
}
