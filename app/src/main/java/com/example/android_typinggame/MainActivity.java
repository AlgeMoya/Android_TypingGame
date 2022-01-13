package com.example.android_typinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rand = new Random();
    TextView tv;
    EditText et;
    Button btnStart;
    String st;
    String[] array = new String[]{"피자", "떡볶이", "치킨", "닭볶음탕", "부대찌개","삼겹살","갈비찜","초밥","순대국밥"};
    // Button btn;
    // int randomnum, c = 0;

    String[] strings = new String[] { // 배열을 여기서 초기화해서 하드코딩! // 현재 22개
            "닭 콩팥 훔친 집사",
            "물컵 속 팥 찾던 형",
            "동틀 녘 햇빛 포개짐",
            "동틀 녘 햇빛 작품",
            "자동차 바퀴 틈새가 파랗니",
            "해태 옆 치킨집 닭맛",
            "코털 팽 대감네 첩 좋소",
            "닭 잡아서 치킨파티 함",
            "쵸코볼은 티피가 맛 좋다",
            "다람쥐 헌 쳇바퀴에 타고파",
            "파티에 참석한 키다리 부자",
            "호두 팥죽을 삼킨 첩",
            "새 투포환 코치 닭 보여줌",
            "저 고래 콩팥 해부 마친 듯",
            "Nick, you had better put choco jam too",
            "추운 겨울에는 따뜻한 커피와 티를 마셔야지요",
            "그 늙다리만 본선에 진출케 투표해",
            "가나 독립 명분상 이제 총칼 타파함",
            "그는 독립문 보수에 지쳐 칼퇴 표함",
            "그날 드라마 부서와 재차 콘티 평함",
            "그 남다른 마법사의 절친 커트 폐하",
            "가느다란 몸 부수어 쥔 총칼, 터 평화"
    };

    /*
    // 스레드 무한반복
    Thread my = new Thread() {
        public void run() {
            while (true) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        et.setText("");
                        // randomnum = rand.nextInt(22);
                        // System.out.println(randomnum);
                        // tv.setText(strings[randomnum]); // 랜덤 범위: 0~21, 배열 원소 개수에 맞게 맞춰주기!
                        st = strings[rand.nextInt(22)];
                        tv.setText(st); // 랜덤 범위: 0~21, 배열 원소 개수에 맞게 맞춰주기!
                    }
                });
                try {
                    Thread.sleep(10000); // 출력 주기 설정. 합칠때 꼭 맞출 것! // 10초
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception ex) {
                    System.out.println("Error");
                }
            } // while end
        } // run end
    }; // my end
     */

    // 글자 맞췄을 때 새로운 문자열로 불러오는 부분이 Thread로 어려워서 부득이하게 변경...
    Handler hand = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                et.setText("");
                st = strings[rand.nextInt(22)];
                tv.setText(st); // 랜덤 범위: 0~21, 배열 원소 개수에 맞게 맞춰주기!
            }
            // Thread 사용 없이 handler 호출할때 delayMillis를 전달하는 것만으로 구현이 가능
            hand.sendEmptyMessageDelayed(1, 60000); // while 무한루프 기술 X // 출력 주기 설정. 합칠때 꼭 맞출 것! // 60초
        } // handleMessage end
    }; // hand end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.button);
        tv = findViewById(R.id.textview);
        et = findViewById(R.id.edittext);

        //게임시작버튼
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                hand.sendEmptyMessage(1);
                // my.start();

                // 이하 스레드/핸들러에서 처리하는 구문
                // st=array[(int)(Math.random()*8)] ;
                // tv.setText(st);
            }// end
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
                if (et.getText().toString().equals(st)) {
                    Toast.makeText(MainActivity.this, "정답!", Toast.LENGTH_SHORT).show(); // 토스트 표출
                    hand.sendEmptyMessage(1);
                    // et.setText(""); // 글자 지우기

                    // onDestroy(); // 스레드 멈추고
                    // my.start(); // 스레드 다시 시작
                    // tv.setText(strings[rand.nextInt(22)]);
                    // st = array[(int) (Math.random() * 8)];
                    // tv.setText(st);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 입력이 끝났을 때
            }
        });

    } // onCreate END
}// class END