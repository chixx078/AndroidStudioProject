package com.example.android.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //countDownTimer distroy after finish
        //good if you want a temperory timer
        new CountDownTimer(10000, 1000){
            public void onTick(long mSecUntilDone){
                //run when counting down.

                Log.i("Seconds left", String.valueOf(mSecUntilDone/1000));
            }

            public void onFinish(){
                //finish after 10 sec in this case

                Log.i("Done","finished");
            }
        }.start();

        /*final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                //insert code to be run every second.

                Log.i("Runnable has run", "....");

                handler.postDelayed(this, 1000);
            }
        };

        handler.post(run);
        */

    }
}
