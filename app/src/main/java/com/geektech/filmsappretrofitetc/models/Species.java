package com.geektech.filmsappretrofitetc.models;

import com.google.gson.annotations.SerializedName;

public class Species {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("classification")
    private String classification;

    @SerializedName("eye_colors")
    private String eye_colors;

    @SerializedName("hair_colors")
    private String hair_colors;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassification() {
        return classification;
    }

    public String getEye_colors() {
        return eye_colors;
    }

    public String getHair_colors() {
        return hair_colors;
    }
}