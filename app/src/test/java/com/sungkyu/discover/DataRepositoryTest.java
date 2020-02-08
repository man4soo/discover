package com.sungkyu.discover;

import com.sungkyu.discover.db.Dao.RestaurantDao;
import com.sungkyu.discover.db.Entity.Restaurant;
import com.sungkyu.discover.network.RestaurantNetworkService;
import com.sungkyu.discover.utils.Constants;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DataRepositoryTest {

    @Inject
    public RestaurantNetworkService mRestaurantNetworkService;

    public DataRepositoryTest() {
    }

    @Test
    public void runRestaurantNetworkServiceTest() {
        mRestaurantNetworkService.getRestaurant(Constants.DD_LAT, Constants.DD_LAT, Constants.REQUEST_BATCH_NUMBER, Constants.DEFAULT_OFFSET)
                .enqueue(new Callback<List<Restaurant>>() {

                    @Override
                    public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                        assertTrue(response.body()== null);
                    }

                    @Override
                    public void onFailure(Call<List<Restaurant>> call, Throwable t) {

                    }
                });
    }

}
