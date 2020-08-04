package de.h_da.fbi.demoroom.model;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CityDao {

    @Query("SELECT * FROM city")
    List<City> getAll();

    @Query("SELECT * from city where uid=:id")
    City getCityById(long id);

    @Insert
    long insert(City city);

    @Delete
    void delete(City city);

    @Update
    void update(City city);
}
