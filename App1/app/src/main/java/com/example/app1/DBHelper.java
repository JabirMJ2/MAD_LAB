package com.example.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context){
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, contact TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }

    public boolean insertData(@NonNull String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        long result = DB.insert("users", null, contentValues);
        if(result == -1) return false;

        return true;
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cr = DB.rawQuery("select * from users", null);
        return cr;
    }


}
