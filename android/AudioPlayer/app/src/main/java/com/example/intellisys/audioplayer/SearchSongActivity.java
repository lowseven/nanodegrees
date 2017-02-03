package com.example.intellisys.audioplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.intellisys.audioplayer.model.Songs;

import java.io.File;
import java.util.ArrayList;

public class SearchSongActivity extends AppCompatActivity {


    private ArrayList<Songs> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.songs = new ArrayList<>();



        setContentView(R.layout.activity_search_song);
    }

    private void searchingSongs(File[] files, String searchQuery) {
        for(File file : files) {
            if(file.isFile()) {
                if(file.getPath().endsWith(".mp3") && file.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                    Songs song = new Songs();
                    song.pathUri = file.getPath();
                    song.name = file.getName();
                    song.image = 0;
                    songs.add(song);
                }
            } else {
                searchingSongs(file.listFiles(), searchQuery);
            }
        }
    }

    public void searchButton(View view) {
        songs.clear();
        EditText editText = (EditText) findViewById(R.id.edit_text_song_search);

//how can i improve this?
        File downloadDirectory = new File(Environment.getExternalStorageDirectory().getPath() + "/Download/");
        File musicDirectory = new File(Environment.getExternalStorageDirectory().getPath() + "/Music/");

        searchingSongs(downloadDirectory.listFiles(), editText.getText().toString());
        searchingSongs(musicDirectory.listFiles(), editText.getText().toString());

        inflateSongList();
//------
    }

    private void inflateSongList() {
        LinearLayout songList = (LinearLayout) findViewById(R.id.song_list);
        songList.removeAllViews();
        for(Songs song : this.songs) {
            LinearLayout songItemList = (LinearLayout) getLayoutInflater().inflate(R.layout.song_item_list, null);
            TextView songTextView = (TextView) songItemList.getChildAt(0);
            songTextView.setText(song.name.substring(0, song.name.indexOf('.')));
            songTextView.setTag(song.pathUri);
            songTextView.setOnClickListener(new itemListOnClickClass());

            songList.addView(songItemList);
        }
        this.songs.clear();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.songs = null;
    }

    private class itemListOnClickClass implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            TextView songTextView = (TextView) view;
            MainActivity.songUri = songTextView.getTag().toString();
            startActivity(new Intent(SearchSongActivity.this, CurrentSongActivity.class));
        }

    }
}
