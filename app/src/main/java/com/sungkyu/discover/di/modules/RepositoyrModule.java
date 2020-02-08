package com.sungkyu.discover.di.modules;

import com.sungkyu.discover.DataRepository;
import com.sungkyu.discover.db.Dao.RestaurantDao;
import com.sungkyu.discover.network.RestaurantNetworkService;

import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DBModule.class, NetworkModule.class})
public class RepositoyrModule {

    @Provides
    Executor provideExecutor() {
        return Executors.newFixedThreadPool(4);
    }

    @Provides
    @Singleton
    DataRepository provideDataRepository(RestaurantNetworkService restaurantNetworkService, RestaurantDao restaurantDao, Executor executor) {
        return new DataRepository(restaurantNetworkService, restaurantDao, executor, new HashSet<Integer>());
    }
}
