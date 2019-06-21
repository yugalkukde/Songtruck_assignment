package com.example.songtruck;

public class ItemHolder {

    private int imageProfile;
    private String textName;
    private String textPrice;
    private boolean isAvailable;
    private double ratings;

    public ItemHolder(int imageProfile, String textName, String textPrice, Boolean isAvailable, double ratings) {
        this.imageProfile = imageProfile;
        this.textName = textName;
        this.textPrice = textPrice;
        this.isAvailable = isAvailable;
        this.ratings = ratings;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getImageProfile() {
        return imageProfile;
    }

    public String getTextName() {
        return textName;
    }

    public String getTextPrice() {
        return textPrice;
    }

    public double getRatings() {
        return ratings;
    }
}
