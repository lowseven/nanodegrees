package com.example.android.reportcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class GradesAdapter extends ArrayAdapter<Grades> {
    public GradesAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.itemlist_child, parent, false);
        }

        TextView gradeValue = (TextView) listViewItem.findViewById(R.id.grades_value);
        TextView gradeSubject = (TextView) listViewItem.findViewById(R.id.grades_text);
        Grades grades = getItem(position);

        gradeSubject.setText(grades.getSubject());
        gradeValue.setText(grades.getGrade());

        return listViewItem;
    }
}
