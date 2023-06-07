package com.example.universitylife.registration;

import java.util.ArrayList;
import java.util.HashMap;

public class UserEntity {
    public String id;
    public String name_and_sername;
    public String instityt;
    public String group;
    public String description;

    public UserEntity(){}

    public UserEntity(String id, String name_and_sername, String instityt, String group, String description) {
        this.id = id;
        this.name_and_sername = name_and_sername;
        this.instityt = instityt;
        this.group = group;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_and_sername() {
        return name_and_sername;
    }

    public void setName_and_sername(String name_and_sername) {
        this.name_and_sername = name_and_sername;
    }

    public String getInstityt() {
        return instityt;
    }

    public void setInstityt(String instityt) {
        this.instityt = instityt;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
