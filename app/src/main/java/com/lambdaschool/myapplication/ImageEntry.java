package com.lambdaschool.myapplication;

import android.net.Uri;

import java.io.Serializable;

public class ImageEntry implements Serializable {
    public static  final String TAG = "JournalEntry";
    public static final int INVALID_ID = -1;

    private String imageName, imageUri;
    private int id, switchValue;

    public ImageEntry(String imageName, String imageUri, int switchValue, int id) {
        this.imageName = imageName;
        this.imageUri = imageUri;
        this.id = id;
        this.switchValue = switchValue;
    }

    public ImageEntry(int id) {
        this.id = id;
        this.imageName = "";
        this.imageUri = "";
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Uri getImageUri() {
        if(! imageUri.equals("")) {
            return Uri.parse(imageUri);
        }else{
            return null;
        }
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSwitchValue() {
        return switchValue;
    }

    public void setSwitchValue(int switchValue) {
        this.switchValue = switchValue;
    }
}