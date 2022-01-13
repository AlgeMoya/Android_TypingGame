package com.example.android_typinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class ScoreActivity extends Activity {

    TimerTask timerTask;
    Timer timer = new Timer();
    TextView tvQuiz, tvTimeAll, tvTimeQuiz; //문제, 남은시간 출력할 TextView
    EditText et; //정답
    Button btnStart, btnEnd;  //게임시작 및 종료
    int score;  //점수
    long time; //제한시간(1분)
    boolean running; //게임 진행여부
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        //시간과 스코어 출력, 나중에 종료 버튼

        tvQuiz = findViewById(R.id.tvQuiz);
        tvTimeAll= findViewById(R.id.tvTimeAll);
        tvTimeQuiz= findViewById(R.id.tvTimeQuiz);
        et = findViewById(R.id.et);
        score = 0;
        time = 60;
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);

    }//onCreate end

    public void monClick(View v){
        switch (v.getId()){
            case R.id.btnStart:
                startTimerTask();
                break;
                
            case R.id.btnEnd:
                stopTimerTask();
                break;
        }
    }//monClick end

    private void startTimerTask() {
        stopTimerTask(); //초기화하고 시작
        timerTask = new TimerTask()
        {
            int time=60;
            public void run(){
                time--;
                tvTimeAll.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTimeAll.setText(time + " 초");
                    }
                });
            }
        };
        timer.schedule(timerTask,0 , 1000);
    }//startTimerTask end
    
    private void stopTimerTask() {
        if (timerTask != null) {
            tvTimeAll.setText("60 초");
            timerTask.cancel();
            timerTask = null;
        }
    }//stopTimerTask end


    }//class END