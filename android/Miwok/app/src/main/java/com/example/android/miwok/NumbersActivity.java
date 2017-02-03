package com.example.android.miwok;

import android.app.ActionBar;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer media;
    private AudioManager audio;
    private AudioManager.OnAudioFocusChangeListener audioFocus;

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
        setContentView(R.layout.activity_number);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        audio = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("one", "lutti", R.raw.number_one));
        words.add(new Word("two", "otiiko", R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.raw.number_four));
        words.add(new Word("five", "massokka", R.raw.number_five));
        words.add(new Word("six", "temmokka", R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.raw.number_ten));

        audioFocus = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    media.pause();
                    media.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    media.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    releaseMediaPlayer();
                }
            }
        };

        WordAdapter adapter = new WordAdapter(this, R.layout.list_item, words);
        ListView listView = (ListView) findViewById(R.id.list_numbers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int request = audio.requestAudioFocus(audioFocus, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (request == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    media = MediaPlayer.create(NumbersActivity.this, words.get(position).getSoundId());
                    media.start();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
