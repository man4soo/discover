package com.sungkyu.discover.di.modules;

import android.app.Application;

import androidx.core.content.PermissionChecker;
import androidx.room.Room;

import com.sungkyu.discover.db.Dao.RestaurantDao;
import com.sungkyu.discover.db.RestaurantDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {
    @Provides
    @Singleton
    RestaurantDB provideDatabase(Application app) {
        return Room.databaseBuilder(
                app,
                RestaurantDB.class,
                "RestaurantDB.db")
                .build();
    }

    @Provides
    @Singleton
    RestaurantDao provideRestaurantDao(RestaurantDB restaurantDB) {
        return restaurantDB.RestaurantDao();
    }
}
