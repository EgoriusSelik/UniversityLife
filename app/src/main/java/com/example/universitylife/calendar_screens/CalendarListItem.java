package com.example.universitylife.calendar_screens;

public class CalendarListItem {
    private String name;
    private String date_and_place;

    public CalendarListItem(String name, String date_and_place) {
        this.name = name;
        this.date_and_place = date_and_place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_and_place() {
        return date_and_place;
    }

    public void setDate_and_place(String date_and_place) {
        this.date_and_place = date_and_place;
    }
}
