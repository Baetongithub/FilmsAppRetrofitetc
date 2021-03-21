package com.geektech.filmsappretrofitetc.models;

import com.google.gson.annotations.SerializedName;

public class People {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("gender")
    private String gender;

    @SerializedName("age")
    private String age;

    @SerializedName("eye_color")
    private String eye_color;

    @SerializedName("hair_color")
    private String hair_color;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getEye_color() {
        return eye_color;
    }

    public String getHair_color() {
        return hair_color;
    }
}
