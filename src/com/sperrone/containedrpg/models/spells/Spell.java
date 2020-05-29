package com.sperrone.containedrpg.models.spells;

public class Spell {

    private String name;
    private int level;
    private String spellType;

    public Spell (String startName, String startSpellType) {
        name = startName;
        spellType = startSpellType;
        level = 1;
    }

    public void increaseLevel () {
        this.level++;
    }

    @Override
    public String toString() {
        return (this.name + "<" + this.spellType + ">");
    }
}
