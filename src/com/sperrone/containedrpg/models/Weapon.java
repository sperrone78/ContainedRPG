package com.sperrone.containedrpg.models;

public class Weapon extends Item {

    private int attackBonus;
    private String weaponType;

    public Weapon (String startName, int startCost, int startAttackBonus, String startWeaponType) {
        super(startName,startCost);
        setAttackBonus(startAttackBonus);
        setWeaponType(startWeaponType);
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }
}
