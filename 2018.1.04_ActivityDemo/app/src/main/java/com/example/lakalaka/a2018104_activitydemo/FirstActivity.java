package com.example.lakalaka.a2018104_activitydemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button actionStart= (Button) findViewById(R.id.btn_actionStart);
        actionStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.actionStart(FirstActivity.this,"data1","data2");

            }
        });
    }
}
