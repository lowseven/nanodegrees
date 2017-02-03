package com.example.intellisys.audioplayer.model;

/**
 * Created by Lowse on 07-Feb-17.
 */

public class Songs {
    public String pathUri;
    public int image;
    public String name;

    public Songs(String pathUri, String name, int image) {
        this.pathUri = pathUri;
        this.name = name;
        this.image = image;
    }

    public Songs() {

    }
}
