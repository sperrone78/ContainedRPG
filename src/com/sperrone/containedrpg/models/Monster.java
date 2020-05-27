package com.sperrone.containedrpg.models;

public class Monster extends Actor{
    private int maxHealth;
    private int currentHealth;
    private String monsterType;
    private int attackModifier;
    private int defModifier;
    private int speed;
    private int strength;

    public Monster (String startName, String startMonsterType, int startCurrentHealth,
                    int startMaxHealth, int startAttackModifier, int startDefModifier,
                    int startSpeed, int startStrength)  {
        super(startName);
        setMaxHealth(startMaxHealth);
        setCurrentHealth(startCurrentHealth);
        setMonsterType(startMonsterType);
        setAttackModifier(startAttackModifier);
        setDefModifier(startDefModifier);
        super.setCurrentLocation("Forest");
        setSpeed(startSpeed);
        setStrength(startStrength);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    public int getDefModifier() {
        return defModifier;
    }

    public void setDefModifier(int defModifier) {
        this.defModifier = defModifier;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
