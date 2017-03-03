package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int score = 0;
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void three_a(View view){
        score = score + 3;
        displayForTeamA(score);
    }

    public void two_a(View view){
        score = score +2;
        displayForTeamA(score);
    }

    public void free_a(View view){
        score = score +1;
        displayForTeamA(score);
    }

    //Team B
    int score_b = 0;

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score_b));
    }

    public void three_b(View view){
        score_b = score_b + 3;
        displayForTeamB(score_b);
    }

    public void two_b(View view){
        score_b = score_b +2;
        displayForTeamB(score_b);
    }

    public void free_b(View view){
        score_b = score_b +1;
        displayForTeamB(score_b);
    }

    public void reset(View view){
        score=0;
        score_b=0;
        displayForTeamA(score);
        displayForTeamB(score_b);
    }
}
