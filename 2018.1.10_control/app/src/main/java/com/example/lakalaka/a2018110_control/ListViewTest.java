package com.example.lakalaka.a2018110_control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewTest extends AppCompatActivity {
    private String[] data = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "11",
            "22", "33", "44", "55", "66", "77"
    };
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);

        initFruits();
        FruitAdpater adpater = new FruitAdpater(ListViewTest.this, R.layout.fruit_item, fruitList);
        final ListView listView1 = (ListView) findViewById(R.id.list_view1);
        listView1.setAdapter(adpater);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit=fruitList.get(position);
                Toast.makeText(ListViewTest.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {

        for (int i = 0; i < 2; i++) {
            Fruit a = new Fruit("1", R.mipmap.ic_launcher);
            fruitList.add(a);
            Fruit a2 = new Fruit("2",R.mipmap.ic_launcher);
            fruitList.add(a2);
            Fruit a3 = new Fruit("3",R.mipmap.ic_launcher);
            fruitList.add(a3);
            Fruit a4 = new Fruit("4",R.mipmap.ic_launcher);
            fruitList.add(a4);
            Fruit a5 = new Fruit("5",R.mipmap.ic_launcher);
            fruitList.add(a5);
            Fruit a6 = new Fruit("6",R.mipmap.ic_launcher);
            fruitList.add(a6);
            Fruit a7 = new Fruit("7",R.mipmap.ic_launcher);
            fruitList.add(a7);
            Fruit a8 = new Fruit("8",R.mipmap.ic_launcher);
            fruitList.add(a8);
            Fruit a9 = new Fruit("9",R.mipmap.ic_launcher);
            fruitList.add(a9);
            Fruit a11 = new Fruit("11",R.mipmap.ic_launcher);
            fruitList.add(a11);
            Fruit a22 = new Fruit("22",R.mipmap.ic_launcher);
            fruitList.add(a22);
            Fruit a33 = new Fruit("33",R.mipmap.ic_launcher);
            fruitList.add(a33);
            Fruit a44 = new Fruit("44",R.mipmap.ic_launcher);
            fruitList.add(a44);
            Fruit a55 = new Fruit("55",R.mipmap.ic_launcher);
            fruitList.add(a55);
            Fruit a66 = new Fruit("66",R.mipmap.ic_launcher);
            fruitList.add(a66);
            Fruit a77 = new Fruit("77",R.mipmap.ic_launcher);
            fruitList.add(a77);
        }

    }
}