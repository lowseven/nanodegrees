package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int pointsTeamA;
    private int pointsTeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pointsTeamA = 0;
        pointsTeamB = 0;
        displayForTeamA(pointsTeamA);
        displayForTeamB(pointsTeamB);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void add3PointsTeamA(View view){
        pointsTeamA += 3;
        displayForTeamA(pointsTeamA);
    }

    public void add2PointsTeamA(View view){
        pointsTeamA += 2;
        displayForTeamA(pointsTeamA);
    }

    public void addFreeThrowTeamA(View view){
        displayForTeamA(++pointsTeamA);
    }


    public void displayForTeamB(int score) {
        TextView bScoreView = (TextView) findViewById(R.id.team_b_score);
        bScoreView.setText(String.valueOf(score));
    }

    public void add3PointsTeamB(View view){
        pointsTeamB += 3;
        displayForTeamB(pointsTeamB);
    }

    public void add2PointsTeamB(View view){
        pointsTeamB += 2;
        displayForTeamB(pointsTeamB);
    }

    public void addFreeThrowTeamB(View view){
        displayForTeamB(++pointsTeamB);
    }

    public void resetScores(View view){
        pointsTeamA = pointsTeamB= 0;
        displayForTeamA(pointsTeamA);
        displayForTeamB(pointsTeamB);
    }
}
