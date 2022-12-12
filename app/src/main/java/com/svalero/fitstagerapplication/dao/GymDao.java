package com.svalero.fitstagerapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.fitstagerapplication.domain.Gym;

import java.util.List;

@Dao
public interface GymDao {

    @Query("SELECT * FROM gym")
    List<Gym> getAll();

    @Query("SELECT * FROM gym WHERE id = :id")
    Gym getGymById(int id);

    @Query("SELECT * FROM gym WHERE name LIKE :query")
    List<Gym> getByBrandString(String query);

    @Query("SELECT * FROM gym WHERE city LIKE :query")
    List<Gym> getByModelString(String query);

    @Query("SELECT * FROM gym WHERE street LIKE :query")
    List<Gym> getByRamString(String query);

    @Insert
    void insert(Gym gym);

    @Update
    void update(Gym gym);

    @Delete
    void delete(Gym gym);

}
