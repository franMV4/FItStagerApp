package com.svalero.fitstagerapplication.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Client;

import java.util.List;

@Dao
public interface ActivityDao {



    @Query("SELECT * FROM activitie")
    List<Activitie> getAll();



    @Insert
    void insert(Activitie activity);

    @Update
    void update(Activitie activity);

    @Delete
    void delete(Activitie activity);

}
