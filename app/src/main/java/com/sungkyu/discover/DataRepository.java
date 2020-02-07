package com.sungkyu.discover;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sungkyu.discover.db.Dao.RestaurantDao;
import com.sungkyu.discover.db.Entity.Restaurant;
import com.sungkyu.discover.db.RestaurantDB;
import com.sungkyu.discover.utils.Constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class DataRepository {
    private String TAG = "DataRepository";
    private RequestQueue mRequestQueue;
    private Executor mExecutor;
    private RestaurantDao mRestaurantDao;
    private Gson mGson;
    private Set<Integer> mSet;
    private int mLastInsertedId;

    @Inject
    public DataRepository(RequestQueue requestQueue, RestaurantDao restaurantDao, Executor executor, Gson gson, Set<Integer> set) {
        mRequestQueue = requestQueue;
        mRestaurantDao = restaurantDao;
        mExecutor = executor;
        mGson = gson;
        mSet = set;
        mLastInsertedId = 0;
    }

    public LiveData<List<Restaurant>> getRestaurant() {
        fetchData(mLastInsertedId);
        return mRestaurantDao.getAll();
    }

    private void fetchData(final int start) {
        if(!mSet.add(start)) return;
        mExecutor.execute(() -> {
            int idx = start;
            while (idx <= Constants.REQUEST_BATCH_NUMBER + start) {
                String url = Constants.BASE_URL + idx;
                mRequestQueue.add( new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int currentIdx = Integer.valueOf(url.substring(url.indexOf("offset")+7));

                                if (response == null || response.isEmpty() || response.equals("[]")) {
                                    Log.i(TAG, "Server response is null or empty");
                                    mSet.clear();
                                    return;
                                }

                                mExecutor.execute(() -> {
                                    Restaurant[] restaurants = mGson.fromJson(response, Restaurant[].class);
                                    for (Restaurant r : restaurants) {
                                        mRestaurantDao.save(r);
                                        mLastInsertedId++;
                                    }
                                });

                                if(currentIdx != 0 && (currentIdx % Constants.REQUEST_BATCH_NUMBER == 0))
                                    fetchData(currentIdx);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "getReviewData error : " + error.toString());
                    }
                }));
                idx += 2;
            }
        });
    }
}
