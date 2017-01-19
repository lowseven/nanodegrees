package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int checkAnswers() {
        int answer = 0;

        //RADIO BUTTONS ANSWERS VALIDATOR
        int q1_selectedRadioButtonId = ((RadioGroup) findViewById(R.id.q1_radio_button)).getCheckedRadioButtonId();
        int q2_selectedRadioButtonId = ((RadioGroup) findViewById(R.id.q2_radio_button)).getCheckedRadioButtonId();

        RadioButton q1_radioButton = ((RadioButton) findViewById(q1_selectedRadioButtonId));
        RadioButton q2_radioButton = ((RadioButton) findViewById(q2_selectedRadioButtonId));

            answer = answer << 2;
//        answer <<= (q1_radioButton.getText() == getResources().getString(R.string.q1_answer)) ? 1 : 0;
//        answer <<= (q2_radioButton.getText() == getResources().getString(R.string.q2_answer)) ? 2 : 0;

//        //CHECKBOXES ANSWERS VALIDATOR
//        CheckBox cbKent1 = (CheckBox) findViewById(R.id.q3_kent1);
//        CheckBox cbKent2 = (CheckBox) findViewById(R.id.q3_kent2);
//        CheckBox cbKent3 = (CheckBox) findViewById(R.id.q3_kent3);
//        CheckBox cbKent4 = (CheckBox) findViewById(R.id.q3_kent4);
//
//        answer <<= (cbKent1.isChecked()) ? 1 : 0;
//        answer <<= (cbKent1.isChecked()) ? 1 : 0;

        return answer;
    }

    public void submitAnswer(View view) {
        int response = (checkAnswers() & 240);
        Toast.makeText(this, Integer.toString(checkAnswers()), Toast.LENGTH_LONG).show();
    }
}
