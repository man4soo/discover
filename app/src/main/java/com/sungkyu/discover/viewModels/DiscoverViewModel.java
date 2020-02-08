package com.sungkyu.discover.viewModels;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sungkyu.discover.DataRepository;
import com.sungkyu.discover.db.Entity.Restaurant;
import com.sungkyu.discover.utils.Constants;

import java.util.List;

import javax.inject.Inject;

public class DiscoverViewModel extends ViewModel {

    private DataRepository mDataRepository;

    private LiveData<List<Restaurant>> mRestaurantList;
    @Inject
    public DiscoverViewModel(DataRepository dataRepository) {
        mDataRepository = dataRepository;
        mRestaurantList =  mDataRepository.getRestaurant(Constants.DD_LAT, Constants.DD_LNG);
    }

    public LiveData<List<Restaurant>> getRestaurants() {
        return mRestaurantList;
    }
}
