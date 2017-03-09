package com.example.lowseven.tourguideapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class ActivityAttractions extends AppCompatActivity {

    public static ILocations[] locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_places);

        ArrayList<ILocations> attractions = new ArrayList<>();
        Collections.addAll(attractions, locations);

        PlacesAdapter adapter = new PlacesAdapter(this, R.layout.list_item, attractions);
        ListView listView = (ListView) findViewById(R.id.list_places_view);

        listView.setAdapter(adapter);
    }
}
