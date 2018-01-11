package com.example.lakalaka.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
   // private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayoutManager layouManager;
    private StaggeredGridLayoutManager layoutmanager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        //设置为横向布局
        //layouManager.setOrientation(LinearLayoutManager.HORIZONTAL);

    }

    private void initData() {
        //layoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //列数和布局方式
        layoutmanager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new MyAdapter(getData());
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutmanager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 20; i++) {
            data.add( getRandomLengthName(i)+temp);
        }
        return data;
    }
    private String getRandomLengthName(int name){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);

        }
        return builder.toString();
    }



}
