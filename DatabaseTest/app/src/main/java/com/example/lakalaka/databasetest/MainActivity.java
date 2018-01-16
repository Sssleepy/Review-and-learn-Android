package com.example.lakalaka.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper dbHelper;
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.create_database).setOnClickListener(this);
        findViewById(R.id.add_data).setOnClickListener(this);
        findViewById(R.id.update_data).setOnClickListener(this);
        findViewById(R.id.delete_data).setOnClickListener(this);
        findViewById(R.id.query_data).setOnClickListener(this);
        findViewById(R.id.add_data_provider).setOnClickListener(this);
        findViewById(R.id.delete_data_provider).setOnClickListener(this);
        findViewById(R.id.update_data_provider).setOnClickListener(this);
        findViewById(R.id.query_data_provider).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Uri uri=Uri.parse("content://com.example.lakalaka.databasetest.provider/book");
        Uri uri1=Uri.parse("content://com.example.lakalaka.databasetest.provider/book/"+newId);
        switch (v.getId()) {
            case R.id.add_data:
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values);
                values.clear();
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values);
                break;
            case R.id.update_data:
                values.put("price", 10.99);
                db.update("Book", values, "name = ? ", new String[]{"The Da Vinci Code"});
                break;
            case R.id.delete_data:
                db.delete("Book", "pages>?", new String[]{"500"});
                break;
            case R.id.query_data:
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("Tag", "book name is " + name);
                        Log.d("Tag", "book author is " + author);
                        Log.d("Tag", "book pages is " + pages);
                        Log.d("Tag", "book price is " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case R.id.create_database:
                dbHelper.getWritableDatabase();
                break;
            case R.id.add_data_provider:
                ContentValues valuse=new ContentValues();
                values.put("name","A Clash of Kings");
                values.put("author","Georger Martin");
                values.put("pages",1040);
                values.put("price",22.85);
                Uri newUri=getContentResolver().insert(uri,values);
                newId=newUri.getPathSegments().get(1);
                break;
            case R.id.delete_data_provider:
                getContentResolver().delete(uri1,null,null);
                break;
            case R.id.update_data_provider:
                values.put("name","A Srorm of Swords");
                values.put("pages",1216);
                values.put("price",24.05);
                getContentResolver().update(uri1,values,null,null);
                break;
            case R.id.query_data_provider:
                Cursor cursor1=getContentResolver().query(uri,null,null,null,null);
                if(cursor1!=null){
                    while (cursor1.moveToNext()){
                        String name=cursor1.getString(cursor1.getColumnIndex("name"));
                        String author=cursor1.getString(cursor1.getColumnIndex("author"));
                        int pages=cursor1.getInt(cursor1.getColumnIndex("pages"));
                        double price=cursor1.getDouble(cursor1.getColumnIndex("price"));
                        Log.d("Tag", "book name is " + name);
                        Log.d("Tag", "book author is " + author);
                        Log.d("Tag", "book pages is " + pages);
                        Log.d("Tag", "book price is " + price);
                    }
                    cursor1.close();
                }
                break;


        }


    }
}
