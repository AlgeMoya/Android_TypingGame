package com.example.android_typinggame_main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText et;
    Button btnStart;
    String st;
    String[] array = new String[]{"피자", "떡볶이", "치킨", "닭볶음탕", "부대찌개","삼겹살","갈비찜","초밥","순대국밥"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnStart=findViewById(R.id.btnStart);
        tv=findViewById(R.id.tv);
        et=findViewById(R.id.et);

        //게임시작버튼
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                et.setText("");
                st=array[(int)(Math.random()*8)] ;
                tv.setText(st);
            }// end
        });


        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent event) {

                switch(id){
                    default:
                        if (et.getText().toString().equals(st)) {
                            Toast.makeText(MainActivity.this, "정답!", Toast.LENGTH_SHORT).show();
                            et.setText("");
                            st = array[(int) (Math.random() * 8)];
                            tv.setText(st);
                        }
                }
                et.setText("");
                return true;
            }
        });

    }//onCre END


}//class END