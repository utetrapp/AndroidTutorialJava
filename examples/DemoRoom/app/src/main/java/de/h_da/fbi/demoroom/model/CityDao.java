package de.h_da.fbi.demoroom.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Maybe;

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
    Maybe<Integer> update(City city);
}
