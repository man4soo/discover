package com.sungkyu.discover.db.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.Query;

import com.sungkyu.discover.db.Entity.Restaurant;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    LiveData<List<Restaurant>> getAll();

    @Insert(onConflict = REPLACE)
    void save(Restaurant restaurant);

    @Query("SELECT * FROM restaurant WHERE id = :id")
    LiveData<Restaurant> get(int id);

}
