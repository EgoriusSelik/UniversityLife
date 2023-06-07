package com.example.universitylife.ui.profile_screens.history;

public class HistoryItem {
    private String name_of_mero;
    private String date_of_mero;
    private int image_of_mero;

    public HistoryItem(String name_of_mero, String date_of_mero, int image_of_mero) {
        this.name_of_mero = name_of_mero;
        this.date_of_mero = date_of_mero;
        this.image_of_mero = image_of_mero;
    }


    public String getName_of_mero() {
        return name_of_mero;
    }

    public void setName_of_mero(String name_of_mero) {
        this.name_of_mero = name_of_mero;
    }

    public String getDate_of_mero() {
        return date_of_mero;
    }

    public void setDate_of_mero(String date_of_mero) {
        this.date_of_mero = date_of_mero;
    }

    public int getImage_of_mero() {
        return image_of_mero;
    }

    public void setImage_of_mero(int image_of_mero) {
        this.image_of_mero = image_of_mero;
    }
}
