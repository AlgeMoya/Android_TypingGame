package com.example.android_typinggame;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rand = new Random();
    TextView tv,tvScore;
    Chronometer chrono;
    EditText et; //정답
    Button btnStart, btnEnd;  //게임시작 및 종료
    int score=0;  //점수
    boolean gaming=false; //게임 진행상태
    String st;
    String[] strings = new String[] { // 배열을 여기서 초기화해서 하드코딩! // 현재 25개
            "붉은 파밭의 푸른 새싹을 보아라",
            "햇빛도 그늘이 있어야 맑고 눈이 부시다",
            "그 맑은 눈빛으로 나를 씻어",
            "장닭 꼬리 날리는 하얀 바람 봄길",
            "포도빛 향기에 취해만 가는데",
            "생명의 꽃을 피우는 미나리 빛깔의 봄",
            "산 넘어 또 오를 산이 없다면 삶은 얼마나 밋밋할까",
            "꿈은 힘든 생활을 헤쳐나가는 힘",
            "진정 오늘밖엔 없는 것처럼 시간을 아껴 쓰고",
            "꽃은 젖어도 향기는 젖지 않는다",
            "풀섶 이슬에 함초롬 휘적시던 곳",
            "청노루 맑은 눈에 도는 구름",
            "내려갈 때 보았네 올라갈 때 못 본 그 꽃",
            "오늘 밤에도 별은 바람에 스치춘다",
            "이 많은 별빛이 내린 언덕 위에",
            "그러나 겨울이 지나고 나의 별에도 봄이 오면",
            "내가 그의 이름을 불러 주기 전에는",
            "잊혀지지 않는 하나의 눈짓이 되고 싶다",
            "세상이 바람 불고 춥고 어둡다 해도",
            "따뜻한 함박눈이 되어 내리자",
            "다시 우러러보는 이 하늘에",
            "소리쳐 부를 수도 없는 이 아득한 거리에",
            "그대 맑은 눈을 들어 나를 보느니",
            "말없이 재 넘는 초승달처럼",
            "희망의 샘 하나 출렁이고 있을 것만 같았다"
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

        //크로노미터 TickListener
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                //제한시간 01:00 (1분)
                String sCurrentTime = chronometer.getText().toString();
                if (sCurrentTime.equals("경과시간:01:00")) {
                    chrono.stop();
                    gaming = false;
                    tv.setText("게임이 끝났습니다!");
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
                    score++;
                    tvScore.setText(String.format(score+"점"));
                    tvScore.setTextColor(Color.RED);
                    et.setText("");
                    st = strings[rand.nextInt(25)]; // 랜덤 범위: 0~24, 배열 원소 개수에 맞게 맞춰주기!
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
                    gaming=true;
                    et.setText("");
                    st = strings[rand.nextInt(25)]; // 랜덤 범위: 0~24, 배열 원소 개수에 맞게 맞춰주기!
                    tv.setText(st);
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
                    btnStart.setVisibility(View.VISIBLE);
                    score=0;
                    tvScore.setText("점수");
                    break;
                } else {
                    Toast.makeText(MainActivity.this, "게임을 시작하지 않았습니다!", Toast.LENGTH_SHORT).show(); // 토스트 표출
                    break;
                }
        }
    }//monClick end
}// class END