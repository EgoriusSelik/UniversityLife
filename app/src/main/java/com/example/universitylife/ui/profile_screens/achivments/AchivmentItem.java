package com.example.universitylife.ui.profile_screens.achivments;

public class AchivmentItem {

    private String name_of_ach;

    private String description_of_ach;

    private int image_of_ach;

    public AchivmentItem(String name_of_ach, String description_of_ach, int image_of_ach) {
        this.name_of_ach = name_of_ach;
        this.description_of_ach = description_of_ach;
        this.image_of_ach = image_of_ach;
    }

    public String getName_of_ach() {
        return name_of_ach;
    }

    public void setName_of_ach(String name_of_ach) {
        this.name_of_ach = name_of_ach;
    }

    public String getDescription_of_ach() {
        return description_of_ach;
    }

    public void setDescription_of_ach(String description_of_ach) {
        this.description_of_ach = description_of_ach;
    }

    public int getImage_of_ach() {
        return image_of_ach;
    }

    public void setImage_of_ach(int image_of_ach) {
        this.image_of_ach = image_of_ach;
    }
}
