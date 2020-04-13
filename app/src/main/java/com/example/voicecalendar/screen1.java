package com.example.voicecalendar;


import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteException;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
//import android.support.annotation.RequiresApi;
//import android.support.v4.app.DialogFragment;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

public class screen1 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
//EditText firstname,lastname,middlename;
    //TextView textView;

    EditText firstname,lastname,middlename;
    //TextView textView;
    DB_controller controller;


    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;
    EditText editText;
    private Context context;

    //DB_controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);


        checkPermission();
        //editText = findViewById(R.id.textView);

        firstname = (EditText)findViewById(R.id.firstname_input);
        lastname = (EditText)findViewById(R.id.lastname_input);
        middlename = (EditText)findViewById(R.id.middle);

        //textView = (TextView)findViewById(R.id.textView);

        controller = new DB_controller(this,"",null,2);

        /*firstname = (EditText)findViewById(R.id.textView);
        lastname = (EditText)findViewById(R.id.textView1);
        middlename = (EditText)findViewById(R.id.middle);

        textView = (TextView)findViewById(R.id.editText);


        controller = new DB_controller(this,"",null,2);*/

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL , RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        Intent intent = mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle bundle) {

                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                processResult(matches.get(0));
                processResult1(matches.get(0));
                processResult_add(matches.get(0));
                processResult_delete(matches.get(0));
                processResult_list(matches.get(0));
                if(matches!= null){

                    processResult0(matches.get(0));
                    //firstname.setText(matches.get(0));
                    //textView.setText(matches.get(0));


                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        findViewById(R.id.imageButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {

                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        //firstname.setHint("You will see the input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        //firstname.setText("");

                        //firstname.setHint("Listening...");
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        break;

                }

                return false;
            }
        });

    }

    private void checkPermission(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(!(ContextCompat.checkSelfPermission( this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)){

                Intent intent  = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" +getPackageName()));

                startActivity(intent);
                finish();

            }

        }

       /* Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatepickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });*/
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.lastname_input);
        lastname.setText(currentDateString);

        //lastname.setText(currentDateString);

    }

    private void processResult0(String command){


        firstname.setText(command);

    }
    private void processResult1(String command){
        if (command.indexOf("set") != -1) {
            if (command.indexOf("time") != -1) {

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

            }
        }

    }

    private void processResult(String command) {
        command = command.toLowerCase();
        if (command.indexOf("set") != -1) {
            if (command.indexOf("date") != -1) {

                DialogFragment datePicker = new DialogFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        }

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        TextView textView = (TextView)findViewById(R.id.middle);
        middlename.setText("Hour : "+ hourOfDay + "Minute : "+ minute);
        //middlename.setText("Hour : "+ hourOfDay + " Minute : "+ minute);
    }

    /*public void btn_click(View view) {
        switch (view.getId()){

            case R.id.btn_add:

                try{

                    controller.insert_student(firstname.getText().toString(),lastname.getText().toString(),middlename.getText().toString());

                }catch (SQLiteException e){
                    Toast.makeText(screen1.this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_delete:
                controller.delete_student(firstname.getText().toString());

                break;
            case R.id.btn_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(screen1.this);
                dialog.setTitle("ENTER NEW FIRSTNAME");

                final EditText new_firstname = new EditText(this);
                dialog.setView(new_firstname);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.update_student(firstname.getText().toString(),new_firstname.getText().toString());
                    }
                });

                dialog.show();
                break;
            case R.id.list_students:

                controller.list_all_students(textView);
                break;

        }
    }*/

    private void processResult_add(String command) {
        command = command.toLowerCase();
        if (command.indexOf("add") != -1) {


            try{

                controller.insert_student(firstname.getText().toString(),lastname.getText().toString(),middlename.getText().toString());

            }catch (SQLiteException e){
                Toast.makeText(screen1.this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();
            }


        }

    }


    private void processResult_delete(String command) {
        command = command.toLowerCase();
        if (command.indexOf("delete") != -1) {


            controller.delete_student(lastname.getText().toString());

        }


    }


    private void processResult_list(String command) {
        command = command.toLowerCase();
        if (command.indexOf("view") != -1) {

            //Intent intent = new Intent(this,screen2.class);
            //startActivity(intent);
            Intent intent = new Intent(this,screen2.class);
            startActivity(intent);

            //controller.list_all_students(textView);
        }


    }

}
