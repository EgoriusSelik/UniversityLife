package com.example.universitylife.news_screens;


public class NewsItem {

    private String name_of_mero;
    private String description;
    private String date_and_place;
    private int image_of_mero;

    public NewsItem(String name_of_mero,
                    String description,
                    String date_and_place, int image_of_mero) {
        this.name_of_mero = name_of_mero;
        this.description = description;
        this.date_and_place = date_and_place;
        this.image_of_mero = image_of_mero;
    }

    public String getName_of_mero() {
        return name_of_mero;
    }

    public void setName_of_mero(String name_of_mero) {
        this.name_of_mero = name_of_mero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_and_place() {
        return date_and_place;
    }

    public void setDate_and_place(String date_and_place) {
        this.date_and_place = date_and_place;
    }

    public int getImage_of_mero() {
        return image_of_mero;
    }

    public void setImage_of_mero(int image_of_mero) {
        this.image_of_mero = image_of_mero;
    }
}
