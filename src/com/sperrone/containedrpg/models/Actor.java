package com.sperrone.containedrpg.models;

public class Actor {

    private String name;
    private String currentLocation; //Need to make this a new object later

    public Actor (String startName) {
        setName(startName);
        setCurrentLocation("Town");
    }

    public String getInfo () {
        String returnInfo = getName() + " is at location: " + getCurrentLocation();
        return returnInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}
