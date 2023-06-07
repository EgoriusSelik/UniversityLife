package com.example.universitylife.ui.profile_screens.organizations;

public class OrganizationItem {
    private String name_of_org;
    private String description_of_org;
    private int image_of_org;

    public OrganizationItem(String name_of_org, String description_of_org, int image_of_org) {
        this.name_of_org = name_of_org;
        this.description_of_org = description_of_org;
        this.image_of_org = image_of_org;
    }


    public String getName_of_org() {
        return name_of_org;
    }

    public void setName_of_org(String name_of_org) {
        this.name_of_org = name_of_org;
    }

    public String getDescription_of_org() {
        return description_of_org;
    }

    public void setDescription_of_org(String description_of_org) {
        this.description_of_org = description_of_org;
    }

    public int getImage_of_org() {
        return image_of_org;
    }

    public void setImage_of_org(int image_of_org) {
        this.image_of_org = image_of_org;
    }
}
