package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_family);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("Father", "әpә", R.raw.family_father));
        words.add(new Word("Mother", "әṭa", R.raw.family_mother));
        words.add(new Word("Son", "angsi", R.raw.family_son));
        words.add(new Word("Daughter", "tune", R.raw.family_daughter));
        words.add(new Word("Older brother", "taachi", R.raw.family_older_brother));
        words.add(new Word("Younger brother", "chalitti", R.raw.family_younger_brother));
        words.add(new Word("Older Sister", "tete",R.raw.family_older_sister));
        words.add(new Word("Young sister", "kolliti", R.raw.family_younger_sister));
        words.add(new Word("Grandmother", "ama", R.raw.family_grandmother));
        words.add(new Word("Granfather", "paapa", R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, R.layout.list_item, words);
        ListView listView = (ListView) findViewById(R.id.list_family);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                media = MediaPlayer.create(FamilyActivity.this, words.get(position).getSoundId());
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

