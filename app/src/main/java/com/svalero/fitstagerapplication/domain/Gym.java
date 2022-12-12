package com.svalero.fitstagerapplication.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity
public class Gym implements Comparable<Gym> {


    @PrimaryKey(autoGenerate = true)
    private int id;     // TODO en la API es LONG!!!
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String street;
    @ColumnInfo
    private String horary;
    @ColumnInfo
    private String city;
    @ColumnInfo
    private float latitude;
    @ColumnInfo
    private float longitude;
    @ColumnInfo
    private String gymImage;

    @Ignore
    public Gym(Gym gym) {
        this.id = gym.id;
        this.name = gym.name;
        this.street = gym.street;
        this.horary = gym.horary;
        this.city = gym.city;
        this.latitude = gym.latitude;
        this.longitude = gym.longitude;
    }


    public Gym(int id, String name, String street, String horary, String city, float latitude, float longitude, String gymImage) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.horary = horary;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gymImage = gymImage;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHorary() {
        return horary;
    }

    public void setHorary(String horary) {
        this.horary = horary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getGymImage() {
        return gymImage;
    }

    public void setGymImage(String gymImage) {
        this.gymImage = gymImage;
    }



    @Ignore
    public Gym() {
    }


    @Override
    public int compareTo(Gym o) {
        return 0;
    }
}
