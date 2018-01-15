package com.example.lakalaka.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by lakalaka on 2018/1/14/0014.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mcontext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Book(id integer primary key autoincrement,author text,price real,pages integer,name text);");
        db.execSQL("create table Category(id integer primary key autoincrement,category _name text,category_code integer);");
        Toast.makeText(mcontext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
