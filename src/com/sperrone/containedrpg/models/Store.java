package com.sperrone.containedrpg.models;

import java.util.ArrayList;

public class Store {

    private String name;
    private String type;
    private ArrayList<Item> inventory = new ArrayList<>();
    private NPC storeOwner;

    public Store (String startName, String startType) {

    }
}
