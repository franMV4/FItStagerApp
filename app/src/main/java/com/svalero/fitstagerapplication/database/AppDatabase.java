package com.svalero.fitstagerapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.fitstagerapplication.dao.ActivityDao;
import com.svalero.fitstagerapplication.dao.Activity_ClientDao;
import com.svalero.fitstagerapplication.dao.ClientDao;
import com.svalero.fitstagerapplication.dao.GymDao;
import com.svalero.fitstagerapplication.dao.StaffDao;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Activitie;

@Database(entities = {Gym.class, Client.class, Activitie.class, Staff.class, Activity_Client.class}, version =4 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GymDao gymDao();
    public abstract StaffDao staffDao();
    public abstract ClientDao clientDao();
    public abstract ActivityDao activityDao();
    public abstract Activity_ClientDao activity_clientDao();

}
