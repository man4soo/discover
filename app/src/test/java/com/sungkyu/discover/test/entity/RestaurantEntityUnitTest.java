package com.sungkyu.discover.test.entity;

import com.sungkyu.discover.db.Entity.Restaurant;
import com.sungkyu.discover.utils.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class RestaurantEntityUnitTest {
    @Test
    public void testId() {
        Restaurant r = new Restaurant();
        r.setId(100);
        assertEquals(r.getId(), 100);
    }

    @Test
    public void testImgUrl() {
        Restaurant r = new Restaurant();
        r.setImgUrl(Constants.BASE_URL);
        assertEquals(r.getImgUrl(), Constants.BASE_URL);
    }

    @Test
    public void testName() {
        Restaurant r = new Restaurant();
        r.setName("restaurant");
        assertEquals(r.getName(), "restaurant");
    }

    @Test
    public void testDescription() {
        Restaurant r = new Restaurant();
        r.setDescription("description");
        assertEquals(r.getDescription(), "description");
    }

    @Test
    public void testStatus() {
        Restaurant r = new Restaurant();
        r.setStatus("open");
        assertEquals(r.getStatus(), "open");
    }
}
