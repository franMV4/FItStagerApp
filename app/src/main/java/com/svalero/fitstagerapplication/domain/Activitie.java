package com.svalero.fitstagerapplication.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.svalero.fitstagerapplication.database.TimestampConverter;

@Entity
public class Activitie {

    @PrimaryKey(autoGenerate = true)
    private int id;     // TODO en la API es LONG!!!
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String difficulty;
    @ColumnInfo
    private String room;
    @ColumnInfo
    private String style;
    @ColumnInfo
    @TypeConverters({TimestampConverter.class})
    private String date;
    @ColumnInfo
    private String activityImage;
    @Ignore
    private Staff staff;   // TODO en API contiene un objeto user entero, sacar el ID
    @Ignore
    private Gym gym;   // TODO en API contiene un objeto user entero, sacar el ID


    @Ignore
    public Activitie() {
    }


    @Ignore
    public Activitie(Activitie activity) {
        this.id = activity.id;
        this.name = activity.name;
        this.difficulty = activity.difficulty;
        this.room = activity.room;
        this.style = activity.style;
        this.date = activity.date;
        this.activityImage = activity.activityImage;
    }


    public Activitie(int id, String name, String difficulty, String room, String style, String date, String activityImage) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.room = room;
        this.style = style;
        this.date = date;
        this.activityImage = activityImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }

}
