package com.sperrone.containedrpg.models.spells;

public class Spell {

    private String name;
    private int level;
    private String spellType;

    public Spell (String startName, String startSpellType) {
        setName(startName);
        setSpellType(startSpellType);
        setLevel(1);
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
}
