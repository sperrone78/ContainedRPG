package com.sperrone.containedrpg.models;

public class Item {

    private String name;
    private double cost;

    public Item (String startName, int startCost) {
        setName(startName);
        setCost(startCost);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
