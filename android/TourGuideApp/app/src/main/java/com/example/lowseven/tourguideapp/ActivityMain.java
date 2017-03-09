package com.example.lowseven.tourguideapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView dominica = (TextView) findViewById(R.id.dominican_republic_location);
        TextView italy = (TextView) findViewById(R.id.italy_location);
        TextView spain = (TextView) findViewById(R.id.spain_location);
        TextView japan = (TextView) findViewById(R.id.japan_location);

        dominica.setTag(DominicanRepublic.values());
        italy.setTag(Italy.values());
        spain.setTag(Spain.values());
        japan.setTag(Japan.values());
    }

    public void showPlaces(View view) {
        ActivityAttractions.locations = (ILocations[]) view.getTag();
        Intent i = new Intent(this, ActivityAttractions.class);
        startActivity(i);
    }
}
