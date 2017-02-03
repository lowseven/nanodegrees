package com.example.intellisys.audioplayer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.intellisys.audioplayer.musicplayer.MusicPlayer;

public class CurrentSongActivity extends AppCompatActivity {

    private MusicPlayer player;
    private Drawable playButton;
    private Drawable pauseButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_song);

        this.playButton = ResourcesCompat.getDrawable(getResources(), R.drawable.play, null);
        this.pauseButon = ResourcesCompat.getDrawable(getResources(), R.drawable.pause, null);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(player != null)
            player.pauseMediaFile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView imageView = (ImageView) findViewById(R.id.play_pause_button);
        if (MainActivity.songUri != null) {
            if (this.player == null) {
                this.player = new MusicPlayer(this);
                this.player.initPlayer(MainActivity.songUri);
            } else {
                imageView.setImageDrawable(pauseButon);
                this.player.resumeMedia();
            }
        } else {
            Toast.makeText(this, "Select a song", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null) {
            this.player.releasePlayer();
            this.player = null;
        }
    }

    public void playPauseButton(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.play_pause_button);
        if (MainActivity.songUri != null) {
            if (player.isPlaying()) {
                imageView.setImageDrawable(playButton);
                player.pauseMediaFile();
            } else {
                imageView.setImageDrawable(pauseButon);
                player.resumeMedia();
            }
        } else {
            imageView.setImageDrawable(playButton);
            Toast.makeText(this, "Select a song", Toast.LENGTH_LONG).show();
        }
    }

    public void next10Sec(View view) {
        if (MainActivity.songUri != null)
            player.next10Sec();
        else
            Toast.makeText(this, "Select a song", Toast.LENGTH_LONG).show();
    }

    public void rewind10Sec(View view) {
        if (MainActivity.songUri != null)
            player.rewind10Sec();
        else
            Toast.makeText(this, "Select a song", Toast.LENGTH_LONG).show();
    }
}
