package com.sperrone.containedrpg.models;

import java.util.ArrayList;

public class Store {

    private String name;
    private String storeType;
    private ArrayList<Item> inventory = new ArrayList<>();
    private NPC storeOwner;

    public Store (String startName, String startStoreType, NPC startStoreOwner) {
        setName(startName);
        setStoreType(startStoreType);
        setStoreOwner(startStoreOwner);
        buildInventory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void buildInventory() {
        //Add code to read from JSON file based on store type
        //Adding test code:
        Item apple = new Item("Apple", 1,1,"Consumable",
                1,3,"Poor", true,1);
        Item milk = new Item("Milk", 1,1,"Consumable",
                1,3,"Poor", true,1);
        Item standardSword = new Item("Standard Sword", 10,2,"1HWeapon",
                3,8,"Standard", false,1);
        Item standardShield = new Item("Standard Shield", 10,1,"Shield",
                2,2,"Standard", false,1);
        Item standardHelmet = new Item("Standard Helmet", 10,1,"Helmet",
                2,2,"Standard", false,1);
        Item standardChest = new Item("Standard Chest Plate", 10,1,"Chest",
                2,2,"Standard", false,1);
        Item standardPants = new Item("Standard Pants", 10,1,"Pants",
                2,2,"Standard", false,1);
        Item standardBoots = new Item("Standard Boots", 10,1,"Boots",
                2,2,"Standard", false,1);
        inventory.add(apple);
        inventory.add(milk);
        inventory.add(standardBoots);
        inventory.add(standardChest);
        inventory.add(standardHelmet);
        inventory.add(standardPants);
        inventory.add(standardShield);
        inventory.add(standardSword);
    }

    public NPC getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(NPC storeOwner) {
        this.storeOwner = storeOwner;
    }
}
