package com.sungkyu.discover.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sungkyu.discover.R;
import com.sungkyu.discover.db.Entity.Restaurant;
import com.sungkyu.discover.utils.Constants;
import com.sungkyu.discover.viewModels.DiscoverViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements DiscoverAdapter.ItemClickListener{

    private RecyclerView mRecyclerView;
    private DiscoverAdapter mDiscoverAdapter;

    @Inject
    DiscoverViewModel mDiscoverViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        showRecylerView();

        mDiscoverViewModel.getRestaurants().observe(this, restaurants -> {
            mDiscoverAdapter.addRestaurant(restaurants);
        });
        mDiscoverAdapter.setItemClickListener(this);
    }

    private void showRecylerView() {
        mDiscoverAdapter = new DiscoverAdapter(this);
        mRecyclerView.setAdapter(mDiscoverAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(Restaurant r) {
        Toast.makeText(
                getApplicationContext(),
                r.toString(),
                Toast.LENGTH_SHORT).show();
    }

}
