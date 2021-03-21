package com.geektech.filmsappretrofitetc.models;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("climate")
    private String climate;

    @SerializedName("terrain")
    private String terrain;

    @SerializedName("surface_water")
    private String surface_water;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getSurface_water() {
        return surface_water;
    }
}
