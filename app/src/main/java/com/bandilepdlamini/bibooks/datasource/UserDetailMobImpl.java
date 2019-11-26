package com.bandilepdlamini.bibooks.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.bandilepdlamini.bibooks.util.UserDetail;

import java.util.ArrayList;
import java.util.List;


public class UserDetailMobImpl implements UserDetailMobDao {

    private static final String DB_TABLE = "USERDETAIL";
    private Context context;
    private SQLiteDatabase db;
    private DataBaseSetup dbHelper;
    private boolean isFound = false;
    String[] columns = new String[] {
            "_id ", "USERNAME", "BESTSCORE ", "BESTTIME", "ACTIVE" };


    // -----------------------
    public UserDetailMobImpl(Context context) {
        this.context = context;
    }

    // -----------------------
    @Override
    public boolean open() {
        boolean isOpen = false;

        try {
            dbHelper = new DataBaseSetup(context);
            db = dbHelper.getWritableDatabase();
            isOpen = true;
        }// try

        catch (SQLiteException ex) {
            ex.printStackTrace();
            // System.exit(0);
        }

        return isOpen;
    }

    // -----------------------
    @Override
    public boolean close() {
        boolean isClose = false;
        try {
            dbHelper.close();
            isClose = true;
        }// try
        catch (SQLiteException ex) {
            ex.printStackTrace();
            // System.exit(0);
        }

        return isClose;
    }

    // -----------------------
    @Override
    public int deleteAll() {
        int recdeleted = 0;
        try {
            recdeleted = db.delete(DB_TABLE, "1", null);
        } catch (SQLiteException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recdeleted;
    }

    // -----------------------
    @Override
    public long insertUserDetail(UserDetail pUserDetail) {
        long recid = 0;
        ContentValues values = createContentValues(pUserDetail);
        recid = db.insert(DB_TABLE, null, values);
        // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + );
        // System.out.println(">>>>"+productName);

        return recid;
    }

    // -----------------------
    @Override
    public int updateUserDetail(UserDetail pUserDetail, String username) {
        int rowsupdated = 0;
        try {
            ContentValues values = createContentValues(pUserDetail);
            rowsupdated = db.update(DB_TABLE, values, "USERNAME" + " = '"
                    + username + "'", null);
        } catch (SQLiteException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsupdated;
    }


    // --------------------
    private ContentValues createContentValues(UserDetail pUserDetail) {
        ContentValues values = new ContentValues();
        values.put("USERNAME", "" + pUserDetail.getUsername());
        values.put("BESTSCORE", "" + pUserDetail.getBestScore());
        values.put("BESTTIME", "" + pUserDetail.getBestTime());
        values.put("ACTIVE", "" + pUserDetail.getActive());

        return values;
    }


    //-----------------------------------------
    @Override
    public boolean deleteUserDetail(String username) {
        boolean isDeleted = false;
        try {
            int rows = db.delete(DB_TABLE, "USERNAME" + " = ?",
                    new String[] { username });
            if (rows == 1) {
                isDeleted = true;
            }
        } catch (SQLiteException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }



    // ------------------
    @Override
    public Cursor fetchAllUserDetails() {
        return db.query(DB_TABLE, new String[] { "_id", "USERNAME"}, null, null, null, null, null);
    }




    // --------------------------------------
    //get first record
    @Override
    public UserDetail getUser() {
        UserDetail Userdetail;
        Cursor cursor = db.query(DB_TABLE, columns,null , null, null, null, null, null);
        cursor.moveToFirst();
        Userdetail = setAll(cursor);
        cursor.close();
        return Userdetail;
    }

    // --------------------------------------
    //fetchLikeUserRecords
    @Override
    public Cursor getUserDetailOnName(String pUserName) {
        //     Cursor cursor = db.query(DB_TABLE, columns, "UserNAME" + " LIKE '"
        //                   + pUserName + "%'", null, null, null, null, null);
        //Cursor cursor = db.query(DB_TABLE, columns, "UserNAME" + " LIKE '"
        //            + pUserName + "%'", null, null, null, "UserNAME", null);
        //drie 26/2/2017

        Cursor cursor = db.query(DB_TABLE, columns, "USERNAME LIKE ?",
                new String[] { "%" + pUserName + "%" }, null, null, "USERNAME", null);
        return cursor;
    }


    // ------------------------------------
    private UserDetail setAll(Cursor pCursor) {
        UserDetail Userdetail = new UserDetail();
        try {
            if(pCursor.getCount() != 0) {
                Userdetail.setUsername(pCursor.getString(pCursor.getColumnIndex("USERNAME")));
                Userdetail.setBestScore(pCursor.getString(pCursor.getColumnIndex("BESTSCORE")));
                Userdetail.setBestTime(pCursor.getString(pCursor.getColumnIndex("BESTTIME")));
                Userdetail.setActive(pCursor.getString(pCursor.getColumnIndex("ACTIVE")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Userdetail;
    }

    // ------------------
    @Override
    public ArrayList<UserDetail> fetchAllUsers() {
        ArrayList<UserDetail> list = new ArrayList<>();
        String limitRecCount = "30";
        open();
        Cursor cursor = db.query(DB_TABLE, columns, "USERNAME LIKE ?",
                new String[] { "%" }, null, null, "USERNAME",
                limitRecCount);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            list.add(setAll(cursor));
        }
        close();
        return list;
    }

    //-------------------------------
    @Override
    public boolean isFound() {
        return isFound;
    }
}
