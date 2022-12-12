package com.svalero.fitstagerapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.fitstagerapplication.domain.Staff;

import java.util.List;


@Dao
public interface StaffDao {

    @Query("SELECT * FROM staff")
    List<Staff> getAll();



    @Insert
    void insert(Staff staff);

    @Update
    void update(Staff staff);

    @Delete
    void delete(Staff staff);

}
