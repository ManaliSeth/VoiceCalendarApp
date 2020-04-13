package com.example.voicecalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;


public class DB_controller extends SQLiteOpenHelper{
    public DB_controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT,LASTNAME TEXT UNIQUE,MIDDLENAME TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(sqLiteDatabase);
    }

    public void insert_student(String firstname,String lastname,String middlename){
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME",firstname);
        contentValues.put("LASTNAME",lastname);
        contentValues.put("MIDDLENAME",middlename);

        this.getWritableDatabase().insertOrThrow("STUDENTS","",contentValues);

    }

    public void delete_student(String lastname){
        this.getWritableDatabase().delete("STUDENTS","LASTNAME='"+lastname+"'",null);
    }

    public void update_student(String old_firstname,String new_firstname) {
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='"+new_firstname+"' WHERE FIRSTNAME='"+old_firstname);

    }

    public void list_all_students(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS",null);
        textView.setText("");

        while(cursor.moveToNext()){
            textView.append("\n**"+cursor.getString(2)+"\n  "+cursor.getString(3)+"\n  "+cursor.getString(1)+"\n");
        }
    }
}
