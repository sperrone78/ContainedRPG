package com.sperrone.containedrpg.models;

public class Item {

    private String name;
    private int cost;
    private int sellValue;
    private String itemType;
    private int itemMinValue; //damage, armor, consumable value
    private int itemMaxValue;
    private String quality;
    private Boolean isStackable;
    private int weight;


    public Item (String startName, int startCost, int startSellValue, String startItemType, int startItemMinValue,
                 int startItemMaxValue, String startQuality, Boolean startIsStackable, int startWeight) {
        setName(startName);
        setCost(startCost);
        setSellValue(startSellValue);
        setItemType(startItemType);
        setItemMaxValue(startItemMaxValue);
        setItemMinValue(startItemMinValue);
        setQuality(startQuality);
        setIsStackable(startIsStackable);
        setWeight(startWeight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSellValue() {
        return sellValue;
    }

    public void setSellValue(int sellValue) {
        this.sellValue = sellValue;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemMinValue() {
        return itemMinValue;
    }

    public void setItemMinValue(int itemMinValue) {
        this.itemMinValue = itemMinValue;
    }

    public int getItemMaxValue() {
        return itemMaxValue;
    }

    public void setItemMaxValue(int itemMaxValue) {
        this.itemMaxValue = itemMaxValue;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Boolean getIsStackable() {
        return isStackable;
    }

    public void setIsStackable(Boolean stackable) {
        isStackable = stackable;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
