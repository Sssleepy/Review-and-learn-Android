package com.example.lakalaka.mpandroidchart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView mGridView;//列表
    private String[] mStr={"线性图","多轴线性图","条形图"};//数据源
    private ArrayAdapter<String> mAdapter;//适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mGridView= (GridView) findViewById(R.id.mGridView);
        mAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mStr);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case 0:
                Intent intent=new Intent(MainActivity.this,LineChartActivity.class);
                startActivity(intent);
            break;
            case 1:
                Intent intent1 = new Intent(MainActivity.this, DoubleLineChartActivity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(MainActivity.this, BarChartActivity.class);
                startActivity(intent2);
                break;
            
            default:
            break;
         }
        
    }
}
