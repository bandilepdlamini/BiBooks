package com.bandilepdlamini.bibooks.datasource;

import android.database.Cursor;

import com.bandilepdlamini.bibooks.util.UserDetail;

import java.util.ArrayList;


public interface UserDetailMobDao {

    boolean open();
    boolean close();
    int deleteAll();
    long insertUserDetail(UserDetail UserDetail);
    int updateUserDetail(UserDetail UserDetail, String username);
    Cursor fetchAllUserDetails();
    ArrayList<UserDetail> fetchAllUsers();
    Cursor getUserDetailOnName(String UserName);
    boolean deleteUserDetail(String username);
    boolean isFound();
    UserDetail getUser();

}


