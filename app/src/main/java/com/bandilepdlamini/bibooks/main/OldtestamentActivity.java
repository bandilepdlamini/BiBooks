package com.bandilepdlamini.bibooks.main;

/**
 * Created by User on 2018/01/20.
 */


import android.app.*;
import android.os.*;
import android.support.v4.content.ContextCompat;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.util.*;

import com.bandilepdlamini.bibooks.R;
import com.bandilepdlamini.bibooks.datasource.UserDetailMobImpl;
import com.bandilepdlamini.bibooks.main.MainActivity;
import com.bandilepdlamini.bibooks.util.RewardDialog;
import com.bandilepdlamini.bibooks.util.ScoreboardDialog;
import com.bandilepdlamini.bibooks.util.UserDetail;

import java.util.*;


public class OldtestamentActivity extends Activity {

    private TextView level;
    private TextView category;
    private TextView username;
    private TextView timerTextView;
    private TextView txtScore;
    /*private LinearLayout linear1;
    private LinearLayout linear2;
    private LinearLayout linear3;
    private LinearLayout linear4;
    private LinearLayout linear5;
    private LinearLayout linear6;
    private LinearLayout linear7;
    private LinearLayout linear8;
    private LinearLayout linear9;
    private LinearLayout linear10;
    private LinearLayout linear11;
    private ImageView imageview1;
    private ImageView imageview2;
    private ImageView imageview3;
    private ImageView imageview4;
    private ImageView imageview5;
    private ImageView imageview6;
    private ImageView imageview7;
    private ImageView imageview8;
    private LinearLayout image1;
    private LinearLayout image2;
    private LinearLayout image3;
    private LinearLayout image4;
    private LinearLayout image5;
    private LinearLayout image6;*/

    private Button imageview1;
    private Button imageview2;
    private Button imageview3;
    private Button imageview4;
    private Button imageview5;
    private Button imageview6;
    private Button imageview7;
    private Button imageview8;
    private Button imageview9;
    private Button imageview10;
    private Button imageview11;
    private Button imageview12;
    private Button startbutton;
    private Button stopbutton;
    private Button home;
    private Button newgame;
    private GridLayout gridLayout;

    private UserDetailMobImpl userDetailMob;
    private UserDetail userDetail;
    private ArrayList<UserDetail> arrayList;

    private double bestTime = 0;
    private double currentTime = 0;
    private double initValue = 0;
    private double srcIndex = 0;
    private double answerIndex = 0;
    private double prevSelected = 0;
    private double currentSelected = 0;
    private double bestScore = 0;
    private double score = 0;
    private double game_level = 0;
    private double total_score = 0;
    private double total_time = 0;
    private double total_minutes = 0;
    private double total_seconds = 0;
    private boolean isStarted;
    private long longStartTime = 0;

    private ArrayList<Double> srcList = new ArrayList<Double>();
    private ArrayList<Double> answersList = new ArrayList<Double>();

    private Calendar endTime = Calendar.getInstance();
    private Calendar startTime = Calendar.getInstance();
    private Vibrator vibration;
    private Intent page = new Intent();
    private AlertDialog.Builder dialog;

    private long millis;
    private int seconds;
    private int minutes;
    private int totalSeconds = 0;
    private int totalMinutes = 0;

    final Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            millis = System.currentTimeMillis() - longStartTime;
            seconds = (int) (millis / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 500);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldtestament);
        getActionBar().hide();
        initialize();
        initializeLogic();
    }

    private void  initialize() {
        /*linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        linear4 = (LinearLayout) findViewById(R.id.linear4);
        linear5 = (LinearLayout) findViewById(R.id.linear5);
        linear6 = (LinearLayout) findViewById(R.id.linear6);
        linear7 = (LinearLayout) findViewById(R.id.linear7);
        linear8 = (LinearLayout) findViewById(R.id.linear8);
        linear9 = (LinearLayout) findViewById(R.id.linear9);
        linear10 = (LinearLayout) findViewById(R.id.linear10);
        linear11 = (LinearLayout) findViewById(R.id.linear11);
        imageview8 = (ImageView) findViewById(R.id.imageview8);
        bibooks = (TextView) findViewById(R.id.bibooks);
        imageview7 = (ImageView) findViewById(R.id.imageview7);
        image1 = (LinearLayout) findViewById(R.id.image1);
        image2 = (LinearLayout) findViewById(R.id.image2);
        image3 = (LinearLayout) findViewById(R.id.image3);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        imageview3 = (ImageView) findViewById(R.id.imageview3);
        image4 = (LinearLayout) findViewById(R.id.image4);
        image5 = (LinearLayout) findViewById(R.id.image5);
        image6 = (LinearLayout) findViewById(R.id.image6);
        imageview4 = (ImageView) findViewById(R.id.imageview4);
        imageview5 = (ImageView) findViewById(R.id.imageview5);
        imageview6 = (ImageView) findViewById(R.id.imageview6);*/

        level = (TextView) findViewById(R.id.level);
        category = (TextView) findViewById(R.id.category);
        username = (TextView) findViewById(R.id.username);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        txtScore = (TextView) findViewById(R.id.score);

        imageview1 = (Button) findViewById(R.id.imageview1);
        imageview2 = (Button) findViewById(R.id.imageview2);
        imageview3 = (Button) findViewById(R.id.imageview3);
        imageview4 = (Button) findViewById(R.id.imageview4);
        imageview5 = (Button) findViewById(R.id.imageview5);
        imageview6 = (Button) findViewById(R.id.imageview6);
        imageview7 = (Button) findViewById(R.id.imageview7);
        imageview8 = (Button) findViewById(R.id.imageview8);
        imageview9 = (Button) findViewById(R.id.imageview9);
        imageview10 = (Button) findViewById(R.id.imageview10);
        imageview11 = (Button) findViewById(R.id.imageview11);
        imageview12 = (Button) findViewById(R.id.imageview12);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        startbutton = (Button) findViewById(R.id.startbutton);
        stopbutton = (Button) findViewById(R.id.stopbutton);
        home = (Button) findViewById(R.id.home);
        newgame = (Button) findViewById(R.id.newgame);



        vibration = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        dialog = new AlertDialog.Builder(this);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                page.setClass(getApplicationContext(), MainActivity.class);
                startActivity(page);
                finish();
            }
        });
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                page.setClass(getApplicationContext(), OldtestamentActivity.class);
                startActivity(page);
                finish();
            }
        });
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                startbutton.setEnabled(false);
                startbutton.setVisibility(View.GONE);
                gridLayout.setVisibility(View.VISIBLE);
                stopbutton.setEnabled(true);
                startbutton.setBackgroundColor(0xFF757575);
                stopbutton.setBackgroundColor(0xFFF44336);

                if ((game_level == 1) || ((game_level == 3) || (game_level == 4))) {

                    imageview1.setVisibility(View.INVISIBLE);
                    imageview2.setVisibility(View.INVISIBLE);
                    imageview3.setVisibility(View.INVISIBLE);
                    imageview4.setVisibility(View.INVISIBLE);
                    imageview5.setVisibility(View.VISIBLE);
                    imageview6.setVisibility(View.VISIBLE);
                    imageview7.setVisibility(View.VISIBLE);
                    imageview8.setVisibility(View.VISIBLE);
                    imageview9.setVisibility(View.VISIBLE);
                    imageview10.setVisibility(View.INVISIBLE);
                    imageview11.setVisibility(View.INVISIBLE);
                    imageview12.setVisibility(View.INVISIBLE);

                    srcList.clear();
                    initValue = 0;
                    score = 5;
                    txtScore.setText(String.valueOf(score));
                    for(int _repeat34 = 0; _repeat34 < (int)(5); _repeat34++) {
                        srcList.add(Double.valueOf(initValue));
                        initValue++;
                    }
                    answersList.clear();
                    for(int _repeat39 = 0; _repeat39 < (int)(5); _repeat39++) {
                        srcIndex = getRandom((int)(0), (int)(srcList.size() - 1));
                        answersList.add(Double.valueOf(srcList.get((int)(srcIndex)).doubleValue()));
                        srcList.remove((int)(srcIndex));
                    }
                    answerIndex = 0;
                    for(int _repeat50 = 0; _repeat50 < (int)(5); _repeat50++) {
                        _displayBooks(answerIndex, answersList.get((int)(answerIndex)).doubleValue());
                        answerIndex++;
                    }
                    prevSelected = -1;
                    isStarted = false;
                }
                if ((game_level == 2) || (game_level == 5)) {

                    imageview1.setVisibility(View.VISIBLE);
                    imageview2.setVisibility(View.VISIBLE);
                    imageview3.setVisibility(View.VISIBLE);
                    imageview4.setVisibility(View.VISIBLE);
                    imageview5.setVisibility(View.VISIBLE);
                    imageview6.setVisibility(View.VISIBLE);
                    imageview7.setVisibility(View.VISIBLE);
                    imageview8.setVisibility(View.VISIBLE);
                    imageview9.setVisibility(View.VISIBLE);
                    imageview10.setVisibility(View.VISIBLE);
                    imageview11.setVisibility(View.VISIBLE);
                    imageview12.setVisibility(View.VISIBLE);

                    srcList.clear();
                    initValue = 0;
                    score = 12;
                    txtScore.setText(String.valueOf(score));
                    for(int _repeat62 = 0; _repeat62 < (int)(12); _repeat62++) {
                        srcList.add(Double.valueOf(initValue));
                        initValue++;
                    }
                    answersList.clear();
                    for(int _repeat67 = 0; _repeat67 < (int)(12); _repeat67++) {
                        srcIndex = getRandom((int)(0), (int)(srcList.size() - 1));
                        answersList.add(Double.valueOf(srcList.get((int)(srcIndex)).doubleValue()));
                        srcList.remove((int)(srcIndex));
                    }
                    answerIndex = 0;
                    for(int _repeat78 = 0; _repeat78 < (int)(12); _repeat78++) {
                        _displayBooks(answerIndex, answersList.get((int)(answerIndex)).doubleValue());
                        answerIndex++;
                    }
                    prevSelected = -1;
                    isStarted = false;
                }

                timerTextView.setTextColor(getResources().getColor(R.color.red));
                longStartTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);
            }
        });
        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                stopbutton.setEnabled(false);
                startbutton.setEnabled(true);
                startbutton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.GONE);
                imageview1.setEnabled(false);
                imageview2.setEnabled(false);
                imageview3.setEnabled(false);
                imageview4.setEnabled(false);
                imageview5.setEnabled(false);
                startbutton.setBackgroundColor(getResources().getColor(R.color.green));
                stopbutton.setBackgroundColor(0xFF757575);

                imageview1.setEnabled(false);
                imageview2.setEnabled(false);
                imageview3.setEnabled(false);
                imageview4.setEnabled(false);
                imageview5.setEnabled(false);
                imageview1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                imageview2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                imageview3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                imageview4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                imageview5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                if ((game_level == 2) || (game_level == 5)) { imageview6.setEnabled(false);
                    imageview6.setEnabled(false);
                    imageview7.setEnabled(false);
                    imageview8.setEnabled(false);
                    imageview9.setEnabled(false);
                    imageview10.setEnabled(false);
                    imageview11.setEnabled(false);
                    imageview12.setEnabled(false);
                    imageview6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                    imageview7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                    imageview8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                    imageview9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                    imageview10.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                    imageview11.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                    imageview12.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                }

                timerTextView.setTextColor(getResources().getColor(R.color.black));
                timerHandler.removeCallbacks(timerRunnable);
            }
        });
        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(0)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview1.setEnabled(false);
                    imageview1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(1)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview2.setEnabled(false);
                    imageview2.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(2)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview3.setEnabled(false);
                    imageview3.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(3)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview4.setEnabled(false);
                    imageview4.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(4)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview5.setEnabled(false);
                    imageview5.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(5)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview6.setEnabled(false);
                    imageview6.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(6)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview7.setEnabled(false);
                    imageview7.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(7)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview8.setEnabled(false);
                    imageview8.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(8)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview9.setEnabled(false);
                    imageview9.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(9)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview10.setEnabled(false);
                    imageview10.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview10.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview10.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(10)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview11.setEnabled(false);
                    imageview11.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview11.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview11.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });
        imageview12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                currentSelected = answersList.get((int)(11)).doubleValue();
                if ((prevSelected + 1) == currentSelected) {
                    if (!isStarted) {
                        isStarted = true;
                        startTime = Calendar.getInstance();
                    }
                    imageview12.setEnabled(false);
                    imageview12.setBackgroundColor(getResources().getColor(R.color.green));
                    prevSelected = currentSelected;
                    _decisionSuccess();
                }
                else {
                    if (0 < score) {
                        score = score - 0.5;
                        txtScore.setText(String.valueOf(score));
                    }
                    imageview12.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                    vibration.vibrate((long)(500));
                    new CountDownTimer(1000, 50) {
                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onFinish() {
                            imageview12.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        }
                    }.start();
                }
            }
        });

    }

    private void  initializeLogic() {
        stopbutton.setEnabled(false);
        imageview1.setEnabled(false);
        imageview2.setEnabled(false);
        imageview3.setEnabled(false);
        imageview4.setEnabled(false);
        imageview5.setEnabled(false);
        imageview6.setEnabled(false);
        imageview7.setEnabled(false);
        imageview8.setEnabled(false);
        imageview9.setEnabled(false);
        imageview10.setEnabled(false);
        imageview11.setEnabled(false);
        imageview12.setEnabled(false);
        startbutton.setEnabled(true);
        startbutton.setBackgroundColor(getResources().getColor(R.color.green));
        startbutton.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.GONE);
        stopbutton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
        level.setText("LEVEL ".concat("1"));
        game_level = 1;
        total_score = 0;
        total_time = 0;

        userDetailMob = new UserDetailMobImpl(this);
        userDetail = new UserDetail();
        arrayList = new ArrayList<>();

        userDetailMob.open();
        arrayList = userDetailMob.fetchAllUsers();

        for(int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i).getActive().equals("1")){
                userDetail = arrayList.get(i);

                username.setText(userDetail.getUsername());
                break;
            }
        }
        userDetailMob.close();

    }


    private void _displayBooks (final double _index, final double _value) {
        if ((game_level == 1) || ((game_level == 3) || (game_level == 4))) {
            imageview1.setVisibility(View.VISIBLE);
            imageview2.setVisibility(View.VISIBLE);
            imageview3.setVisibility(View.VISIBLE);
            imageview4.setVisibility(View.VISIBLE);
            imageview5.setVisibility(View.VISIBLE);
            GridLayout.LayoutParams params = (GridLayout.LayoutParams) imageview5.getLayoutParams();
            params.columnSpec = GridLayout.spec(0, 2);
            params.setGravity(Gravity.CENTER);
            //params.gravity = Gravity.CENTER;
            imageview5.setLayoutParams(params);
            imageview6.setVisibility(View.GONE);
            imageview7.setVisibility(View.GONE);
            imageview8.setVisibility(View.GONE);
            imageview9.setVisibility(View.GONE);
            imageview10.setVisibility(View.GONE);
            imageview11.setVisibility(View.GONE);
            imageview12.setVisibility(View.GONE);
            if (_index == 0) {
                _displayBook1(_value);
            }
            if (_index == 1) {
                _displayBook2(_value);
            }
            if (_index == 2) {
                _displayBook3(_value);
            }
            if (_index == 3) {
                _displayBook4(_value);
            }
            if (_index == 4) {
                _displayBook5(_value);
            }
        }
        if ((game_level == 2) || (game_level == 5)) {
            GridLayout.LayoutParams params = (GridLayout.LayoutParams) imageview5.getLayoutParams();
            params.columnSpec = GridLayout.spec(0, 1);
            params.setGravity(Gravity.LEFT);
            imageview5.setLayoutParams(params);
            imageview5.setVisibility(View.VISIBLE);
            imageview6.setVisibility(View.VISIBLE);
            imageview7.setVisibility(View.VISIBLE);
            imageview8.setVisibility(View.VISIBLE);
            imageview9.setVisibility(View.VISIBLE);
            imageview10.setVisibility(View.VISIBLE);
            imageview11.setVisibility(View.VISIBLE);
            imageview12.setVisibility(View.VISIBLE);
            if (_index == 0) {
                _displayBook1(_value);
            }
            if (_index == 1) {
                _displayBook2(_value);
            }
            if (_index == 2) {
                _displayBook3(_value);
            }
            if (_index == 3) {
                _displayBook4(_value);
            }
            if (_index == 4) {
                _displayBook5(_value);
            }
            if (_index == 5) {
                _displayBook6(_value);
            }
            if (_index == 6) {
                _displayBook7(_value);
            }
            if (_index == 7) {
                _displayBook8(_value);
            }
            if (_index == 8) {
                _displayBook9(_value);
            }
            if (_index == 9) {
                _displayBook10(_value);
            }
            if (_index == 10) {
                _displayBook11(_value);
            }
            if (_index == 11) {
                _displayBook12(_value);
            }
        }
    }
    private void _displayBook1 (final double _value) {
        imageview1.setEnabled(true);
        imageview1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview1.setText("Genesis");
            }
            if (_value == 1) {
                imageview1.setText("Exodus");
            }
            if (_value == 2) {
                imageview1.setText("Leviticus");
            }
            if (_value == 3) {
                imageview1.setText("Numbers");
            }
            if (_value == 4) {
                imageview1.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview1.setText("Joshua");
            }
            if (_value == 1) {
                imageview1.setText("Judges");
            }
            if (_value == 2) {
                imageview1.setText("Ruth");
            }
            if (_value == 3) {
                imageview1.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview1.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview1.setText("1 Kings");
            }
            if (_value == 6) {
                imageview1.setText("2 Kings");
            }
            if (_value == 7) {
                imageview1.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview1.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview1.setText("Ezra");
            }
            if (_value == 10) {
                imageview1.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview1.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview1.setText("Job");
            }
            if (_value == 1) {
                imageview1.setText("Psalms");
            }
            if (_value == 2) {
                imageview1.setText("Proverbs");
            }
            if (_value == 3) {
                imageview1.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview1.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview1.setText("Isaiah");
            }
            if (_value == 1) {
                imageview1.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview1.setText("Lamentations");
            }
            if (_value == 3) {
                imageview1.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview1.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview1.setText("Hosea");
            }
            if (_value == 1) {
                imageview1.setText("Joel");
            }
            if (_value == 2) {
                imageview1.setText("Amos");
            }
            if (_value == 3) {
                imageview1.setText("Obadiah");
            }
            if (_value == 4) {
                imageview1.setText("Jonah");
            }
            if (_value == 5) {
                imageview1.setText("Micah");
            }
            if (_value == 6) {
                imageview1.setText("Nahum");
            }
            if (_value == 7) {
                imageview1.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview1.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview1.setText("Haggai");
            }
            if (_value == 10) {
                imageview1.setText("Zechariah");
            }
            if (_value == 11) {
                imageview1.setText("Malachi");
            }
        }
    }
    private void _displayBook2 (final double _value) {
        imageview2.setEnabled(true);
        imageview2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview2.setText("Genesis");
            }
            if (_value == 1) {
                imageview2.setText("Exodus");
            }
            if (_value == 2) {
                imageview2.setText("Leviticus");
            }
            if (_value == 3) {
                imageview2.setText("Numbers");
            }
            if (_value == 4) {
                imageview2.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview2.setText("Joshua");
            }
            if (_value == 1) {
                imageview2.setText("Judges");
            }
            if (_value == 2) {
                imageview2.setText("Ruth");
            }
            if (_value == 3) {
                imageview2.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview2.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview2.setText("1 Kings");
            }
            if (_value == 6) {
                imageview2.setText("2 Kings");
            }
            if (_value == 7) {
                imageview2.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview2.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview2.setText("Ezra");
            }
            if (_value == 10) {
                imageview2.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview2.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview2.setText("Job");
            }
            if (_value == 1) {
                imageview2.setText("Psalms");
            }
            if (_value == 2) {
                imageview2.setText("Proverbs");
            }
            if (_value == 3) {
                imageview2.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview2.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview2.setText("Isaiah");
            }
            if (_value == 1) {
                imageview2.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview2.setText("Lamentations");
            }
            if (_value == 3) {
                imageview2.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview2.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview2.setText("Hosea");
            }
            if (_value == 1) {
                imageview2.setText("Joel");
            }
            if (_value == 2) {
                imageview2.setText("Amos");
            }
            if (_value == 3) {
                imageview2.setText("Obadiah");
            }
            if (_value == 4) {
                imageview2.setText("Jonah");
            }
            if (_value == 5) {
                imageview2.setText("Micah");
            }
            if (_value == 6) {
                imageview2.setText("Nahum");
            }
            if (_value == 7) {
                imageview2.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview2.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview2.setText("Haggai");
            }
            if (_value == 10) {
                imageview2.setText("Zechariah");
            }
            if (_value == 11) {
                imageview2.setText("Malachi");
            }
        }
    }
    private void _displayBook3 (final double _value) {
        imageview3.setEnabled(true);
        imageview3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview3.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview3.setText("Genesis");
            }
            if (_value == 1) {
                imageview3.setText("Exodus");
            }
            if (_value == 2) {
                imageview3.setText("Leviticus");
            }
            if (_value == 3) {
                imageview3.setText("Numbers");
            }
            if (_value == 4) {
                imageview3.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview3.setText("Joshua");
            }
            if (_value == 1) {
                imageview3.setText("Judges");
            }
            if (_value == 2) {
                imageview3.setText("Ruth");
            }
            if (_value == 3) {
                imageview3.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview3.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview3.setText("1 Kings");
            }
            if (_value == 6) {
                imageview3.setText("2 Kings");
            }
            if (_value == 7) {
                imageview3.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview3.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview3.setText("Ezra");
            }
            if (_value == 10) {
                imageview3.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview3.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview3.setText("Job");
            }
            if (_value == 1) {
                imageview3.setText("Psalms");
            }
            if (_value == 2) {
                imageview3.setText("Proverbs");
            }
            if (_value == 3) {
                imageview3.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview3.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview3.setText("Isaiah");
            }
            if (_value == 1) {
                imageview3.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview3.setText("Lamentations");
            }
            if (_value == 3) {
                imageview3.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview3.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview3.setText("Hosea");
            }
            if (_value == 1) {
                imageview3.setText("Joel");
            }
            if (_value == 2) {
                imageview3.setText("Amos");
            }
            if (_value == 3) {
                imageview3.setText("Obadiah");
            }
            if (_value == 4) {
                imageview3.setText("Jonah");
            }
            if (_value == 5) {
                imageview3.setText("Micah");
            }
            if (_value == 6) {
                imageview3.setText("Nahum");
            }
            if (_value == 7) {
                imageview3.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview3.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview3.setText("Haggai");
            }
            if (_value == 10) {
                imageview3.setText("Zechariah");
            }
            if (_value == 11) {
                imageview3.setText("Malachi");
            }
        }
    }
    private void _displayBook4 (final double _value) {
        imageview4.setEnabled(true);
        imageview4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview4.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview4.setText("Genesis");
            }
            if (_value == 1) {
                imageview4.setText("Exodus");
            }
            if (_value == 2) {
                imageview4.setText("Leviticus");
            }
            if (_value == 3) {
                imageview4.setText("Numbers");
            }
            if (_value == 4) {
                imageview4.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview4.setText("Joshua");
            }
            if (_value == 1) {
                imageview4.setText("Judges");
            }
            if (_value == 2) {
                imageview4.setText("Ruth");
            }
            if (_value == 3) {
                imageview4.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview4.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview4.setText("1 Kings");
            }
            if (_value == 6) {
                imageview4.setText("2 Kings");
            }
            if (_value == 7) {
                imageview4.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview4.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview4.setText("Ezra");
            }
            if (_value == 10) {
                imageview4.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview4.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview4.setText("Job");
            }
            if (_value == 1) {
                imageview4.setText("Psalms");
            }
            if (_value == 2) {
                imageview4.setText("Proverbs");
            }
            if (_value == 3) {
                imageview4.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview4.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview4.setText("Isaiah");
            }
            if (_value == 1) {
                imageview4.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview4.setText("Lamentations");
            }
            if (_value == 3) {
                imageview4.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview4.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview4.setText("Hosea");
            }
            if (_value == 1) {
                imageview4.setText("Joel");
            }
            if (_value == 2) {
                imageview4.setText("Amos");
            }
            if (_value == 3) {
                imageview4.setText("Obadiah");
            }
            if (_value == 4) {
                imageview4.setText("Jonah");
            }
            if (_value == 5) {
                imageview4.setText("Micah");
            }
            if (_value == 6) {
                imageview4.setText("Nahum");
            }
            if (_value == 7) {
                imageview4.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview4.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview4.setText("Haggai");
            }
            if (_value == 10) {
                imageview4.setText("Zechariah");
            }
            if (_value == 11) {
                imageview4.setText("Malachi");
            }
        }
    }
    private void _displayBook5 (final double _value) {
        imageview5.setEnabled(true);
        imageview5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview5.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview5.setText("Genesis");
            }
            if (_value == 1) {
                imageview5.setText("Exodus");
            }
            if (_value == 2) {
                imageview5.setText("Leviticus");
            }
            if (_value == 3) {
                imageview5.setText("Numbers");
            }
            if (_value == 4) {
                imageview5.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview5.setText("Joshua");
            }
            if (_value == 1) {
                imageview5.setText("Judges");
            }
            if (_value == 2) {
                imageview5.setText("Ruth");
            }
            if (_value == 3) {
                imageview5.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview5.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview5.setText("1 Kings");
            }
            if (_value == 6) {
                imageview5.setText("2 Kings");
            }
            if (_value == 7) {
                imageview5.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview5.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview5.setText("Ezra");
            }
            if (_value == 10) {
                imageview5.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview5.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview5.setText("Job");
            }
            if (_value == 1) {
                imageview5.setText("Psalms");
            }
            if (_value == 2) {
                imageview5.setText("Proverbs");
            }
            if (_value == 3) {
                imageview5.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview5.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview5.setText("Isaiah");
            }
            if (_value == 1) {
                imageview5.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview5.setText("Lamentations");
            }
            if (_value == 3) {
                imageview5.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview5.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview5.setText("Hosea");
            }
            if (_value == 1) {
                imageview5.setText("Joel");
            }
            if (_value == 2) {
                imageview5.setText("Amos");
            }
            if (_value == 3) {
                imageview5.setText("Obadiah");
            }
            if (_value == 4) {
                imageview5.setText("Jonah");
            }
            if (_value == 5) {
                imageview5.setText("Micah");
            }
            if (_value == 6) {
                imageview5.setText("Nahum");
            }
            if (_value == 7) {
                imageview5.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview5.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview5.setText("Haggai");
            }
            if (_value == 10) {
                imageview5.setText("Zechariah");
            }
            if (_value == 11) {
                imageview5.setText("Malachi");
            }
        }
    }
    private void _displayBook6 (final double _value) {
        imageview6.setEnabled(true);
        imageview6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview6.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview6.setText("Genesis");
            }
            if (_value == 1) {
                imageview6.setText("Exodus");
            }
            if (_value == 2) {
                imageview6.setText("Leviticus");
            }
            if (_value == 3) {
                imageview6.setText("Numbers");
            }
            if (_value == 4) {
                imageview6.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview6.setText("Joshua");
            }
            if (_value == 1) {
                imageview6.setText("Judges");
            }
            if (_value == 2) {
                imageview6.setText("Ruth");
            }
            if (_value == 3) {
                imageview6.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview6.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview6.setText("1 Kings");
            }
            if (_value == 6) {
                imageview6.setText("2 Kings");
            }
            if (_value == 7) {
                imageview6.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview6.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview6.setText("Ezra");
            }
            if (_value == 10) {
                imageview6.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview6.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview6.setText("Job");
            }
            if (_value == 1) {
                imageview6.setText("Psalms");
            }
            if (_value == 2) {
                imageview6.setText("Proverbs");
            }
            if (_value == 3) {
                imageview6.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview6.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview6.setText("Isaiah");
            }
            if (_value == 1) {
                imageview6.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview6.setText("Lamentations");
            }
            if (_value == 3) {
                imageview6.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview6.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview6.setText("Hosea");
            }
            if (_value == 1) {
                imageview6.setText("Joel");
            }
            if (_value == 2) {
                imageview6.setText("Amos");
            }
            if (_value == 3) {
                imageview6.setText("Obadiah");
            }
            if (_value == 4) {
                imageview6.setText("Jonah");
            }
            if (_value == 5) {
                imageview6.setText("Micah");
            }
            if (_value == 6) {
                imageview6.setText("Nahum");
            }
            if (_value == 7) {
                imageview6.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview6.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview6.setText("Haggai");
            }
            if (_value == 10) {
                imageview6.setText("Zechariah");
            }
            if (_value == 11) {
                imageview6.setText("Malachi");
            }
        }
    }
    private void _displayBook7 (final double _value) {
        imageview7.setEnabled(true);
        imageview7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview7.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview7.setText("Genesis");
            }
            if (_value == 1) {
                imageview7.setText("Exodus");
            }
            if (_value == 2) {
                imageview7.setText("Leviticus");
            }
            if (_value == 3) {
                imageview7.setText("Numbers");
            }
            if (_value == 4) {
                imageview7.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview7.setText("Joshua");
            }
            if (_value == 1) {
                imageview7.setText("Judges");
            }
            if (_value == 2) {
                imageview7.setText("Ruth");
            }
            if (_value == 3) {
                imageview7.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview7.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview7.setText("1 Kings");
            }
            if (_value == 6) {
                imageview7.setText("2 Kings");
            }
            if (_value == 7) {
                imageview7.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview7.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview7.setText("Ezra");
            }
            if (_value == 10) {
                imageview7.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview7.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview7.setText("Job");
            }
            if (_value == 1) {
                imageview7.setText("Psalms");
            }
            if (_value == 2) {
                imageview7.setText("Proverbs");
            }
            if (_value == 3) {
                imageview7.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview7.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview7.setText("Isaiah");
            }
            if (_value == 1) {
                imageview7.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview7.setText("Lamentations");
            }
            if (_value == 3) {
                imageview7.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview7.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview7.setText("Hosea");
            }
            if (_value == 1) {
                imageview7.setText("Joel");
            }
            if (_value == 2) {
                imageview7.setText("Amos");
            }
            if (_value == 3) {
                imageview7.setText("Obadiah");
            }
            if (_value == 4) {
                imageview7.setText("Jonah");
            }
            if (_value == 5) {
                imageview7.setText("Micah");
            }
            if (_value == 6) {
                imageview7.setText("Nahum");
            }
            if (_value == 7) {
                imageview7.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview7.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview7.setText("Haggai");
            }
            if (_value == 10) {
                imageview7.setText("Zechariah");
            }
            if (_value == 11) {
                imageview7.setText("Malachi");
            }
        }
    }
    private void _displayBook8 (final double _value) {
        imageview8.setEnabled(true);
        imageview8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview8.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview8.setText("Genesis");
            }
            if (_value == 1) {
                imageview8.setText("Exodus");
            }
            if (_value == 2) {
                imageview8.setText("Leviticus");
            }
            if (_value == 3) {
                imageview8.setText("Numbers");
            }
            if (_value == 4) {
                imageview8.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview8.setText("Joshua");
            }
            if (_value == 1) {
                imageview8.setText("Judges");
            }
            if (_value == 2) {
                imageview8.setText("Ruth");
            }
            if (_value == 3) {
                imageview8.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview8.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview8.setText("1 Kings");
            }
            if (_value == 6) {
                imageview8.setText("2 Kings");
            }
            if (_value == 7) {
                imageview8.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview8.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview8.setText("Ezra");
            }
            if (_value == 10) {
                imageview8.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview8.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview8.setText("Job");
            }
            if (_value == 1) {
                imageview8.setText("Psalms");
            }
            if (_value == 2) {
                imageview8.setText("Proverbs");
            }
            if (_value == 3) {
                imageview8.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview8.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview8.setText("Isaiah");
            }
            if (_value == 1) {
                imageview8.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview8.setText("Lamentations");
            }
            if (_value == 3) {
                imageview8.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview8.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview8.setText("Hosea");
            }
            if (_value == 1) {
                imageview8.setText("Joel");
            }
            if (_value == 2) {
                imageview8.setText("Amos");
            }
            if (_value == 3) {
                imageview8.setText("Obadiah");
            }
            if (_value == 4) {
                imageview8.setText("Jonah");
            }
            if (_value == 5) {
                imageview8.setText("Micah");
            }
            if (_value == 6) {
                imageview8.setText("Nahum");
            }
            if (_value == 7) {
                imageview8.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview8.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview8.setText("Haggai");
            }
            if (_value == 10) {
                imageview8.setText("Zechariah");
            }
            if (_value == 11) {
                imageview8.setText("Malachi");
            }
        }
    }
    private void _displayBook9 (final double _value) {
        imageview9.setEnabled(true);
        imageview9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview9.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview9.setText("Genesis");
            }
            if (_value == 1) {
                imageview9.setText("Exodus");
            }
            if (_value == 2) {
                imageview9.setText("Leviticus");
            }
            if (_value == 3) {
                imageview9.setText("Numbers");
            }
            if (_value == 4) {
                imageview9.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview9.setText("Joshua");
            }
            if (_value == 1) {
                imageview9.setText("Judges");
            }
            if (_value == 2) {
                imageview9.setText("Ruth");
            }
            if (_value == 3) {
                imageview9.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview9.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview9.setText("1 Kings");
            }
            if (_value == 6) {
                imageview9.setText("2 Kings");
            }
            if (_value == 7) {
                imageview9.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview9.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview9.setText("Ezra");
            }
            if (_value == 10) {
                imageview9.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview9.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview9.setText("Job");
            }
            if (_value == 1) {
                imageview9.setText("Psalms");
            }
            if (_value == 2) {
                imageview9.setText("Proverbs");
            }
            if (_value == 3) {
                imageview9.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview9.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview9.setText("Isaiah");
            }
            if (_value == 1) {
                imageview9.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview9.setText("Lamentations");
            }
            if (_value == 3) {
                imageview9.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview9.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview9.setText("Hosea");
            }
            if (_value == 1) {
                imageview9.setText("Joel");
            }
            if (_value == 2) {
                imageview9.setText("Amos");
            }
            if (_value == 3) {
                imageview9.setText("Obadiah");
            }
            if (_value == 4) {
                imageview9.setText("Jonah");
            }
            if (_value == 5) {
                imageview9.setText("Micah");
            }
            if (_value == 6) {
                imageview9.setText("Nahum");
            }
            if (_value == 7) {
                imageview9.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview9.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview9.setText("Haggai");
            }
            if (_value == 10) {
                imageview9.setText("Zechariah");
            }
            if (_value == 11) {
                imageview9.setText("Malachi");
            }
        }
    }
    private void _displayBook10 (final double _value) {
        imageview10.setEnabled(true);
        imageview10.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview10.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview10.setText("Genesis");
            }
            if (_value == 1) {
                imageview10.setText("Exodus");
            }
            if (_value == 2) {
                imageview10.setText("Leviticus");
            }
            if (_value == 3) {
                imageview10.setText("Numbers");
            }
            if (_value == 4) {
                imageview10.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview10.setText("Joshua");
            }
            if (_value == 1) {
                imageview10.setText("Judges");
            }
            if (_value == 2) {
                imageview10.setText("Ruth");
            }
            if (_value == 3) {
                imageview10.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview10.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview10.setText("1 Kings");
            }
            if (_value == 6) {
                imageview10.setText("2 Kings");
            }
            if (_value == 7) {
                imageview10.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview10.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview10.setText("Ezra");
            }
            if (_value == 10) {
                imageview10.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview10.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview10.setText("Job");
            }
            if (_value == 1) {
                imageview10.setText("Psalms");
            }
            if (_value == 2) {
                imageview10.setText("Proverbs");
            }
            if (_value == 3) {
                imageview10.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview10.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview10.setText("Isaiah");
            }
            if (_value == 1) {
                imageview10.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview10.setText("Lamentations");
            }
            if (_value == 3) {
                imageview10.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview10.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview10.setText("Hosea");
            }
            if (_value == 1) {
                imageview10.setText("Joel");
            }
            if (_value == 2) {
                imageview10.setText("Amos");
            }
            if (_value == 3) {
                imageview10.setText("Obadiah");
            }
            if (_value == 4) {
                imageview10.setText("Jonah");
            }
            if (_value == 5) {
                imageview10.setText("Micah");
            }
            if (_value == 6) {
                imageview10.setText("Nahum");
            }
            if (_value == 7) {
                imageview10.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview10.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview10.setText("Haggai");
            }
            if (_value == 10) {
                imageview10.setText("Zechariah");
            }
            if (_value == 11) {
                imageview10.setText("Malachi");
            }
        }
    }
    private void _displayBook11 (final double _value) {
        imageview11.setEnabled(true);
        imageview11.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview11.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview11.setText("Genesis");
            }
            if (_value == 1) {
                imageview11.setText("Exodus");
            }
            if (_value == 2) {
                imageview11.setText("Leviticus");
            }
            if (_value == 3) {
                imageview11.setText("Numbers");
            }
            if (_value == 4) {
                imageview11.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview11.setText("Joshua");
            }
            if (_value == 1) {
                imageview11.setText("Judges");
            }
            if (_value == 2) {
                imageview11.setText("Ruth");
            }
            if (_value == 3) {
                imageview11.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview11.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview11.setText("1 Kings");
            }
            if (_value == 6) {
                imageview11.setText("2 Kings");
            }
            if (_value == 7) {
                imageview11.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview11.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview11.setText("Ezra");
            }
            if (_value == 10) {
                imageview11.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview11.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview11.setText("Job");
            }
            if (_value == 1) {
                imageview11.setText("Psalms");
            }
            if (_value == 2) {
                imageview11.setText("Proverbs");
            }
            if (_value == 3) {
                imageview11.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview11.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview11.setText("Isaiah");
            }
            if (_value == 1) {
                imageview11.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview11.setText("Lamentations");
            }
            if (_value == 3) {
                imageview11.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview11.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview11.setText("Hosea");
            }
            if (_value == 1) {
                imageview11.setText("Joel");
            }
            if (_value == 2) {
                imageview11.setText("Amos");
            }
            if (_value == 3) {
                imageview11.setText("Obadiah");
            }
            if (_value == 4) {
                imageview11.setText("Jonah");
            }
            if (_value == 5) {
                imageview11.setText("Micah");
            }
            if (_value == 6) {
                imageview11.setText("Nahum");
            }
            if (_value == 7) {
                imageview11.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview11.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview11.setText("Haggai");
            }
            if (_value == 10) {
                imageview11.setText("Zechariah");
            }
            if (_value == 11) {
                imageview11.setText("Malachi");
            }
        }
    }
    private void _displayBook12 (final double _value) {
        imageview12.setEnabled(true);
        imageview12.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview12.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview12.setText("Genesis");
            }
            if (_value == 1) {
                imageview12.setText("Exodus");
            }
            if (_value == 2) {
                imageview12.setText("Leviticus");
            }
            if (_value == 3) {
                imageview12.setText("Numbers");
            }
            if (_value == 4) {
                imageview12.setText("Deutoronomy");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview12.setText("Joshua");
            }
            if (_value == 1) {
                imageview12.setText("Judges");
            }
            if (_value == 2) {
                imageview12.setText("Ruth");
            }
            if (_value == 3) {
                imageview12.setText("1 Samuel");
            }
            if (_value == 4) {
                imageview12.setText("2 Samuel");
            }
            if (_value == 5) {
                imageview12.setText("1 Kings");
            }
            if (_value == 6) {
                imageview12.setText("2 Kings");
            }
            if (_value == 7) {
                imageview12.setText("1 Chronicles");
            }
            if (_value == 8) {
                imageview12.setText("2 Chronicles");
            }
            if (_value == 9) {
                imageview12.setText("Ezra");
            }
            if (_value == 10) {
                imageview12.setText("Nehemiah");
            }
            if (_value == 11) {
                imageview12.setText("Esther");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview12.setText("Job");
            }
            if (_value == 1) {
                imageview12.setText("Psalms");
            }
            if (_value == 2) {
                imageview12.setText("Proverbs");
            }
            if (_value == 3) {
                imageview12.setText("Ecclesiastes");
            }
            if (_value == 4) {
                imageview12.setText("Song of Solomon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview12.setText("Isaiah");
            }
            if (_value == 1) {
                imageview12.setText("Jeremiah");
            }
            if (_value == 2) {
                imageview12.setText("Lamentations");
            }
            if (_value == 3) {
                imageview12.setText("Ezekiel");
            }
            if (_value == 4) {
                imageview12.setText("Daniel");
            }
        }
        if (game_level == 5) {
            if (_value == 0) {
                imageview12.setText("Hosea");
            }
            if (_value == 1) {
                imageview12.setText("Joel");
            }
            if (_value == 2) {
                imageview12.setText("Amos");
            }
            if (_value == 3) {
                imageview12.setText("Obadiah");
            }
            if (_value == 4) {
                imageview12.setText("Jonah");
            }
            if (_value == 5) {
                imageview12.setText("Micah");
            }
            if (_value == 6) {
                imageview12.setText("Nahum");
            }
            if (_value == 7) {
                imageview12.setText("Habakkuk");
            }
            if (_value == 8) {
                imageview12.setText("Zephaniah");
            }
            if (_value == 9) {
                imageview12.setText("Haggai");
            }
            if (_value == 10) {
                imageview12.setText("Zechariah");
            }
            if (_value == 11) {
                imageview12.setText("Malachi");
            }
        }
    }
    private void _decisionSuccess () {
        if ((prevSelected == 4) && ((game_level == 1) || ((game_level == 3) || (game_level == 4)))) {
            startbutton.setEnabled(true);
            startbutton.setVisibility(View.VISIBLE);
            gridLayout.setVisibility(View.GONE);
            stopbutton.setEnabled(false);
            startbutton.setBackgroundColor(getResources().getColor(R.color.green));
            stopbutton.setBackgroundColor(0xFF757575);
            endTime = Calendar.getInstance();
            currentTime = (long)(endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;

            timerTextView.setTextColor(getResources().getColor(R.color.black));
            timerHandler.removeCallbacks(timerRunnable);

            /*dialog.setTitle("SCOREBOARD:");
            dialog.setMessage("Your Time: ".concat(String.valueOf((long)(currentTime)).concat("s          ¦          ".concat("Your Score: ".concat(String.valueOf((long)(score)))))));
            dialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface _dialog, int _which) {

                }
            });
            dialog.create().show();*/

            totalMinutes = totalMinutes + minutes;
            totalSeconds = totalSeconds + seconds;
            for(int _repeat129 = 0; _repeat129 < (int)(60); _repeat129++) {
                if (59 < totalSeconds) {
                    totalSeconds = totalSeconds - 60;
                    totalMinutes++;
                }
            }
            /*ScoreboardDialog scoreboardDialog = new ScoreboardDialog(this, String.format("%d:%02d", minutes, seconds),
                    String.valueOf((long)(score)));
            scoreboardDialog.show();*/

            if ((bestTime == 0) || (currentTime < bestTime)) {
                bestTime = currentTime;
            }
            if ((bestScore == 0) || (bestScore < score)) {
                bestScore = score;
            }
            game_level++;
            if (game_level == 2) {
                level.setText("LEVEL ".concat("2"));
                category.setText("HISTORY ");
            }
            if (game_level == 4) {
                level.setText("LEVEL ".concat("4"));
                category.setText("MAJOR PROPHETS ");
            }

            imageview1.setVisibility(View.INVISIBLE);
            imageview2.setVisibility(View.INVISIBLE);
            imageview3.setVisibility(View.INVISIBLE);
            imageview4.setVisibility(View.INVISIBLE);
            imageview5.setVisibility(View.INVISIBLE);
            imageview6.setVisibility(View.INVISIBLE);
            imageview7.setVisibility(View.INVISIBLE);
            imageview8.setVisibility(View.INVISIBLE);
            imageview9.setVisibility(View.INVISIBLE);

            total_time = total_time + currentTime;
            total_seconds = total_time;
            for(int _repeat81 = 0; _repeat81 < (int)(60); _repeat81++) {
                if (59 < total_seconds) {
                    total_seconds = total_seconds - 60;
                    total_minutes++;
                }
            }
            total_score = score + total_score;
        }
        if ((prevSelected == 11) && ((game_level == 2) || (game_level == 5))) {
            startbutton.setEnabled(true);
            startbutton.setVisibility(View.VISIBLE);
            gridLayout.setVisibility(View.GONE);
            stopbutton.setEnabled(false);
            startbutton.setBackgroundColor(getResources().getColor(R.color.green));
            stopbutton.setBackgroundColor(0xFF757575);
            endTime = Calendar.getInstance();
            currentTime = (long)(endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;

            timerTextView.setTextColor(getResources().getColor(R.color.black));
            timerHandler.removeCallbacks(timerRunnable);

            if ((bestTime == 0) || (currentTime < bestTime)) {
                bestTime = currentTime;
            }
            if ((bestScore == 0) || (bestScore < score)) {
                bestScore = score;
            }
            game_level++;
            if (game_level == 3) {
                /*dialog.setTitle("SCOREBOARD:");
                dialog.setMessage("Your Time: ".concat(String.valueOf((long)(currentTime)).concat("s          ¦          ".concat("Your Score: ".concat(String.valueOf((long)(score)))))));
                dialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                dialog.create().show();*/

                totalMinutes = totalMinutes + minutes;
                totalSeconds = totalSeconds + seconds;
                for(int _repeat129 = 0; _repeat129 < (int)(60); _repeat129++) {
                    if (59 < totalSeconds) {
                        totalSeconds = totalSeconds - 60;
                        totalMinutes++;
                    }
                }
                /*ScoreboardDialog scoreboardDialog = new ScoreboardDialog(this, String.format("%d:%02d", minutes, seconds),
                        String.valueOf((long)(score)));
                scoreboardDialog.show();*/

                level.setText("LEVEL ".concat("3"));
                category.setText("POETRY ");
            }
            if (game_level == 5) {
                /*dialog.setTitle("SCOREBOARD:");
                dialog.setMessage("Your Time: ".concat(String.valueOf((long)(currentTime)).concat("s          ¦          ".concat("Your Score: ".concat(String.valueOf((long)(score)))))));
                dialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                dialog.create().show();*/

                totalMinutes = totalMinutes + minutes;
                totalSeconds = totalSeconds + seconds;
                for(int _repeat129 = 0; _repeat129 < (int)(60); _repeat129++) {
                    if (59 < totalSeconds) {
                        totalSeconds = totalSeconds - 60;
                        totalMinutes++;
                    }
                }
                /*ScoreboardDialog scoreboardDialog = new ScoreboardDialog(this, String.format("%d:%02d", minutes, seconds),
                        String.valueOf((long)(score)));
                scoreboardDialog.show();*/

                level.setText("LEVEL ".concat("5"));
                category.setText("MINOR PROPHETS ");
            }
            if (game_level == 6) {
                /*dialog.setTitle("SCOREBOARD:");
                dialog.setMessage("Total Time: ".concat(String.valueOf((long)(total_minutes)).concat("m".concat(String.valueOf((long)(total_seconds)).concat("s  ¦¦  ".concat("Total Score: ".concat(String.valueOf((long)(total_score)).concat("/39"))))))));
                dialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                dialog.create().show();*/

                RewardDialog rewardDialog = new RewardDialog(this, String.format("%d:%02d", totalMinutes, totalSeconds),
                        String.valueOf((long)(total_score)).concat("/39"));
                rewardDialog.show();

                startbutton.setEnabled(false);
                stopbutton.setEnabled(false);
                startbutton.setVisibility(View.GONE);
                stopbutton.setVisibility(View.GONE);

                level.setText("GAME OVER ");
                level.setTextColor(0xFFF44336);
            }

            imageview1.setVisibility(View.INVISIBLE);
            imageview2.setVisibility(View.INVISIBLE);
            imageview3.setVisibility(View.INVISIBLE);
            imageview4.setVisibility(View.INVISIBLE);
            imageview5.setVisibility(View.INVISIBLE);
            imageview6.setVisibility(View.INVISIBLE);
            imageview7.setVisibility(View.INVISIBLE);
            imageview8.setVisibility(View.INVISIBLE);
            imageview9.setVisibility(View.INVISIBLE);
            imageview10.setVisibility(View.INVISIBLE);
            imageview11.setVisibility(View.INVISIBLE);
            imageview12.setVisibility(View.INVISIBLE);

            total_time = total_time + currentTime;
            total_seconds = total_time;
            for(int _repeat165 = 0; _repeat165 < (int)(60); _repeat165++) {
                if (59 < total_seconds) {
                    total_seconds = total_seconds - 60;
                    total_minutes++;
                }
            }
            total_score = score + total_score;
        }
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

