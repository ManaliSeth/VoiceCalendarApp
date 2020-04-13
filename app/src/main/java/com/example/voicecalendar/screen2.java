package com.example.voicecalendar;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class screen2 extends AppCompatActivity {
    TextView textView;
    DB_controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        textView = (TextView)findViewById(R.id.textView);

        controller = new DB_controller(this,"",null,2);

        controller.list_all_students(textView);
    }
}
