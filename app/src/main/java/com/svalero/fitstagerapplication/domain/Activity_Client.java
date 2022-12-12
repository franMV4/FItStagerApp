package com.svalero.fitstagerapplication.domain;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Activity_Client {


    @PrimaryKey(autoGenerate = true)
    private int id;     // TODO en la API es LONG!!!

    @Ignore
    private Activitie activity;   // TODO en API contiene un objeto user entero, sacar el ID

    @Ignore
    private Client client;   // TODO en API contiene un objeto user entero, sacar el ID

    @Ignore
    public Activity_Client(Activity_Client activity_client) {
        this.id = activity_client.id;
    }


    @Ignore
    public Activity_Client() {
    }




    public Activity_Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Activitie getActivity() {
        return activity;
    }

    public void setActivity(Activitie activity) {
        this.activity = activity;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
