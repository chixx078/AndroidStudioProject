package com.example.android.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.button;
import static com.example.android.braintrainer.R.id.button0;
import static com.example.android.braintrainer.R.id.countDownText;
import static com.example.android.braintrainer.R.id.startButton;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultText;
    TextView pointsText;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAns;
    int score = 0;
    int numberOfQuestions = 0;

    RelativeLayout game;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView countDownText;
    Button playAgainButton;

    public void playAgain(View view){

        score = 0;
        numberOfQuestions=0;
        countDownText.setText("00:30");
        pointsText.setText("0/0");
        resultText.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestions();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {
                int secLeft = (int) l/1000;
                String secString = String.format("%02d", secLeft);
                countDownText.setText("00:" + secString);
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                countDownText.setText("00:00");
                resultText.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();
    }


    public void generateQuestions(){

        Random rand = new Random();
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAns = rand.nextInt(4); //0-3

        //delete the previous 4 answers from array list
        answers.clear();

        int incorrect;

        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAns){
                answers.add(a+b);
            }else{
                incorrect = rand.nextInt(101);
                while(incorrect == a+b) {
                    incorrect = rand.nextInt(101);
                }
                answers.add(incorrect);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }


    public void choose(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAns))){
            score++;
            resultText.setText("Right!");
        }else{
            resultText.setText("Wrong!");
        }

        numberOfQuestions++;

        pointsText.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        generateQuestions();

    }

    public void start(View view){

        startButton.setVisibility(view.INVISIBLE);

        game.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgain));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        resultText = (TextView) findViewById(R.id.correctText);
        pointsText = (TextView) findViewById(R.id.pointsText);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumText);


        countDownText = (TextView) findViewById(R.id.countDownText);

        playAgainButton = (Button) findViewById(R.id.playAgain);

        game = (RelativeLayout) findViewById(R.id.gameRelative);

    }
}
