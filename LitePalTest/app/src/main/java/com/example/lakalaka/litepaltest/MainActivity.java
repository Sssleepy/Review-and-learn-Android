package com.example.lakalaka.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Book firstBook = DataSupport.findFirst(Book.class);
                查询第一条数据
                Book lastBook = DataSupport.findLast(Book.class);
                查询最后一条数据
                List<Book> books = DataSupport.select("name", "author").find(Book.class);
                查询 name author 这两列的数据
                List<Book> books = DataSupport.where("pages>?", "400").find(Book.class);
                查询 pages大于400的数据
                List<Book> books = DataSupport.where("price desc").find(Book.class);
                查询结果按照 price 降序排列   asc 表示升序排列
                List<Book> books = DataSupport.limit(3).find(Book.class);
                只查询表中前3条数据
                List<Book> books = DataSupport.limit(3).offset(1).find(Book.class);
                offset偏移量  表示查询第2.3.4条刷数据
                可以进行整合查询
                */
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book : books) {
                    Log.d("Tag", "book name is" + book.getName());
                    Log.d("Tag", "book author is" + book.getAuthor());
                    Log.d("Tag", "book pages is" + book.getPages());
                    Log.d("Tag", "book price is" + book.getPrice());
                    Log.d("Tag", "book press is" + book.getPress());
                }
            }
        });
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class, "price<?", "15");
            }
        });
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name=?and author=? ", "The Da Vinic Code", "Dan Brown");
            }
        });
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinic Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPress("Unknow");
                book.setPrice(16.96);
                book.save();
            }
        });
        Button creatDatabase = (Button) findViewById(R.id.create_database);
        creatDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });
    }
}
