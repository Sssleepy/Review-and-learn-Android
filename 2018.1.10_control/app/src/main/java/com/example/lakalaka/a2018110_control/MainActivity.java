package com.example.lakalaka.a2018110_control;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ProgressBar progressBar;
    private Button btn_2;
    private Button btn_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn_1);
        btn_2= (Button) findViewById(R.id.btn_2);
        btn_3= (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                //progressDialog.setCancelable(false);  不能取消 通过加载完数据后dismiss来取消
                progressDialog.show();
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("This is Dialog");
                dialog.setMessage("Something important");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress=progressBar.getProgress();
                progress+=10;
                if (progress==100){
                    Toast.makeText(MainActivity.this,"111", Toast.LENGTH_SHORT).show();
                }
                progressBar.setProgress(progress);
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}
