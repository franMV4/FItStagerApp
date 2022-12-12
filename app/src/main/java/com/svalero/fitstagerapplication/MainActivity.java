package com.svalero.fitstagerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.svalero.fitstagerapplication.view.ActivityListView;
import com.svalero.fitstagerapplication.view.Activity_ClientListView;
import com.svalero.fitstagerapplication.view.ClientListView;
import com.svalero.fitstagerapplication.view.GymListView;
import com.svalero.fitstagerapplication.view.StaffListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewGym (View view){
        Intent intent = new Intent(this, GymListView.class);
        startActivity(intent);
    }



    public void viewStaff (View view){
        Intent intent = new Intent(this, StaffListView.class);
        startActivity(intent);
    }

    public void viewClient (View view){
        Intent intent = new Intent(this, ClientListView.class);
        startActivity(intent);
    }

    public void viewActivity (View view){
        Intent intent = new Intent(this, ActivityListView.class);
        startActivity(intent);
    }

    public void viewActivity_Client (View view){
        Intent intent = new Intent(this, Activity_ClientListView.class);
        startActivity(intent);
    }


}