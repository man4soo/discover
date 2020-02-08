package com.sungkyu.discover.network;

import com.sungkyu.discover.db.Entity.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantNetworkService {
    @GET("/v2/restaurant/?")
    Call<List<Restaurant>> getRestaurant(@Query("lat") double lat,
                                         @Query("lng") double lng,
                                         @Query("limit") int limit,
                                         @Query("offset") int offset);
}
