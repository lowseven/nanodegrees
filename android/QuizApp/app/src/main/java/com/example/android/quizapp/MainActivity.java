package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean[] checkAnswers() {
        boolean[] answers = new boolean[4];

        //RADIO BUTTONS ANSWERS
        int q1_selectedRadioButtonId = ((RadioGroup) findViewById(R.id.q1_radio_button)).getCheckedRadioButtonId();
        int q2_selectedRadioButtonId = ((RadioGroup) findViewById(R.id.q2_radio_button)).getCheckedRadioButtonId();

        if(q1_selectedRadioButtonId > 0) {
            RadioButton q1_radioButton = ((RadioButton) findViewById(q1_selectedRadioButtonId));
            answers[0] = (q1_radioButton.getText() == getResources().getString(R.string.q1_answer));
        } else {
          answers[0] = false;
        }
        if(q2_selectedRadioButtonId > 0) {
            RadioButton q2_radioButton = ((RadioButton) findViewById(q2_selectedRadioButtonId));
            answers[1] = (q2_radioButton.getText() == getResources().getString(R.string.q2_answer));
        } else {
            answers[1] = false;
        }

        //CHECKBOX ANSWERS
        CheckBox q3_checkBox1 = (CheckBox) findViewById(R.id.q3_kent1);
        CheckBox q3_checkBox2 = (CheckBox) findViewById(R.id.q3_kent2);
        CheckBox q3_checkBox3 = (CheckBox) findViewById(R.id.q3_kent3);
        CheckBox q3_checkBox4 = (CheckBox) findViewById(R.id.q3_kent4);

        answers[2] = (q3_checkBox1.isChecked() && q3_checkBox3.isChecked() && !q3_checkBox1.isChecked() && !q3_checkBox4.isChecked());

        //EDITTEXT ANSWER
        EditText editText = (EditText) findViewById(R.id.q4_answer1);
        String q4_answer = getResources().getString(R.string.q4_answer).toLowerCase();
        answers[3] =  editText.getText().toString().toLowerCase().contains(q4_answer);

        return answers;
    }

    public void submitAnswer(View view) {
        boolean[] answers = checkAnswers();
        int points = 0;

        for(int i=0; i<4;i++) {
            points += answers[i] ? i+1 : 0 ;
        }

        Toast.makeText(this, "Score: " + points + " of 10 max points", Toast.LENGTH_LONG).show();
    }
}
