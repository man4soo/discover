package com.sungkyu.discover.di.modules;

import android.app.Application;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sungkyu.discover.db.Entity.Restaurant;
import com.sungkyu.discover.network.RestaurantNetworkService;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sungkyu.discover.utils.Constants.BASE_URL;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    RestaurantNetworkService provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(RestaurantNetworkService.class);
    }

}
