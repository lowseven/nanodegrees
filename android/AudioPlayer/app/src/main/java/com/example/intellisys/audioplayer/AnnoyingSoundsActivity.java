package com.example.intellisys.audioplayer;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Random;

public class AnnoyingSoundsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annoying_sounds);
        LinearLayout annoyingSongList = (LinearLayout) findViewById(R.id.annoying_song_list);
        String rawPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/";
        Random randomColor = new Random();
        Drawable[] colors = new Drawable[]{
                ResourcesCompat.getDrawable(getResources(), R.color.blue, null),
                ResourcesCompat.getDrawable(getResources(), R.color.blue_grey, null),
                ResourcesCompat.getDrawable(getResources(), R.color.teal, null),
                ResourcesCompat.getDrawable(getResources(), R.color.indigo, null),
                ResourcesCompat.getDrawable(getResources(), R.color.light_blue, null),
        };

        Field[] test = R.raw.class.getFields();
        int idRawFile = 0;

        for (Field field : test) {
            LinearLayout annoyingSong = (LinearLayout) getLayoutInflater().inflate(R.layout.annoying_item_list, null);
            annoyingSong.setBackground(colors[randomColor.nextInt(colors.length)]);
            TextView songNameTextView = (TextView) annoyingSong.getChildAt(0);
            ImageView songItemListImageButton = (ImageView) annoyingSong.getChildAt(1);

            try {
                if ((field.getName() != "$change") && (field.getName() != "serialVersionUID")) {
                    idRawFile = field.getInt(field);
                    songNameTextView.setText(field.getName());
                    songItemListImageButton.setOnClickListener(new OnPlayListener());

                    songItemListImageButton.setTag(rawPath + idRawFile);
                    annoyingSongList.addView(annoyingSong);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    private class OnPlayListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            ImageView imageButton = (ImageView) view;
            MainActivity.songUri = imageButton.getTag().toString();

            startActivity(new Intent(AnnoyingSoundsActivity.this, CurrentSongActivity.class));
        }
    }
}
