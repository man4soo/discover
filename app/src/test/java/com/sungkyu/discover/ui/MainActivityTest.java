package com.sungkyu.discover.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.sungkyu.discover.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainActivityTest {

    private MainActivity mMainActivity;

    @Test
    public void runTestRecyclerView() throws Exception {
        RecyclerView recyclerView = mMainActivity.findViewById(R.id.recycler_view);
        assertEquals(recyclerView, null);
    }

}
