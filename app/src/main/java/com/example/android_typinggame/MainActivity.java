package com.example.android_typinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rand = new Random();
    TextView tv, tvScore, tvStage;
    Chronometer chrono;
    EditText et; //정답
    Button btnStart, btnEnd;  //게임시작 및 종료
    int score1 = 0, score2 = 0, score3 = 0;  //점수
    int scoretotal;
    int stage = 0; // 단계
    boolean gaming = false; //게임 진행상태
    String st, msg;

    String[] words = new String[] {"피자", "떡볶이", "치킨", "닭볶음탕", "부대찌개", "삼겹살", "갈비찜","초밥","순대국밥", "비빔국수", "만두", "찜닭", "누룽지통닭", "냉면", "까르보나라", "카레", "배추김치", "숭늉", "식혜"};

    String[] strings1 = new String[] { // 배열을 여기서 초기화해서 하드코딩! // 현재 8개
            "그 맑은 눈빛으로 나를 씻어",
            "포도빛 향기에 취해만 가는데",
            "청노루 맑은 눈에 도는 구름",
            "따뜻한 함박눈이 되어 내리자",
            "다시 우러러보는 이 하늘에",
            "말없이 재 넘는 초승달처럼",
            "나 하늘로 돌아가리라",
            "새벽빛 와 닿으면 스러지는"
    };
    String[] strings2 = new String[] { // 배열을 여기서 초기화해서 하드코딩! // 현재 11개
            "햇빛도 그늘이 있어야 맑고 눈이 부시다",
            "생명의 꽃을 피우는 미나리 빛깔의 봄",
            "산 넘어 또 오를 산이 없다면 삶은 얼마나 밋밋할까",
            "진정 오늘밖엔 없는 것처럼 시간을 아껴 쓰고",
            "내려갈 때 보았네 올라갈 때 못 본 그 꽃",
            "그러나 겨울이 지나고 나의 별에도 봄이 오면",
            "잊혀지지 않는 하나의 눈짓이 되고 싶다",
            "소리쳐 부를 수도 없는 이 아득한 거리에",
            "희망의 샘 하나 출렁이고 있을 것만 같았다",
            "동짓달 기나긴 밤을 한 허리를 버혀내어",
            "어론 님 오신 날 밤이여든 구뷔구뷔 펴리라"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textview);
        et = findViewById(R.id.edittext);
        tvScore = findViewById(R.id.tvScore);
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        chrono=findViewById(R.id.chronoAll);
        tvStage = findViewById(R.id.tvStage);


        //크로노미터 TickListener
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                String sCurrentTime = chronometer.getText().toString();
                if (sCurrentTime.equals("경과시간:00:30") && stage == 1 && score1 >= 8) { // 30초, 1단계, 8점 이상
                    stage = 2;
                    tvStage.setText("2단계 : 짧은 문장");
                    tvScore.setText(String.format("2단계 점수: " + score2 + "점 / 총점 : " + scoretotal));
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    et.setText("");
                    st = strings1[rand.nextInt(strings1.length)];
                    tv.setText(st);
                } else if (sCurrentTime.equals("경과시간:01:00") && stage == 2 && score2 >= 5) { // 60초, 2단계, 5점 이상
                    stage = 3;
                    tvStage.setText("3단계 : 긴 문장");
                    tvScore.setText(String.format("3단계 점수: " + score3 + "점 / 총점 : " + scoretotal));
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    et.setText("");
                    st = strings2[rand.nextInt(strings2.length)];
                    tv.setText(st);
                } else if ((sCurrentTime.equals("경과시간:00:30") && stage == 1 && score1 <= 7) || (sCurrentTime.equals("경과시간:01:00") && stage == 2 && score2 <= 4) || (sCurrentTime.equals("경과시간:01:30") && stage == 3)) {
                    chrono.stop();
                    gaming = false;
                    tv.setText("게임이 끝났습니다!");
                    stage = 0;
                    tvScore.setTextColor(Color.GRAY);
                    chrono.setTextColor(Color.BLACK);
                    btnStart.setVisibility(View.VISIBLE);
                    Toast ts = Toast.makeText(getApplicationContext(),"게임이 끝났습니다!", Toast.LENGTH_SHORT);
                    ts.show();
                }
            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 입력하기 전
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // EditText에 변화가 있을 때
                System.out.println(st);
                if (et.getText().toString().equals(st) && gaming == true) {
                    Toast.makeText(MainActivity.this, "정답!", Toast.LENGTH_SHORT).show(); // 토스트 표출

                    tvScore.setTextColor(Color.RED);
                    et.setText("");

                    switch (stage) {
                        case 1:
                            st = words[rand.nextInt(words.length)];
                            score1++;
                            scoretotal++;
                            tvScore.setText(String.format("1단계 점수: " + score1 + "점 / 총점 : " + scoretotal));
                            break;
                        case 2:
                            st = strings1[rand.nextInt(strings1.length)];
                            score2++;
                            scoretotal++;
                            tvScore.setText(String.format("2단계 점수: " + score2 + "점 / 총점 : " + scoretotal));
                            break;
                        case 3:
                            st = strings2[rand.nextInt(strings2.length)];
                            score3++;
                            scoretotal++;
                            tvScore.setText(String.format("3단계 점수: " + score3 + "점 / 총점 : " + scoretotal));
                            break;
                    }
                    tv.setText(st);
                } else if (et.getText().toString().equals(st) && gaming == false) {
                    Toast.makeText(MainActivity.this, "게임이 진행중이지 않습니다!", Toast.LENGTH_SHORT).show(); // 토스트 표출
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 입력이 끝났을 때
            }
        });

    } // onCreate END
    public void monClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                if (gaming == false) {
                    // hand.sendEmptyMessage(1);
                    Toast.makeText(MainActivity.this, "게임을 시작합니다!", Toast.LENGTH_SHORT).show(); // 토스트 표출
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    gaming = true;
                    stage = 1;
                    score1 = 0;
                    score2 = 0;
                    score3 = 0;
                    scoretotal = 0;
                    tvScore.setText(String.format("1단계 점수: " + score1 + "점 / 총점 : " + scoretotal));
                    tvScore.setTextColor(Color.RED);
                    et.setText("");
                    st = words[rand.nextInt(words.length)];
                    tv.setText(st);
                    tvStage.setText("1단계 : 짧은 단어");
                    btnStart.setVisibility(View.INVISIBLE);
                    chrono.setTextColor(Color.BLUE);
                    break;
                } else {
                    Toast.makeText(MainActivity.this, "게임이 이미 진행중입니다!", Toast.LENGTH_SHORT).show(); // 토스트 표출
                    break;
                }
            case R.id.btnEnd:
                if (gaming == true) {
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.stop();
                    gaming=false;
                    tv.setText("게임을 종료했습니다!");
                    tvScore.setTextColor(Color.GRAY);
                    chrono.setTextColor(Color.BLACK);
                    btnStart.setVisibility(View.VISIBLE);
                    stage = 0;
                    // tvScore.setText("점수");
                    break;
                } else {
                    Toast.makeText(MainActivity.this, "게임을 시작하지 않았습니다!", Toast.LENGTH_SHORT).show(); // 토스트 표출
                    break;
                }
            case R.id.btnHelp:
                msg = "게임으로 타자실력을 확인해 봅시다! \n\n중간에 나오는 단어나 문장을 동일하게 입력하시면 1점을 득점합니다. \n\n총 3단계로 이루어져 있으며, 각 단계에는 제한시간이 존재합니다. \n\n제한시간 내에 일정한 점수를 득점하셔야 다음 단계로 넘어갈 수 있습니다. \n\n1단계: 30초/8점\n2단계: 60초/5점\n3단계: 90초";
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("게임 방법").setMessage(msg);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        // Toast.makeText(getApplicationContext(), "OK Click", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
    }//monClick end
}// class END