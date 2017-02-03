package com.example.intellisys.audioplayer.musicplayer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import java.io.IOException;

public class MusicPlayer extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private MediaPlayer player;
    private Context context;

    //player store states
    private static int resumePlayerPosition = 0;

    public MusicPlayer(Context context) {
        this.context = context;
        this.player = new MediaPlayer();

        player.setOnPreparedListener(this);
        player.setOnErrorListener(this);
        player.setOnCompletionListener(this);

        player.reset();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    private void playMediaFile() {
        if(!player.isPlaying()) {
            player.start();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void initPlayer(int mediaFileUri) {
        try {
            Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + mediaFileUri);
            player.setDataSource(context, uri);
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            stopSelf();
        }
    }

    public void initPlayer(String mediaFileUri) {
        try {
            player.setDataSource(context, Uri.parse(mediaFileUri));
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            stopSelf();
        }
    }

    public void pauseMediaFile() {
        if(player.isPlaying()) {
           player.pause();
            MusicPlayer.resumePlayerPosition = player.getCurrentPosition();
        }
    }

    public void resumeMedia() {
        if(!player.isPlaying()) {
            player.seekTo(MusicPlayer.resumePlayerPosition);
            player.start();
        }
    }

    public void releasePlayer() {
        this.player.release();
        stopSelf();
    }

    public void next10Sec() {
        int currentPos = player.getCurrentPosition();
        int maxDuration = player.getDuration();

        if(currentPos + 10 < maxDuration) {
            player.seekTo(currentPos + 10*1000);
        } else {
            player.seekTo(maxDuration);
        }

    }

    public void rewind10Sec() {
        int currentPos = player.getCurrentPosition();

        if(currentPos - 10 >= 0) {
            player.seekTo(currentPos - 10*1000);
        } else {
            player.seekTo(0);
        }
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        stopSelf();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        playMediaFile();
    }

}
