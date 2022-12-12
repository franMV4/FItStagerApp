package com.svalero.fitstagerapplication.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Client;

import java.util.List;

@Dao
public interface Activity_ClientDao {


    @Query("SELECT * FROM activity_client")
    List<Activity_Client> getAll();



    @Insert
    void insert(Activity_Client activity_client);

    @Update
    void update(Activity_Client activity_client);

    @Delete
    void delete(Activity_Client activity_client);


}
