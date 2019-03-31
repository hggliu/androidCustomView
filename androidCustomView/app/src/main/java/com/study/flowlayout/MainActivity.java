package com.study.flowlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.study.flowlayout.view.CustomProgressBar;

public class MainActivity extends AppCompatActivity {


    private  int progress = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomProgressBar progressBar = findViewById(R.id.progressbar);

        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       while (progress<=100){
                           progress +=2;
                           progressBar.setProgress(progress);
                           try {
                               Thread.sleep(200);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                    }
                }).start();
            }
        });
    }
}
