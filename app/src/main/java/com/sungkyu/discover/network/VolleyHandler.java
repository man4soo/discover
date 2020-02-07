package com.sungkyu.discover.network;

import android.app.Application;
import android.media.AsyncPlayer;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener ;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sungkyu.discover.db.Entity.Restaurant;

public class VolleyHandler {

    private String TAG = "VolleyHandler";

    private static RequestQueue mQueue;

    public static void initVolleyHanlder(Application app) {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(app);
        }
    }

    private VolleyHandler() {
        super();
    }

    public void getRestaurant() {
        String url = "test";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response == null || response.isEmpty()) {
                            Log.i(TAG, "Server response is null or empty");
                            return;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "getReviewData error : " + error.toString());
            }
        });

        mQueue.add(stringRequest);

    }

}
