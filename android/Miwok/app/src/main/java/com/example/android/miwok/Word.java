package com.example.android.miwok;

public class Word {
    private String defaultTranslation;
    private String miwokTranslation;
    private int soundId;
    private int iconImage;

    public Word(String defaultTranslation, String miwokTranslation, int soundId) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.iconImage = 0;
        this.soundId = soundId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int iconImage, int soundId) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.iconImage = iconImage;
        this.soundId = soundId;
    }

    public String getDefaultTransSlation() {
        return defaultTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public int getIconImage() {
        return iconImage;
    }

    public int getSoundId() { return soundId;  }

    public void setSoundId(int soundId) { this.soundId = soundId; }

}
