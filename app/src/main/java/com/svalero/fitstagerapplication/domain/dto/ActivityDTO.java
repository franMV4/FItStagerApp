package com.svalero.fitstagerapplication.domain.dto;

public class ActivityDTO {


    private String name;
    private String difficulty;
    private String room;
    private String style;
    private String date;
    private int staff;
    private int gym;
    private String activityImage;



    public ActivityDTO() {

    }


    public ActivityDTO(String name, String difficulty, String room, String style, String date, int staff, int gym, String activityImage) {
        this.name = name;
        this.difficulty = difficulty;
        this.room = room;
        this.style = style;
        this.date = date;
        this.staff = staff;
        this.gym = gym;
        this.activityImage = activityImage;
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

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public int getGym() {
        return gym;
    }

    public void setGym(int gym) {
        this.gym = gym;
    }
}
