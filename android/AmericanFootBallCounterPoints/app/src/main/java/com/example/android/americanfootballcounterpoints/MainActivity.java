package com.example.android.americanfootballcounterpoints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int teamAPoint;
    public int teamBPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamAPoint = teamBPoint = 0;
    }

    public void updatePointsTeamA(int point) {
        TextView textView = (TextView) findViewById(R.id.teamACounterTextView);
        textView.setText(String.valueOf(point));
    }

    public void updatePointsTeamB(int point) {
        TextView textView = (TextView) findViewById(R.id.teamBCounterTextView);
        textView.setText(String.valueOf(point));
    }

    public void addTeamATouchdown(View view) {
        teamAPoint += 4;
        updatePointsTeamA(teamAPoint);
    }

    public void addTeamBTouchdown(View view) {
        teamBPoint += 4;
        updatePointsTeamB(teamBPoint);
    }

    public void addTeamFieldGoalTeamA(View view) {
        teamAPoint += 3;
        updatePointsTeamA(teamAPoint);
    }

    public void addTeamFieldGoalTeamB(View view) {
        teamBPoint += 3;
        updatePointsTeamB(teamBPoint);
    }

    public void addTeamExtraPointTeamA(View view) {
        updatePointsTeamA(++teamAPoint);
    }

    public void addTeamExtraPointTeamB(View view) {
        updatePointsTeamB(++teamBPoint);
    }

    public void addDefensiveTackleTeamA(View view) {
        teamAPoint += 2;
        updatePointsTeamA(teamAPoint);
    }

    public void addDefensiveTackleTeamB(View view) {
        teamBPoint += 2;
        updatePointsTeamB(teamBPoint);
    }

    public void resetPoints(View view) {
        teamAPoint = teamBPoint  = 0;
        updatePointsTeamA(teamAPoint);
        updatePointsTeamB(teamBPoint);
    }


}
