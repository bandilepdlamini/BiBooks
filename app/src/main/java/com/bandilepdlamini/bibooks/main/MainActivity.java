package com.bandilepdlamini.bibooks.main;

/**
 * Created by User on 2018/01/20.
 */

import android.app.*;
import android.graphics.PorterDuff;
import android.os.*;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.util.*;

import com.bandilepdlamini.bibooks.R;
import com.bandilepdlamini.bibooks.datasource.UserDetailMobImpl;
import com.bandilepdlamini.bibooks.util.AchievementsDialog;
import com.bandilepdlamini.bibooks.util.ProfileDialog;
import com.bandilepdlamini.bibooks.util.UserDetail;

import java.util.*;


public class MainActivity extends Activity {

    private ImageView imageview1;
    private TextView bibooks;
    private ImageView imageview2;
    private TextView author;
    private EditText name;
    private TextView viewName;
    private TextView info1;
    private CheckBox newTestament;
    private CheckBox oldTestament;
    private Button edit;
    private Button achievements;
    private Button donebutton;
    private Button aboutbutton;
    private Button exit;
    private UserDetailMobImpl userDetailMob = new UserDetailMobImpl(this);
    private UserDetail userDetail = new UserDetail();
    private ArrayList<UserDetail> arrayList = new ArrayList<>();
    private Thread thread = null;


    private Intent page = new Intent();

    private Bundle bundle = new Bundle();

    public void startThread(){
        if(thread != null){
            thread.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getActionBar().hide();
        initialize();
        initializeLogic();
    }

    private void  initialize() {
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        bibooks = (TextView) findViewById(R.id.bibooks);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        author = (TextView) findViewById(R.id.author);
        name = (EditText) findViewById(R.id.name);
        viewName = (TextView) findViewById(R.id.viewname);
        info1 = (TextView) findViewById(R.id.info1);
        edit = (Button) findViewById(R.id.btn_edit);
        achievements = (Button) findViewById(R.id.btn_achievements);
        donebutton = (Button) findViewById(R.id.donebutton);
        aboutbutton = (Button) findViewById(R.id.aboutbutton);
        exit = (Button) findViewById(R.id.exit);
        newTestament = (CheckBox) findViewById(R.id.checkBox1);
        oldTestament = (CheckBox) findViewById(R.id.checkBox2);

        /*thread = new Thread(new Runnable() {
            public void run() {
                if(userDetailMob != null) {
                    userDetailMob.open();
                    arrayList = userDetailMob.fetchAllUsers();

                    for(int i = 0; i < arrayList.size(); i++){
                        if(arrayList.get(i).getActive().equals("1")){
                            userDetail = arrayList.get(i);

                            name.setVisibility(View.INVISIBLE);
                            viewName.setVisibility(View.VISIBLE);
                            viewName.setText("Welcome back " + userDetail.getUsername() + "!");
                            edit.setVisibility(View.VISIBLE);
                            achievements.setVisibility(View.VISIBLE);
                            break;
                        }
                    }

                    //userDetail = userDetailMob.getUser();
                    if(arrayList.size() <= 0){
                        name.setVisibility(View.VISIBLE);
                        viewName.setVisibility(View.INVISIBLE);
                        edit.setVisibility(View.INVISIBLE);
                        achievements.setVisibility(View.INVISIBLE);
                    }
                    userDetailMob.close();
                }
            }
        });*/


        if(userDetailMob != null) {
            userDetailMob.open();
            arrayList = userDetailMob.fetchAllUsers();

            for(int i = 0; i < arrayList.size(); i++){
                if(arrayList.get(i).getActive().equals("1")){
                    userDetail = arrayList.get(i);

                    name.setVisibility(View.INVISIBLE);
                    viewName.setVisibility(View.VISIBLE);
                    viewName.setText("Welcome back " + userDetail.getUsername() + "!");
                    edit.setVisibility(View.VISIBLE);
                    achievements.setVisibility(View.VISIBLE);
                    break;
                }
            }

            //userDetail = userDetailMob.getUser();
            if(arrayList.size() <= 0){
                name.setVisibility(View.VISIBLE);
                viewName.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.INVISIBLE);
                achievements.setVisibility(View.INVISIBLE);
            }
            userDetailMob.close();
        }



        edit.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.white), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        achievements.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.white), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        donebutton.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.white), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        aboutbutton.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.white), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        exit.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.white), PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AchievementsDialog achievementsDialog = new AchievementsDialog(MainActivity.this);
                achievementsDialog.show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileDialog profileDialog = new ProfileDialog(MainActivity.this);
                profileDialog.show();
            }
        });
        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                if(newTestament.isChecked() && !oldTestament.isChecked()){
                    if(!name.getText().toString().equals("")){
                        userDetailMob.open();
                        userDetailMob.insertUserDetail(new UserDetail(name.getText().toString(), "", "", ""));
                        userDetailMob.close();
                    }

                    page.setClass(getApplicationContext(), NewtestamentActivity.class);
                    startActivity(page);
                }else if(oldTestament.isChecked() && !newTestament.isChecked()){
                    if(!name.getText().toString().equals("")){
                        userDetailMob.insertUserDetail(new UserDetail(name.getText().toString(), "", "", ""));
                    }

                    page.setClass(getApplicationContext(), OldtestamentActivity.class);
                    startActivity(page);
                }else if(oldTestament.isChecked() && newTestament.isChecked()){
                    if(!name.getText().toString().equals("")){
                        userDetailMob.insertUserDetail(new UserDetail(name.getText().toString(), "", "", ""));
                    }

                    page.setClass(getApplicationContext(), BibleActivity.class);
                    startActivity(page);
                }else{
                    showMessage("Which books do you know best? ");
                    final ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.select_books);
                    constraintLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.light_grey));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            constraintLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.bd_grey));
                        }
                    }.start();
                }


            }
        });
        aboutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                page.setClass(getApplicationContext(), AboutActivity.class);
                startActivity(page);
                finish();
            }
        });
        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {

            }
        });
        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                for(int _repeat11 = 0; _repeat11 < (int)(50); _repeat11++) {
                    finish();
                }
            }
        });

    }

    private void  initializeLogic() {
    }



    // created automatically
    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    private int getRandom(int _minValue ,int _maxValue){
        Random random = new Random();
        return random.nextInt(_maxValue - _minValue + 1) + _minValue;
    }

    public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double)_arr.keyAt(_iIdx));
        }
        return _result;
    }

    private float getDip(int _input){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    private int getDisplayWidthPixels(){
        return getResources().getDisplayMetrics().widthPixels;
    }

    private int getDisplayHeightPixels(){
        return getResources().getDisplayMetrics().heightPixels;
    }


}


