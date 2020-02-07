package com.sungkyu.discover.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sungkyu.discover.R;
import com.sungkyu.discover.viewModels.DiscoverViewModel;
import com.sungkyu.discover.viewModels.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    public ViewModelFactory mViewModelFactory;

    private RecyclerView mRecyclerView;
    private DiscoverAdapter mDiscoverAdapter;

    private DiscoverViewModel mDiscoverViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        showRecylerView();

        mDiscoverViewModel = new ViewModelProvider(this, mViewModelFactory)
                .get(DiscoverViewModel.class);
        mDiscoverViewModel.getRestaurants().observe(this, restaurants -> {
            mDiscoverAdapter.addRestaurant(restaurants);
        });
    }

    private void showRecylerView() {
        mDiscoverAdapter = new DiscoverAdapter(this);
        mRecyclerView.setAdapter(mDiscoverAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
