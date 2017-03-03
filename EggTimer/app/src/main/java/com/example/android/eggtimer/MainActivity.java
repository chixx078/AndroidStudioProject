package com.example.android.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.android.eggtimer.R.id.timerTextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView timerTextView;
    boolean counterActive = false;
    Button controllerButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        timerTextView.setText("00:00");
        seekBar.setProgress(0);
        countDownTimer.cancel();
        controllerButton.setText("go");
        seekBar.setEnabled(true);
        counterActive = false;

    }

    public void updateText(int secondsLeft){
        int minutes = (int) secondsLeft/60;
        int seconds = secondsLeft - minutes * 60;

        //Set seconds to 10:01 instead of 10:1
        //format the integer with 2 digits, left padding it with zeroes
        String minuteString = String.format("%02d", minutes);
        String secondString = String.format("%02d", seconds);

        timerTextView.setText(minuteString + ":" + secondString);

    }


    public void controlTimer(View view){

        if(counterActive == false) {

            counterActive = true;

            //disable the seekBar while counting down
            seekBar.setEnabled(false);
            controllerButton.setText("stop");

            //+100 to compensate the time to call onTick and onFinish functions
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateText((int) l / 1000);
                }

                @Override
                public void onFinish() {



                    //if using "this", referring to the function controlTimer
                    //we need to refer the whole application
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.chick);
                    mediaPlayer.start();

                    resetTimer();

                }
            }.start();
        }else{

            resetTimer();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        controllerButton = (Button) findViewById(R.id.button);

        timerTextView = (TextView) findViewById(R.id.timerTextView);

        seekBar.setMax(600); //10 mins in sec
        seekBar.setProgress(0); //initially 30 sec when app launches

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateText(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
