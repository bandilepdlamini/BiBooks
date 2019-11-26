package com.bandilepdlamini.bibooks.main;

/**
 * Created by User on 2018/01/20.
 */


import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.net.*;
import android.util.*;

import com.bandilepdlamini.bibooks.R;
import com.bandilepdlamini.bibooks.main.MainActivity;

import java.util.*;


public class AboutActivity extends Activity {


    private Button backbutton;
    private TextView textview5;
    private ImageView facebook;
    private ImageView instagram;
    private ImageView linkedin;
    private ImageView googleplus;



    private Intent page = new Intent();
    private Intent intentWeb = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getActionBar().hide();
        initialize();
        initializeLogic();
    }

    private void  initialize() {
        backbutton = (Button) findViewById(R.id.backbutton);
        textview5 = (TextView) findViewById(R.id.textview5);
        facebook = (ImageView) findViewById(R.id.facebook);
        instagram = (ImageView) findViewById(R.id.instagram);
        linkedin = (ImageView) findViewById(R.id.linkedin);
        googleplus = (ImageView) findViewById(R.id.googleplus);




        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                page.setClass(getApplicationContext(), MainActivity.class);
                startActivity(page);
                finish();
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                intentWeb.setAction(Intent.ACTION_VIEW);
                intentWeb.setData(Uri.parse("https://facebook.com/profile.php?id=100005765928796&ref=bookmarks"));
                startActivity(intentWeb);
            }
        });
        googleplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                intentWeb.setAction(Intent.ACTION_VIEW);
                intentWeb.setData(Uri.parse("https://plus.google.com/u/0/+BandileDlamini7"));
                startActivity(intentWeb);
            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                intentWeb.setAction(Intent.ACTION_VIEW);
                intentWeb.setData(Uri.parse("http://linkedin.com/in/bandile-p-dlamini-9a0583a2"));
                startActivity(intentWeb);
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                intentWeb.setAction(Intent.ACTION_VIEW);
                intentWeb.setData(Uri.parse("http://instagram.com/bandile_dlamini/"));
                startActivity(intentWeb);
            }
        });
        textview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                intentWeb.setAction(Intent.ACTION_VIEW);
                intentWeb.setData(Uri.parse("http://bandilepdlamini.com"));
                startActivity(intentWeb);
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
