package com.svalero.fitstagerapplication.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Staff {

    @PrimaryKey(autoGenerate = true)
    private int id;     // TODO en la API es LONG!!!
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String surname;
    @ColumnInfo
    private String dni;
    @ColumnInfo
    private String phone;
    @ColumnInfo
    private String staffImage;

    @Ignore
    private Gym gym;   // TODO en API contiene un objeto user entero, sacar el ID


    @Ignore
    public Staff() {
    }


    @Ignore
    public Staff(Staff staff) {
        this.id = staff.id;
        this.name = staff.name;
        this.surname = staff.surname;
        this.dni = staff.dni;
        this.phone = staff.phone;
        this.staffImage = staff.staffImage;
    }




    public Staff(int id, String name, String surname, String dni, String phone, String staffImage) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.phone = phone;
        this.staffImage = staffImage;
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

    public String getStaffImage() {
        return staffImage;
    }

    public void setStaffImage(String staffImage) {
        this.staffImage = staffImage;
    }



    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }
}
