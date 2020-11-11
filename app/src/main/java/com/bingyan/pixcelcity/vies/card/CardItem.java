package com.bingyan.pixcelcity.vies.card;

import com.bingyan.pixcelcity.bean.Location;

public class CardItem {

    Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "location=" + location +
                '}';
    }
}
