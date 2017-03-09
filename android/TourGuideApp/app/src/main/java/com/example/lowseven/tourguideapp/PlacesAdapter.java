package com.example.lowseven.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlacesAdapter extends ArrayAdapter<ILocations> {

    public PlacesAdapter(Context context, int resource, ArrayList<ILocations> places) {
        super(context, resource, places);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        final ILocations place = this.getItem(position);

        if(convertView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.place_image_view);
        TextView titleView = (TextView) listItemView.findViewById(R.id.place_image_title_view);
        ImageView imageButton = (ImageView) listItemView.findViewById(R.id.place_location_button);

        imageView.setImageResource(place.image());
        titleView.setText(place.title());
        imageButton.setTag(place.coordinates());
        imageButton.setImageResource(R.mipmap.position_icon);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: 0,0?q=" +  getContext().getString(place.coordinates())));
                i.setPackage("com.google.android.apps.maps");
                getContext().startActivity(i);
            }
        });

        return listItemView;
    }

}
