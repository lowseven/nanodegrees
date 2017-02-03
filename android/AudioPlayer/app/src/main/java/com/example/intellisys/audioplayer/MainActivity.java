package com.example.intellisys.audioplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static String songUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void startSearchSongActivity(View view) {
        startActivity(new Intent(this, SearchSongActivity.class));
    }

    public void startCurrentSongActivity(View view) {
        startActivity(new Intent(this, CurrentSongActivity.class));
    }

    public void startAnnoyingSongActivity(View view) {
        startActivity(new Intent(this, AnnoyingSoundsActivity.class));
    }

}
