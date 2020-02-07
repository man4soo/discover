package com.sungkyu.discover.di.modules;

import android.app.Application;

import androidx.room.Room;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sungkyu.discover.DataRepository;
import com.sungkyu.discover.db.Dao.RestaurantDao;
import com.sungkyu.discover.db.Entity.Restaurant;
import com.sungkyu.discover.db.RestaurantDB;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

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
    Executor provideExecutor() {
        return Executors.newFixedThreadPool(4);
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder().registerTypeAdapter(Restaurant.class, new Restaurant.RestaurantDeserilizer()).create();
    }

    @Provides
    RequestQueue provideRequestQueue (Application app){
        return Volley.newRequestQueue(app);
    }

    @Provides
    @Singleton
    DataRepository provideDataRepository(RequestQueue requestQueue, RestaurantDao restaurantDao, Executor executor, Gson gson) {
        return new DataRepository(requestQueue, restaurantDao, executor, gson);
    }
}
