package com.sungkyu.discover;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.sungkyu.discover.db.Dao.RestaurantDao;
import com.sungkyu.discover.db.Entity.Restaurant;
import com.sungkyu.discover.network.RestaurantNetworkService;
import com.sungkyu.discover.utils.Constants;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
    private String TAG = "DataRepository";
    private final  Executor mExecutor;
    private final RestaurantDao mRestaurantDao;
    private final RestaurantNetworkService mRestaurantNetworkService;
    private Set<Integer> mSet;
    private int mLastInsertedId;

    @Inject
    public DataRepository(RestaurantNetworkService restaurantNetworkService, RestaurantDao restaurantDao, Executor executor, Set<Integer> set) {
        mRestaurantDao = restaurantDao;
        mExecutor = executor;
        mRestaurantNetworkService = restaurantNetworkService;
        mSet = set;
        mLastInsertedId = 0;
    }

    public LiveData<List<Restaurant>> getRestaurant(double lat, double lng) {
        fetchData(mLastInsertedId, lat, lng);
        return mRestaurantDao.getAll();
    }

    public void updateRestaurant(final Restaurant r) {
        mExecutor.execute(() -> {
            mRestaurantDao.save(r);
        });
    }

    private void fetchData(final int start, final double lat, final double lng) {
        if(!mSet.add(start)) return;
        mExecutor.execute(() -> {
            int idx = start;
            while (idx <= Constants.REQUEST_BATCH_NUMBER + start) {
                mRestaurantNetworkService.getRestaurant(lat, lng, Constants.DEFAULT_OFFSET, idx)
                        .enqueue(new Callback<List<Restaurant>>() {

                            @Override
                            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                                String url = response.raw().request().url().toString();
                                int currentIdx =Integer.valueOf(url.substring(url.indexOf("offset")+ Constants.OFFSET_STRING_LEN));

                                if (response == null || response.body() == null ||  response.body().isEmpty()) {
                                    Log.i(TAG, "Server response is null or empty");
                                    mSet.clear();
                                    return;
                                }

                                mExecutor.execute(() -> {
                                    for (Restaurant r : response.body()) {
                                        mRestaurantDao.save(r);
                                        mLastInsertedId++;
                                    }
                                });

                                if(currentIdx != 0 && (currentIdx % Constants.REQUEST_BATCH_NUMBER == 0))
                                    fetchData(currentIdx, lat, lng);
                            }

                            @Override
                            public void onFailure(Call<List<Restaurant>> call, Throwable t) {

                            }
                        });
                idx += Constants.DEFAULT_OFFSET;
            }
        });
    }
}
