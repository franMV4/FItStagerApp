package com.svalero.fitstagerapplication.domain.dto;

public class Activity_ClientDTO {



    private int activity;
    private int client;


    public Activity_ClientDTO() {
    }



    public Activity_ClientDTO(int activity, int client) {
        this.activity = activity;
        this.client = client;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }
}
