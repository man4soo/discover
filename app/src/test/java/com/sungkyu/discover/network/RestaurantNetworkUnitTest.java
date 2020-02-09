package com.sungkyu.discover.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sungkyu.discover.utils.Constants;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sungkyu.discover.utils.Constants.BASE_URL;

@RunWith(JUnit4.class)
public class RestaurantNetworkUnitTest extends TestCase {

    private RestaurantNetworkService mRestaurantNetworkService;

    @Before
    public void createService() {
        mRestaurantNetworkService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build()
                .create(RestaurantNetworkService.class);
    }

    @Test
    public void getRestaurants() {
        try {
            Response r = mRestaurantNetworkService.getRestaurant(
                    Constants.DD_LAT,
                    Constants.DD_LNG,
                    Constants.DEFAULT_OFFSET,
                    50
            ).execute();
            assertEquals(r.code(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
