package com.example.android_typinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Thread_Seokkyoo extends AppCompatActivity {
    Random rand = new Random();
    TextView tv;
    Button btn;
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

    // 스레드 무한반복
    Thread my = new Thread() {
        public void run() {
            while (true) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // randomnum = rand.nextInt(22);
                        // System.out.println(randomnum);
                        // tv.setText(strings[randomnum]); // 랜덤 범위: 0~21, 배열 원소 개수에 맞게 맞춰주기!
                        tv.setText(strings[rand.nextInt(22)]); // 랜덤 범위: 0~21, 배열 원소 개수에 맞게 맞춰주기!
                        }
                });
                try {
                    Thread.sleep(5000); // 출력 주기 설정. 합칠때 꼭 맞출 것! // 5초
                } catch (Exception ex) {
                    System.out.println("Error");
                }
            } // while end
        } // run end
    }; // my end


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_seokkyoo);

        tv = findViewById(R.id.textview);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my.start();
            }
        });
    }
}