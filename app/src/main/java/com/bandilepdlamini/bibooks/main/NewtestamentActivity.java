package com.bandilepdlamini.bibooks.main;

/**
 * Created by User on 2018/01/20.
 */


import android.app.*;
import android.os.*;
import android.support.v4.content.ContextCompat;
import com.bandilepdlamini.bibooks.datasource.UserDetailMobImpl;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.util.*;

import com.bandilepdlamini.bibooks.R;
import com.bandilepdlamini.bibooks.util.RewardDialog;
import com.bandilepdlamini.bibooks.util.ScoreboardDialog;
import com.bandilepdlamini.bibooks.util.UserDetail;

import java.util.*;


public class NewtestamentActivity extends Activity {

    private TextView level;
    private TextView category;
    private TextView username;
    private TextView timerTextView;
    private TextView txtScore;
    /*private LinearLayout image1;
    private LinearLayout image2;
    private LinearLayout image3;
    private LinearLayout image4;
    private LinearLayout image5;
    private LinearLayout image6;
    private LinearLayout image7;
    private LinearLayout image8;
    private LinearLayout image9;
    private ImageView imageview1;
    private ImageView imageview2;
    private ImageView imageview3;
    private ImageView imageview4;
    private ImageView imageview5;
    private ImageView imageview6;
    private ImageView imageview7;
    private ImageView imageview8;
    private ImageView imageview9;*/
    private Button imageview1;
    private Button imageview2;
    private Button imageview3;
    private Button imageview4;
    private Button imageview5;
    private Button imageview6;
    private Button imageview7;
    private Button imageview8;
    private Button imageview9;
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
    private double total_time = 0;
    private double total_score = 0;
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
        setContentView(R.layout.newtestament);
        getActionBar().hide();
        initialize();
        initializeLogic();
    }

    private void  initialize() {
        level = (TextView) findViewById(R.id.level);
        category = (TextView) findViewById(R.id.category);
        username = (TextView) findViewById(R.id.username);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        txtScore = (TextView) findViewById(R.id.score);
        /*image1 = (LinearLayout) findViewById(R.id.image1);
        image2 = (LinearLayout) findViewById(R.id.image2);
        image3 = (LinearLayout) findViewById(R.id.image3);
        image4 = (LinearLayout) findViewById(R.id.image4);
        image5 = (LinearLayout) findViewById(R.id.image5);
        image6 = (LinearLayout) findViewById(R.id.image6);
        image7 = (LinearLayout) findViewById(R.id.image7);
        image8 = (LinearLayout) findViewById(R.id.image8);
        image9 = (LinearLayout) findViewById(R.id.image9);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        imageview3 = (ImageView) findViewById(R.id.imageview3);
        imageview4 = (ImageView) findViewById(R.id.imageview4);
        imageview5 = (ImageView) findViewById(R.id.imageview5);
        imageview6 = (ImageView) findViewById(R.id.imageview6);
        imageview7 = (ImageView) findViewById(R.id.imageview7);
        imageview8 = (ImageView) findViewById(R.id.imageview8);
        imageview9 = (ImageView) findViewById(R.id.imageview9);*/
        imageview1 = (Button) findViewById(R.id.imageview1);
        imageview2 = (Button) findViewById(R.id.imageview2);
        imageview3 = (Button) findViewById(R.id.imageview3);
        imageview4 = (Button) findViewById(R.id.imageview4);
        imageview5 = (Button) findViewById(R.id.imageview5);
        imageview6 = (Button) findViewById(R.id.imageview6);
        imageview7 = (Button) findViewById(R.id.imageview7);
        imageview8 = (Button) findViewById(R.id.imageview8);
        imageview9 = (Button) findViewById(R.id.imageview9);
        startbutton = (Button) findViewById(R.id.startbutton);
        stopbutton = (Button) findViewById(R.id.stopbutton);
        home = (Button) findViewById(R.id.home);
        newgame = (Button) findViewById(R.id.newgame);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

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
                page.setClass(getApplicationContext(), NewtestamentActivity.class);
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
                if (game_level == 1) {
                    /*image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.VISIBLE);
                    image5.setVisibility(View.VISIBLE);
                    image6.setVisibility(View.VISIBLE);
                    image7.setVisibility(View.INVISIBLE);
                    image8.setVisibility(View.INVISIBLE);
                    image9.setVisibility(View.INVISIBLE);*/
                    imageview1.setVisibility(View.INVISIBLE);
                    imageview2.setVisibility(View.INVISIBLE);
                    imageview3.setVisibility(View.INVISIBLE);
                    imageview4.setVisibility(View.INVISIBLE);
                    imageview5.setVisibility(View.VISIBLE);
                    imageview6.setVisibility(View.VISIBLE);
                    imageview7.setVisibility(View.VISIBLE);
                    imageview8.setVisibility(View.VISIBLE);
                    imageview9.setVisibility(View.VISIBLE);

                    srcList.clear();
                    initValue = 0;
                    score = 5;
                    txtScore.setText(String.valueOf(score));
                    for(int _repeat36 = 0; _repeat36 < (int)(5); _repeat36++) {
                        srcList.add(Double.valueOf(initValue));
                        initValue++;
                    }
                    answersList.clear();
                    for(int _repeat41 = 0; _repeat41 < (int)(5); _repeat41++) {
                        srcIndex = getRandom((int)(0), (int)(srcList.size() - 1));
                        answersList.add(Double.valueOf(srcList.get((int)(srcIndex)).doubleValue()));
                        srcList.remove((int)(srcIndex));
                    }
                    answerIndex = 0;
                    for(int _repeat52 = 0; _repeat52 < (int)(5); _repeat52++) {
                        _displayBooks(answerIndex, answersList.get((int)(answerIndex)).doubleValue());
                        answerIndex++;
                    }
                    prevSelected = -1;
                    isStarted = false;
                }
                if ((game_level == 2) || (game_level == 4)) {
                    /*image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.VISIBLE);
                    image5.setVisibility(View.VISIBLE);
                    image6.setVisibility(View.VISIBLE);
                    image7.setVisibility(View.VISIBLE);
                    image8.setVisibility(View.VISIBLE);
                    image9.setVisibility(View.VISIBLE);*/
                    imageview1.setVisibility(View.VISIBLE);
                    imageview2.setVisibility(View.VISIBLE);
                    imageview3.setVisibility(View.VISIBLE);
                    imageview4.setVisibility(View.VISIBLE);
                    imageview5.setVisibility(View.VISIBLE);
                    imageview6.setVisibility(View.VISIBLE);
                    imageview7.setVisibility(View.VISIBLE);
                    imageview8.setVisibility(View.VISIBLE);
                    imageview9.setVisibility(View.VISIBLE);

                    srcList.clear();
                    initValue = 0;
                    score = 9;
                    txtScore.setText(String.valueOf(score));
                    for(int _repeat66 = 0; _repeat66 < (int)(9); _repeat66++) {
                        srcList.add(Double.valueOf(initValue));
                        initValue++;
                    }
                    answersList.clear();
                    for(int _repeat71 = 0; _repeat71 < (int)(9); _repeat71++) {
                        srcIndex = getRandom((int)(0), (int)(srcList.size() - 1));
                        answersList.add(Double.valueOf(srcList.get((int)(srcIndex)).doubleValue()));
                        srcList.remove((int)(srcIndex));
                    }
                    answerIndex = 0;
                    for(int _repeat82 = 0; _repeat82 < (int)(9); _repeat82++) {
                        _displayBooks(answerIndex, answersList.get((int)(answerIndex)).doubleValue());
                        answerIndex++;
                    }
                    prevSelected = -1;
                    isStarted = false;
                }
                if (game_level == 3) {
                    /*image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.VISIBLE);
                    image5.setVisibility(View.VISIBLE);
                    image6.setVisibility(View.VISIBLE);
                    image7.setVisibility(View.INVISIBLE);
                    image8.setVisibility(View.INVISIBLE);
                    image9.setVisibility(View.INVISIBLE);*/
                    imageview1.setVisibility(View.VISIBLE);
                    imageview2.setVisibility(View.VISIBLE);
                    imageview3.setVisibility(View.VISIBLE);
                    imageview4.setVisibility(View.VISIBLE);
                    imageview5.setVisibility(View.VISIBLE);
                    imageview6.setVisibility(View.VISIBLE);
                    imageview7.setVisibility(View.INVISIBLE);
                    imageview8.setVisibility(View.INVISIBLE);
                    imageview9.setVisibility(View.INVISIBLE);


                    srcList.clear();
                    initValue = 0;
                    score = 4;
                    txtScore.setText(String.valueOf(score));
                    for(int _repeat97 = 0; _repeat97 < (int)(4); _repeat97++) {
                        srcList.add(Double.valueOf(initValue));
                        initValue++;
                    }
                    answersList.clear();
                    for(int _repeat102 = 0; _repeat102 < (int)(4); _repeat102++) {
                        srcIndex = getRandom((int)(0), (int)(srcList.size() - 1));
                        answersList.add(Double.valueOf(srcList.get((int)(srcIndex)).doubleValue()));
                        srcList.remove((int)(srcIndex));
                    }
                    answerIndex = 0;
                    for(int _repeat113 = 0; _repeat113 < (int)(4); _repeat113++) {
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
                startbutton.setEnabled(true);
                startbutton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.GONE);
                stopbutton.setEnabled(false);
                startbutton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                stopbutton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
                imageview1.setEnabled(false);
                imageview2.setEnabled(false);
                imageview3.setEnabled(false);
                imageview4.setEnabled(false);
                imageview1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                imageview2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                imageview3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                imageview4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                if (game_level == 1) {
                    imageview5.setEnabled(false);
                    imageview5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                }
                if ((game_level == 2) || (game_level == 4)) {
                    imageview5.setEnabled(false);
                    imageview6.setEnabled(false);
                    imageview7.setEnabled(false);
                    imageview8.setEnabled(false);
                    imageview9.setEnabled(false);
                    imageview5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                    imageview6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                    imageview7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                    imageview8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
                    imageview9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
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
                    _decisionSucess();
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
                    imageview4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    prevSelected = currentSelected;
                    _decisionSucess();
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
                    imageview7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    prevSelected = currentSelected;
                    _decisionSucess();
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
                    imageview2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    prevSelected = currentSelected;
                    _decisionSucess();
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
                    imageview6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    prevSelected = currentSelected;
                    _decisionSucess();
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
                    imageview5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    prevSelected = currentSelected;
                    _decisionSucess();
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
                    imageview9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    prevSelected = currentSelected;
                    _decisionSucess();
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
                    imageview8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    prevSelected = currentSelected;
                    _decisionSucess();
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
                    imageview3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
                    prevSelected = currentSelected;
                    _decisionSucess();
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

    }

    private void  initializeLogic() {
        startbutton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.green));
        stopbutton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.gray));
        startbutton.setEnabled(true);
        startbutton.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.GONE);
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
        level.setText("LEVEL ".concat("1"));
        game_level = 1;

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
        if (game_level == 1) {
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
        if ((game_level == 2) || (game_level == 4)) {
            GridLayout.LayoutParams params = (GridLayout.LayoutParams) imageview5.getLayoutParams();
            params.columnSpec = GridLayout.spec(0, 1);
            params.setGravity(Gravity.LEFT);
            imageview5.setLayoutParams(params);
            imageview5.setVisibility(View.VISIBLE);
            imageview6.setVisibility(View.VISIBLE);
            imageview7.setVisibility(View.VISIBLE);
            imageview8.setVisibility(View.VISIBLE);
            imageview9.setVisibility(View.VISIBLE);
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
        }
        if (game_level == 3) {
            imageview5.setVisibility(View.INVISIBLE);
            imageview6.setVisibility(View.INVISIBLE);
            imageview7.setVisibility(View.INVISIBLE);
            imageview8.setVisibility(View.INVISIBLE);
            imageview9.setVisibility(View.INVISIBLE);
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
        }
    }
    private void _displayBook1 (final double _value) {
        imageview1.setEnabled(true);
        imageview1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview1.setText("Matthew");
            }
            if (_value == 1) {
                imageview1.setText("Mark");
            }
            if (_value == 2) {
                imageview1.setText("Luke");
            }
            if (_value == 3) {
                imageview1.setText("John");
            }
            if (_value == 4) {
                imageview1.setText("Acts");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview1.setText("Romans");
            }
            if (_value == 1) {
                imageview1.setText("1 Corinthians");
            }
            if (_value == 2) {
                imageview1.setText("2 Corinthians");
            }
            if (_value == 3) {
                imageview1.setText("Galatians");
            }
            if (_value == 4) {
                imageview1.setText("Ephesians");
            }
            if (_value == 5) {
                imageview1.setText("Philippians");
            }
            if (_value == 6) {
                imageview1.setText("Colossians");
            }
            if (_value == 7) {
                imageview1.setText("1 Thessalonians");
            }
            if (_value == 8) {
                imageview1.setText("2 Thessalonians");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview1.setText("1 Timothy");
            }
            if (_value == 1) {
                imageview1.setText("2 Timothy");
            }
            if (_value == 2) {
                imageview1.setText("Titus");
            }
            if (_value == 3) {
                imageview1.setText("Philemon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview1.setText("Hebrews");
            }
            if (_value == 1) {
                imageview1.setText("James");
            }
            if (_value == 2) {
                imageview1.setText("1 Peter");
            }
            if (_value == 3) {
                imageview1.setText("2 Peter");
            }
            if (_value == 4) {
                imageview1.setText("1 John");
            }
            if (_value == 5) {
                imageview1.setText("2 John");
            }
            if (_value == 6) {
                imageview1.setText("3 John");
            }
            if (_value == 7) {
                imageview1.setText("Jude");
            }
            if (_value == 8) {
                imageview1.setText("Revelations");
            }
        }
    }
    private void _displayBook2 (final double _value) {
        imageview2.setEnabled(true);
        imageview2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview2.setText("Matthew");
            }
            if (_value == 1) {
                imageview2.setText("Mark");
            }
            if (_value == 2) {
                imageview2.setText("Luke");
            }
            if (_value == 3) {
                imageview2.setText("John");
            }
            if (_value == 4) {
                imageview2.setText("Acts");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview2.setText("Romans");
            }
            if (_value == 1) {
                imageview2.setText("1 Corinthians");
            }
            if (_value == 2) {
                imageview2.setText("2 Corinthians");
            }
            if (_value == 3) {
                imageview2.setText("Galatians");
            }
            if (_value == 4) {
                imageview2.setText("Ephesians");
            }
            if (_value == 5) {
                imageview2.setText("Philippians");
            }
            if (_value == 6) {
                imageview2.setText("Colossians");
            }
            if (_value == 7) {
                imageview2.setText("1 Thessalonians");
            }
            if (_value == 8) {
                imageview2.setText("2 Thessalonians");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview2.setText("1 Timothy");
            }
            if (_value == 1) {
                imageview2.setText("2 Timothy");
            }
            if (_value == 2) {
                imageview2.setText("Titus");
            }
            if (_value == 3) {
                imageview2.setText("Philemon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview2.setText("Hebrews");
            }
            if (_value == 1) {
                imageview2.setText("James");
            }
            if (_value == 2) {
                imageview2.setText("1 Peter");
            }
            if (_value == 3) {
                imageview2.setText("2 Peter");
            }
            if (_value == 4) {
                imageview2.setText("1 John");
            }
            if (_value == 5) {
                imageview2.setText("2 John");
            }
            if (_value == 6) {
                imageview2.setText("3 John");
            }
            if (_value == 7) {
                imageview2.setText("Jude");
            }
            if (_value == 8) {
                imageview2.setText("Revelations");
            }
        }
    }
    private void _displayBook3 (final double _value) {
        imageview3.setEnabled(true);
        imageview3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview3.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview3.setText("Matthew");
            }
            if (_value == 1) {
                imageview3.setText("Mark");
            }
            if (_value == 2) {
                imageview3.setText("Luke");
            }
            if (_value == 3) {
                imageview3.setText("John");
            }
            if (_value == 4) {
                imageview3.setText("Acts");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview3.setText("Romans");
            }
            if (_value == 1) {
                imageview3.setText("1 Corinthians");
            }
            if (_value == 2) {
                imageview3.setText("2 Corinthians");
            }
            if (_value == 3) {
                imageview3.setText("Galatians");
            }
            if (_value == 4) {
                imageview3.setText("Ephesians");
            }
            if (_value == 5) {
                imageview3.setText("Philippians");
            }
            if (_value == 6) {
                imageview3.setText("Colossians");
            }
            if (_value == 7) {
                imageview3.setText("1 Thessalonians");
            }
            if (_value == 8) {
                imageview3.setText("2 Thessalonians");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview3.setText("1 Timothy");
            }
            if (_value == 1) {
                imageview3.setText("2 Timothy");
            }
            if (_value == 2) {
                imageview3.setText("Titus");
            }
            if (_value == 3) {
                imageview3.setText("Philemon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview3.setText("Hebrews");
            }
            if (_value == 1) {
                imageview3.setText("James");
            }
            if (_value == 2) {
                imageview3.setText("1 Peter");
            }
            if (_value == 3) {
                imageview3.setText("2 Peter");
            }
            if (_value == 4) {
                imageview3.setText("1 John");
            }
            if (_value == 5) {
                imageview3.setText("2 John");
            }
            if (_value == 6) {
                imageview3.setText("3 John");
            }
            if (_value == 7) {
                imageview3.setText("Jude");
            }
            if (_value == 8) {
                imageview3.setText("Revelations");
            }
        }
    }
    private void _displayBook4 (final double _value) {
        imageview4.setEnabled(true);
        imageview4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview4.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview4.setText("Matthew");
            }
            if (_value == 1) {
                imageview4.setText("Mark");
            }
            if (_value == 2) {
                imageview4.setText("Luke");
            }
            if (_value == 3) {
                imageview4.setText("John");
            }
            if (_value == 4) {
                imageview4.setText("Acts");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview4.setText("Romans");
            }
            if (_value == 1) {
                imageview4.setText("1 Corinthians");
            }
            if (_value == 2) {
                imageview4.setText("2 Corinthians");
            }
            if (_value == 3) {
                imageview4.setText("Galatians");
            }
            if (_value == 4) {
                imageview4.setText("Ephesians");
            }
            if (_value == 5) {
                imageview4.setText("Philippians");
            }
            if (_value == 6) {
                imageview4.setText("Colossians");
            }
            if (_value == 7) {
                imageview4.setText("1 Thessalonians");
            }
            if (_value == 8) {
                imageview4.setText("2 Thessalonians");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview4.setText("1 Timothy");
            }
            if (_value == 1) {
                imageview4.setText("2 Timothy");
            }
            if (_value == 2) {
                imageview4.setText("Titus");
            }
            if (_value == 3) {
                imageview4.setText("Philemon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview4.setText("Hebrews");
            }
            if (_value == 1) {
                imageview4.setText("James");
            }
            if (_value == 2) {
                imageview4.setText("1 Peter");
            }
            if (_value == 3) {
                imageview4.setText("2 Peter");
            }
            if (_value == 4) {
                imageview4.setText("1 John");
            }
            if (_value == 5) {
                imageview4.setText("2 John");
            }
            if (_value == 6) {
                imageview4.setText("3 John");
            }
            if (_value == 7) {
                imageview4.setText("Jude");
            }
            if (_value == 8) {
                imageview4.setText("Revelations");
            }
        }
    }
    private void _displayBook5 (final double _value) {
        imageview5.setEnabled(true);
        imageview5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview5.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview5.setText("Matthew");
            }
            if (_value == 1) {
                imageview5.setText("Mark");
            }
            if (_value == 2) {
                imageview5.setText("Luke");
            }
            if (_value == 3) {
                imageview5.setText("John");
            }
            if (_value == 4) {
                imageview5.setText("Acts");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview5.setText("Romans");
            }
            if (_value == 1) {
                imageview5.setText("1 Corinthians");
            }
            if (_value == 2) {
                imageview5.setText("2 Corinthians");
            }
            if (_value == 3) {
                imageview5.setText("Galatians");
            }
            if (_value == 4) {
                imageview5.setText("Ephesians");
            }
            if (_value == 5) {
                imageview5.setText("Philippians");
            }
            if (_value == 6) {
                imageview5.setText("Colossians");
            }
            if (_value == 7) {
                imageview5.setText("1 Thessalonians");
            }
            if (_value == 8) {
                imageview5.setText("2 Thessalonians");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview5.setText("1 Timothy");
            }
            if (_value == 1) {
                imageview5.setText("2 Timothy");
            }
            if (_value == 2) {
                imageview5.setText("Titus");
            }
            if (_value == 3) {
                imageview5.setText("Philemon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview5.setText("Hebrews");
            }
            if (_value == 1) {
                imageview5.setText("James");
            }
            if (_value == 2) {
                imageview5.setText("1 Peter");
            }
            if (_value == 3) {
                imageview5.setText("2 Peter");
            }
            if (_value == 4) {
                imageview5.setText("1 John");
            }
            if (_value == 5) {
                imageview5.setText("2 John");
            }
            if (_value == 6) {
                imageview5.setText("3 John");
            }
            if (_value == 7) {
                imageview5.setText("Jude");
            }
            if (_value == 8) {
                imageview5.setText("Revelations");
            }
        }
    }
    private void _displayBook6 (final double _value) {
        imageview6.setEnabled(true);
        imageview6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview6.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview6.setText("Matthew");
            }
            if (_value == 1) {
                imageview6.setText("Mark");
            }
            if (_value == 2) {
                imageview6.setText("Luke");
            }
            if (_value == 3) {
                imageview6.setText("John");
            }
            if (_value == 4) {
                imageview6.setText("Acts");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview6.setText("Romans");
            }
            if (_value == 1) {
                imageview6.setText("1 Corinthians");
            }
            if (_value == 2) {
                imageview6.setText("2 Corinthians");
            }
            if (_value == 3) {
                imageview6.setText("Galatians");
            }
            if (_value == 4) {
                imageview6.setText("Ephesians");
            }
            if (_value == 5) {
                imageview6.setText("Philippians");
            }
            if (_value == 6) {
                imageview6.setText("Colossians");
            }
            if (_value == 7) {
                imageview6.setText("1 Thessalonians");
            }
            if (_value == 8) {
                imageview6.setText("2 Thessalonians");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview6.setText("1 Timothy");
            }
            if (_value == 1) {
                imageview6.setText("2 Timothy");
            }
            if (_value == 2) {
                imageview6.setText("Titus");
            }
            if (_value == 3) {
                imageview6.setText("Philemon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview6.setText("Hebrews");
            }
            if (_value == 1) {
                imageview6.setText("James");
            }
            if (_value == 2) {
                imageview6.setText("1 Peter");
            }
            if (_value == 3) {
                imageview6.setText("2 Peter");
            }
            if (_value == 4) {
                imageview6.setText("1 John");
            }
            if (_value == 5) {
                imageview6.setText("2 John");
            }
            if (_value == 6) {
                imageview6.setText("3 John");
            }
            if (_value == 7) {
                imageview6.setText("Jude");
            }
            if (_value == 8) {
                imageview6.setText("Revelations");
            }
        }
    }
    private void _displayBook7 (final double _value) {
        imageview7.setEnabled(true);
        imageview7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview7.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview7.setText("Matthew");
            }
            if (_value == 1) {
                imageview7.setText("Mark");
            }
            if (_value == 2) {
                imageview7.setText("Luke");
            }
            if (_value == 3) {
                imageview7.setText("John");
            }
            if (_value == 4) {
                imageview7.setText("Acts");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview7.setText("Romans");
            }
            if (_value == 1) {
                imageview7.setText("1 Corinthians");
            }
            if (_value == 2) {
                imageview7.setText("2 Corinthians");
            }
            if (_value == 3) {
                imageview7.setText("Galatians");
            }
            if (_value == 4) {
                imageview7.setText("Ephesians");
            }
            if (_value == 5) {
                imageview7.setText("Philippians");
            }
            if (_value == 6) {
                imageview7.setText("Colossians");
            }
            if (_value == 7) {
                imageview7.setText("1 Thessalonians");
            }
            if (_value == 8) {
                imageview7.setText("2 Thessalonians");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview7.setText("1 Timothy");
            }
            if (_value == 1) {
                imageview7.setText("2 Timothy");
            }
            if (_value == 2) {
                imageview7.setText("Titus");
            }
            if (_value == 3) {
                imageview7.setText("Philemon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview7.setText("Hebrews");
            }
            if (_value == 1) {
                imageview7.setText("James");
            }
            if (_value == 2) {
                imageview7.setText("1 Peter");
            }
            if (_value == 3) {
                imageview7.setText("2 Peter");
            }
            if (_value == 4) {
                imageview7.setText("1 John");
            }
            if (_value == 5) {
                imageview7.setText("2 John");
            }
            if (_value == 6) {
                imageview7.setText("3 John");
            }
            if (_value == 7) {
                imageview7.setText("Jude");
            }
            if (_value == 8) {
                imageview7.setText("Revelations");
            }
        }
    }
    private void _displayBook8 (final double _value) {
        imageview8.setEnabled(true);
        imageview8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview8.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview8.setText("Matthew");
            }
            if (_value == 1) {
                imageview8.setText("Mark");
            }
            if (_value == 2) {
                imageview8.setText("Luke");
            }
            if (_value == 3) {
                imageview8.setText("John");
            }
            if (_value == 4) {
                imageview8.setText("Acts");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview8.setText("Romans");
            }
            if (_value == 1) {
                imageview8.setText("1 Corinthians");
            }
            if (_value == 2) {
                imageview8.setText("2 Corinthians");
            }
            if (_value == 3) {
                imageview8.setText("Galatians");
            }
            if (_value == 4) {
                imageview8.setText("Ephesians");
            }
            if (_value == 5) {
                imageview8.setText("Philippians");
            }
            if (_value == 6) {
                imageview8.setText("Colossians");
            }
            if (_value == 7) {
                imageview8.setText("1 Thessalonians");
            }
            if (_value == 8) {
                imageview8.setText("2 Thessalonians");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview8.setText("1 Timothy");
            }
            if (_value == 1) {
                imageview8.setText("2 Timothy");
            }
            if (_value == 2) {
                imageview8.setText("Titus");
            }
            if (_value == 3) {
                imageview8.setText("Philemon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview8.setText("Hebrews");
            }
            if (_value == 1) {
                imageview8.setText("James");
            }
            if (_value == 2) {
                imageview8.setText("1 Peter");
            }
            if (_value == 3) {
                imageview8.setText("2 Peter");
            }
            if (_value == 4) {
                imageview8.setText("1 John");
            }
            if (_value == 5) {
                imageview8.setText("2 John");
            }
            if (_value == 6) {
                imageview8.setText("3 John");
            }
            if (_value == 7) {
                imageview8.setText("Jude");
            }
            if (_value == 8) {
                imageview8.setText("Revelations");
            }
        }
    }
    private void _displayBook9 (final double _value) {
        imageview9.setEnabled(true);
        imageview9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        imageview9.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        if (game_level == 1) {
            if (_value == 0) {
                imageview9.setText("Matthew");
            }
            if (_value == 1) {
                imageview9.setText("Mark");
            }
            if (_value == 2) {
                imageview9.setText("Luke");
            }
            if (_value == 3) {
                imageview9.setText("John");
            }
            if (_value == 4) {
                imageview9.setText("Acts");
            }
        }
        if (game_level == 2) {
            if (_value == 0) {
                imageview9.setText("Romans");
            }
            if (_value == 1) {
                imageview9.setText("1 Corinthians");
            }
            if (_value == 2) {
                imageview9.setText("2 Corinthians");
            }
            if (_value == 3) {
                imageview9.setText("Galatians");
            }
            if (_value == 4) {
                imageview9.setText("Ephesians");
            }
            if (_value == 5) {
                imageview9.setText("Philippians");
            }
            if (_value == 6) {
                imageview9.setText("Colossians");
            }
            if (_value == 7) {
                imageview9.setText("1 Thessalonians");
            }
            if (_value == 8) {
                imageview9.setText("2 Thessalonians");
            }
        }
        if (game_level == 3) {
            if (_value == 0) {
                imageview9.setText("1 Timothy");
            }
            if (_value == 1) {
                imageview9.setText("2 Timothy");
            }
            if (_value == 2) {
                imageview9.setText("Titus");
            }
            if (_value == 3) {
                imageview9.setText("Philemon");
            }
        }
        if (game_level == 4) {
            if (_value == 0) {
                imageview9.setText("Hebrews");
            }
            if (_value == 1) {
                imageview9.setText("James");
            }
            if (_value == 2) {
                imageview9.setText("1 Peter");
            }
            if (_value == 3) {
                imageview9.setText("2 Peter");
            }
            if (_value == 4) {
                imageview9.setText("1 John");
            }
            if (_value == 5) {
                imageview9.setText("2 John");
            }
            if (_value == 6) {
                imageview9.setText("3 John");
            }
            if (_value == 7) {
                imageview9.setText("Jude");
            }
            if (_value == 8) {
                imageview9.setText("Revelations");
            }
        }
    }
    private void _decisionSucess () {
        if ((prevSelected == 4) && (game_level == 1)) {
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
            //dialog.setMessage("Your Time: ".concat(String.valueOf((long)(currentTime)).concat("s          ¦          ".concat("Your Score: ".concat(String.valueOf((long)(score)))))));
            dialog.setMessage("Your Time: ".concat(String.format("%d:%02d", minutes, seconds).concat("s          ¦          ".concat("Your Score: ".concat(String.valueOf((long)(score)))))));
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
            level.setText("LEVEL ".concat("2"));
            category.setText("PAUL'S LETTERS TO CHURCHES ");
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
            for(int _repeat70 = 0; _repeat70 < (int)(60); _repeat70++) {
                if (59 < total_seconds) {
                    total_seconds = total_seconds - 60;
                    total_minutes++;
                }
            }
            total_score = score + total_score;
        }
        if ((prevSelected == 8) && ((game_level == 2) || (game_level == 4))) {
            startbutton.setEnabled(true);
            startbutton.setVisibility(View.VISIBLE);
            gridLayout.setVisibility(View.GONE);
            stopbutton.setEnabled(false);
            startbutton.setBackgroundColor(getResources().getColor(R.color.green));
            stopbutton.setBackgroundColor(0xFF757575);
            endTime = Calendar.getInstance();
            currentTime = (long)(endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;
            if ((bestTime == 0) || (currentTime < bestTime)) {
                bestTime = currentTime;
            }
            if ((bestScore == 0) || (bestScore < score)) {
                bestScore = score;
            }
            game_level++;

            timerTextView.setTextColor(getResources().getColor(R.color.black));
            timerHandler.removeCallbacks(timerRunnable);

            if (game_level == 3) {
                /*dialog.setTitle("SCOREBOARD:");
                //dialog.setMessage("Your Time: ".concat(String.valueOf((long)(currentTime)).concat("s          ¦          ".concat("Your Score: ".concat(String.valueOf((long)(score)))))));
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
                category.setText("PAUL'S LETTERS TO SAINTS ");
            }
            if (game_level == 5) {
                /*dialog.setTitle("SCOREBOARD:");
                dialog.setMessage("Total Time: ".concat(String.valueOf((long)(total_minutes)).concat("m".concat(String.valueOf((long)(total_seconds)).concat("s  ¦¦  ".concat("Total Score: ".concat(String.valueOf((long)(total_score)).concat("/18"))))))));
                dialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                dialog.create().show();*/


                userDetailMob.open();
                if(userDetail.getBestScore().equals("")){
                    userDetail.setBestScore(String.valueOf(total_score));
                    userDetailMob.updateUserDetail(userDetail, userDetail.getUsername());
                }
                else if(Integer.valueOf(userDetail.getBestScore()) < total_score){
                    userDetail.setBestScore(String.valueOf(total_score));
                    userDetailMob.updateUserDetail(userDetail, userDetail.getUsername());
                }
                userDetailMob.close();

                RewardDialog rewardDialog = new RewardDialog(this, String.format("%d:%02d", totalMinutes, totalSeconds),
                        String.valueOf((long)(total_score)).concat("/18"));
                rewardDialog.show();
                level.setText("GAME OVER! ");
                level.setTextColor(0xFFF44336);
                imageview1.setVisibility(View.INVISIBLE);
                imageview2.setVisibility(View.INVISIBLE);
                imageview3.setVisibility(View.INVISIBLE);
                imageview4.setVisibility(View.INVISIBLE);
                imageview5.setVisibility(View.INVISIBLE);
                imageview6.setVisibility(View.INVISIBLE);
                imageview7.setVisibility(View.INVISIBLE);
                imageview8.setVisibility(View.INVISIBLE);
                imageview9.setVisibility(View.INVISIBLE);
                home.setVisibility(View.VISIBLE);
                startbutton.setVisibility(View.INVISIBLE);
                stopbutton.setVisibility(View.INVISIBLE);
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
            for(int _repeat129 = 0; _repeat129 < (int)(60); _repeat129++) {
                if (59 < total_seconds) {
                    total_seconds = total_seconds - 60;
                    total_minutes++;
                }
            }
            total_score = score + total_score;
        }
        if ((prevSelected == 3) && (game_level == 3)) {
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
            //dialog.setMessage("Your Time: ".concat(String.valueOf((long)(currentTime)).concat("s          ¦          ".concat("Your Score: ".concat(String.valueOf((long)(score)))))));
            dialog.setMessage("Your Time: ".concat(String.format("%d:%02d", minutes, seconds).concat("s          ¦          ".concat("Your Score: ".concat(String.valueOf((long)(score)))))));
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
            if (game_level == 4) {
                level.setText("LEVEL ".concat("4"));
                category.setText("GENERAL LETTERS ");
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
            for(int _repeat211 = 0; _repeat211 < (int)(60); _repeat211++) {
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
