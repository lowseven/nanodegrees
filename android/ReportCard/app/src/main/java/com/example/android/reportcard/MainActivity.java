package com.example.android.reportcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GradesAdapter grades = new GradesAdapter(this, R.layout.itemlist_child);
        ListView listView = (ListView) findViewById(R.id.activity_main);

        grades.add(new Grades("Math", "A+"));
        grades.add(new Grades("Science", "B+"));
        grades.add(new Grades("Drawing", "C"));
        grades.add(new Grades("Physics", "B"));
        grades.add(new Grades("History", "A+"));
        grades.add(new Grades("Civics", "B-"));
        grades.add(new Grades("Spelling", "B-"));
        grades.add(new Grades("Arithmetic", "A+"));
        grades.add(new Grades("Writing", "C+"));
        grades.add(new Grades("Reading", "C+"));

        listView.setAdapter(grades);
    }
}
