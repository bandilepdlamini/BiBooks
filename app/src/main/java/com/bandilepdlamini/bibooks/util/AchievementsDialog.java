package com.bandilepdlamini.bibooks.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bandilepdlamini.bibooks.R;
import com.bandilepdlamini.bibooks.datasource.UserDetailMobImpl;

import java.util.ArrayList;

public class AchievementsDialog extends Dialog implements android.view.View.OnClickListener{
    public Context c;
    public Dialog d;
    public Button cancel;
    ListView listView;
    private UserDetailMobImpl userDetailMob;
    private AchievementsAdapter achievementsAdapter;
    TextView text;

    public AchievementsDialog(Context a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        userDetailMob = new UserDetailMobImpl(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.achievements_dialog);
        cancel = (Button) findViewById(R.id.btn_cancel);
        listView = (ListView) findViewById(R.id.listview);
        cancel.setOnClickListener(this);

        userDetailMob.open();

        ArrayList<UserDetail> arrayList = userDetailMob.fetchAllUsers();
        ArrayList<String> achievements = new ArrayList<>();
        UserDetail current = null;

        for(int i = 0 ; i < arrayList.size(); i++){
            if(arrayList.get(i).getActive().equals("1")){
                current = arrayList.get(i);
            }
        }

        if(current != null){
            if(!current.getBestScore().equals("") && !current.getBestScore().equals("0")){
                achievements.add("Best Score," + current.getBestScore());
            }
            if(!current.getBestTime().equals("") && !current.getBestTime().equals("0")){
                achievements.add("Best Time," + current.getBestTime());
            }
        }

        if(!achievements.isEmpty()){
            achievementsAdapter = new AchievementsAdapter(achievements, c);
            listView.setAdapter(achievementsAdapter);
        }


        userDetailMob.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                //c.getApplicationContext().finish();
                dismiss();
                break;
            default:
                break;
        }
    }

}
