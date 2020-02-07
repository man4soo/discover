package com.sungkyu.discover.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sungkyu.discover.db.Dao.RestaurantDao;
import com.sungkyu.discover.db.Entity.Restaurant;


@Database(entities = {Restaurant.class}, version = 1, exportSchema = false)
public abstract class RestaurantDB extends RoomDatabase {

    private static volatile RestaurantDB INSTANCE;

    public abstract RestaurantDao RestaurantDao();

}
