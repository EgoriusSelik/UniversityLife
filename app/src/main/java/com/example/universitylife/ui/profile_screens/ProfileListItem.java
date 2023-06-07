package com.example.universitylife.ui.profile_screens;

public class ProfileListItem {
    private String name;
    private int image_of_action;

    private int add_panel;

    public ProfileListItem(String name, int image_of_action, int add_panel) {
        this.name = name;
        this.image_of_action = image_of_action;
        this.add_panel = add_panel;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage_of_action() {
        return image_of_action;
    }

    public void setImage_of_action(int image_of_action) {
        this.image_of_action = image_of_action;
    }

    public int getAdd_panel() {
        return add_panel;
    }

    public void setAdd_panel(int add_panel) {
        this.add_panel = add_panel;
    }
}
