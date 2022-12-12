package com.svalero.fitstagerapplication.domain.dto;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import com.svalero.fitstagerapplication.domain.Gym;

public class ClientDTO {


    private String name;
    private String surname;
    private String dni;
    private String phone;
    private int gym;
    private String clientImage;


    public ClientDTO() {

    }

    public ClientDTO(String name, String surname, String dni, String phone, int gym, String clientImage) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.phone = phone;
        this.gym = gym;
        this.clientImage = clientImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClientImage() {
        return clientImage;
    }

    public void setClientImage(String clientImage) {
        this.clientImage = clientImage;
    }



    public int getGym() {
        return gym;
    }

    public void setGym(int gym) {
        this.gym = gym;
    }
}
