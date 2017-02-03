package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int backgroundColor;
    private Word word;

    public WordAdapter(Context context, int resource, ArrayList<Word> words) {
        super(context, resource, words);
    }

    public WordAdapter(Context context, int resource, ArrayList<Word> words, int backgroundColor) {
        super(context, resource, words);
        this.backgroundColor = backgroundColor;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView defaultTranslation = (TextView) listItemView.findViewById(R.id.default_translation_textview);
        TextView miwokTranslation = (TextView) listItemView.findViewById(R.id.miwok_translation_textview);
        ImageView miwokIconImage = (ImageView) listItemView.findViewById(R.id.default_translation_icon);
        Word word = getItem(position);

        defaultTranslation.setText(word.getDefaultTransSlation());
        miwokTranslation.setText(word.getMiwokTranslation());
        miwokIconImage.setImageResource(word.getIconImage());

        if(backgroundColor> 0) listItemView.setBackgroundColor(this.backgroundColor);
        if(word.getIconImage() == 0) miwokIconImage.setVisibility(View.GONE);

        return listItemView;
    }
}
