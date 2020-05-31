package com.sperrone.containedrpg.models;

public class Spell {

    private String name;
    private int level;
    private String spellType;
    private int spellValue;
    private int tickValue;
    private String debuffName;

    public Spell (String startName, String startSpellType,int startSpellValue, int startTickValue, String startDebuffName) {
        setName(startName);
        setSpellType(startSpellType);
        setLevel(1);
        setSpellValue(startSpellValue);
        setTickValue(startTickValue);
        setDebuffName(startDebuffName);
    }

    public void increaseLevel () {
        this.setLevel(this.getLevel() + 1);
    }

    @Override
    public String toString() {
        return (this.getName() + "<" + this.getSpellType() + ">");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSpellType() {
        return spellType;
    }

    public void setSpellType(String spellType) {
        this.spellType = spellType;
    }

    public int getSpellValue() {
        return spellValue;
    }

    public void setSpellValue(int spellValue) {
        this.spellValue = spellValue;
    }

    public int getTickValue() {
        return tickValue;
    }

    public void setTickValue(int tickValue) {
        this.tickValue = tickValue;
    }

    public String getDebuffName() {
        return debuffName;
    }

    public void setDebuffName(String debuffName) {
        this.debuffName = debuffName;
    }
}
