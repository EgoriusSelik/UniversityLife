package com.example.universitylife.model;

import com.google.firebase.firestore.DocumentId;

public class NewsEntity {

    @DocumentId
    private String newsId;

    public String image_of_mero;

    public String name_of_mero;

    public String short_description_of_mero;

    public String long_description_of_mero;

    public String date_place_of_mero;

    public NewsEntity(){}

    public NewsEntity(String newsId, String image_of_mero, String name_of_mero,
                      String short_description_of_mero, String long_description_of_mero,
                      String date_place_of_mero) {
        this.newsId = newsId;
        this.image_of_mero = image_of_mero;
        this.name_of_mero = name_of_mero;
        this.short_description_of_mero = short_description_of_mero;
        this.long_description_of_mero = long_description_of_mero;
        this.date_place_of_mero = date_place_of_mero;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getImage_of_mero() {
        return image_of_mero;
    }

    public void setImage_of_mero(String image_of_mero) {
        this.image_of_mero = image_of_mero;
    }

    public String getName_of_mero() {
        return name_of_mero;
    }

    public void setName_of_mero(String name_of_mero) {
        this.name_of_mero = name_of_mero;
    }

    public String getShort_description_of_mero() {
        return short_description_of_mero;
    }

    public void setShort_description_of_mero(String short_description_of_mero) {
        this.short_description_of_mero = short_description_of_mero;
    }

    public String getLong_description_of_mero() {
        return long_description_of_mero;
    }

    public void setLong_description_of_mero(String long_description_of_mero) {
        this.long_description_of_mero = long_description_of_mero;
    }

    public String getDate_place_of_mero() {
        return date_place_of_mero;
    }

    public void setDate_place_of_mero(String date_place_of_mero) {
        this.date_place_of_mero = date_place_of_mero;
    }
}
