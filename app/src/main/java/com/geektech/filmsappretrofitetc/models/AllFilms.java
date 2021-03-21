package com.geektech.filmsappretrofitetc.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "allfilms")
public class AllFilms implements Serializable {

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private String string;

    @SerializedName("title")
    private String title;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("original_title_romanised")
    private String original_title_romanised;

    @SerializedName("description")
    private String description;

    @SerializedName("director")
    private String director;

    @SerializedName("producer")
    private String producer;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("running_time")
    private String running_time;

    @SerializedName("rt_score")
    private String rt_score;

    public AllFilms() {
    }

    public void setString(@NotNull String string) {
        this.string = string;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOriginal_title_romanised(String original_title_romanised) {
        this.original_title_romanised = original_title_romanised;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setRunning_time(String running_time) {
        this.running_time = running_time;
    }

    public void setRt_score(String rt_score) {
        this.rt_score = rt_score;
    }

    @NotNull
    public String getString() {
        return string;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_title_romanised() {
        return original_title_romanised;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getRunning_time() {
        return running_time;
    }

    public String getRt_score() {
        return rt_score;
    }
}