package com.sungkyu.discover.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sungkyu.discover.DataRepository;
import com.sungkyu.discover.db.Entity.Restaurant;

import java.util.List;

import javax.inject.Inject;

public class DiscoverViewModel extends ViewModel {
    private LiveData<List<Restaurant>> mRestaurants;
    private DataRepository mDataRepository;

    @Inject
    public DiscoverViewModel(DataRepository dataRepository) {
        mDataRepository = dataRepository;
        mRestaurants = mDataRepository.getRestaurant();
    }

    public LiveData<List<Restaurant>> getRestaurants() {
        return this.mRestaurants;
    }
}
