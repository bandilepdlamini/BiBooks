package com.bandilepdlamini.bibooks.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bandilepdlamini.bibooks.R;

public class ScoreboardDialog extends Dialog implements android.view.View.OnClickListener {
    private Context c;
    private Dialog d;
    private String time;
    private String score;

    public ScoreboardDialog(Context a, String pTime, String pScore) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        time = pTime;
        score = pScore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scoreboard_dialog);
        TextView txtTime = (TextView) findViewById(R.id.time);
        txtTime.setText(time);
        TextView txtScore = (TextView) findViewById(R.id.score);
        txtScore.setText(score);
        Button ok = (Button) findViewById(R.id.ok_btn);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_btn:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
