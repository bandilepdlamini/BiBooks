package com.bandilepdlamini.bibooks.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bandilepdlamini.bibooks.R;
import com.bandilepdlamini.bibooks.datasource.UserDetailMobImpl;
import com.bandilepdlamini.bibooks.main.MainActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProfileDialog extends Dialog implements android.view.View.OnClickListener{

    public Context c;
    public Dialog d;
    public Button cancel, add;
    ListView listView;
    private UserDetailMobImpl userDetailMob;
    private ProfileAdapter profileAdapter;
    TextView text;

    public ProfileDialog(Context a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        userDetailMob = new UserDetailMobImpl(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.profile_dialog);
        cancel = (Button) findViewById(R.id.btn_cancel);
        add = (Button) findViewById(R.id.add);
        listView = (ListView) findViewById(R.id.listview);
        text = (TextView) findViewById(R.id.textView2);
        cancel.setOnClickListener(this);
        add.setOnClickListener(this);

        userDetailMob.open();

        ArrayList<UserDetail> arrayList = userDetailMob.fetchAllUsers();

        profileAdapter = new ProfileAdapter(arrayList, c, listView, text);
        listView.setAdapter(profileAdapter);

        userDetailMob.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                //c.getApplicationContext().finish();

                dismiss();
                break;
            case R.id.add:
                addUser();
                break;
            default:
                break;
        }
    }


    private void addUser() {
        //userDetailMob.open();
        ArrayList<UserDetail> arrayList = userDetailMob.fetchAllUsers();
        int count = 0;
        for(int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i).getUsername().contains("New Player")){
                count++;
            }
        }

        userDetailMob.open();
        if(count == 0){
            userDetailMob.insertUserDetail(new UserDetail("New Player", "", "", ""));
        }else{

            for(int i = 0; i < arrayList.size(); i++){
                if(arrayList.get(i).getUsername().equals("New Player" + count)){
                    count++;
                }
            }

            UserDetail userDetail = new UserDetail("New Player" + count, "", "", "");

            userDetailMob.insertUserDetail(userDetail);
        }

        arrayList = userDetailMob.fetchAllUsers();
        userDetailMob.close();

        profileAdapter = new ProfileAdapter(arrayList, c, listView, text);
        listView.setAdapter(profileAdapter);

        //userDetailMob.close();

    }

}
