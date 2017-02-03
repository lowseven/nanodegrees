package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer media;

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (media != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            media.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            media = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("red", "weṭeṭṭi", R.raw.color_red));
        words.add(new Word("green", "chokokki", R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.raw.color_gray));
        words.add(new Word("black", "kululli", R.raw.color_black));
        words.add(new Word("white", "kelelli", R.raw.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә", R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(this, R.layout.list_item, words);
        ListView listView = (ListView) findViewById(R.id.list_colors);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                media = MediaPlayer.create(ColorsActivity.this, words.get(position).getSoundId());
                media.start();
                media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }
        });
    }

}
