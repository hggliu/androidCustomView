package com.study.flowlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.study.flowlayout.view.WaterfallLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


   // private  int progress = 0;

    private static int IMG_COUNT = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*final CustomProgressBar progressBar = findViewById(R.id.progressbar);

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
        });*/

        final WaterfallLayout waterfallLayout = findViewById(R.id.waterfallLayout);
        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView(waterfallLayout);
            }
        });
    }

    /**
     * 添加照片
     * @param waterfallLayout
     */
    private void addView(WaterfallLayout waterfallLayout) {

        Random random = new Random();
        Integer num = Math.abs(random.nextInt());
        WaterfallLayout.LayoutParams layoutParams = new WaterfallLayout.LayoutParams(
                WaterfallLayout.WaterfalllayoutParams.MATCH_PARENT,
                WaterfallLayout.WaterfalllayoutParams.MATCH_PARENT
        );

        ImageView imageView = new ImageView(this);
        if (num % IMG_COUNT == 0) {
            imageView.setImageResource(R.drawable.pic_1);
        } else if (num % IMG_COUNT == 1) {
            imageView.setImageResource(R.drawable.pic_2);
        } else if (num % IMG_COUNT == 2) {
            imageView.setImageResource(R.drawable.pic_3);
        } else if (num % IMG_COUNT == 3) {
            imageView.setImageResource(R.drawable.pic_4);
        } else if (num % IMG_COUNT == 4) {
            imageView.setImageResource(R.drawable.pic_5);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        waterfallLayout.addView(imageView, layoutParams);

    }
}
