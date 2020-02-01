package com.sungkyu.discover.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sungkyu.discover.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DiscoverAdapter mDiscoverAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        showRecylerView();
    }

    private void showRecylerView() {
        mDiscoverAdapter = new DiscoverAdapter(this);
        mRecyclerView.setAdapter(mDiscoverAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
