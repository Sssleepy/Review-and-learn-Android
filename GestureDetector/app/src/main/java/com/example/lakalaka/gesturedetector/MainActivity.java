package com.example.lakalaka.gesturedetector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import static android.view.GestureDetector.SimpleOnGestureListener;

public class MainActivity extends AppCompatActivity {

    ImageView img;

    GestureDetector myGestureDetector;


    private class myGestrureListener extends SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX()-e2.getX()>50){
                Toast.makeText(MainActivity.this, "从右向左滑动", Toast.LENGTH_LONG).show();
            }else if(e2.getX()-e1.getX()>50){
                Toast.makeText(MainActivity.this, "从左往右滑动", Toast.LENGTH_SHORT).show();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGestureDetector=new GestureDetector(new myGestrureListener());

        img=(ImageView) findViewById(R.id.img);
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override//可以捕获触摸屏幕发生的Event事件
            public boolean onTouch(View v, MotionEvent event) {
                myGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }
}
