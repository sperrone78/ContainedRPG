package com.sperrone.containedrpg.models.spells;

public class FireSpell extends Spell {
    private int spellDamage;
    private int tickDamage;
    private String debuffName;

    public FireSpell (String startName, String startSpellType, int startSpellDamage, int startTickDamage) {
        super(startName, startSpellType);
        setSpellDamage(startSpellDamage);
        setTickDamage(startTickDamage);
        setDebuffName("Smolder");
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    public void setSpellDamage(int spellDamage) {
        this.spellDamage = spellDamage;
    }

    public int getTickDamage() {
        return tickDamage;
    }

    public void setTickDamage(int tickDamage) {
        this.tickDamage = tickDamage;
    }

    public String getDebuffName() {
        return debuffName;
    }

    public void setDebuffName(String debuffName) {
        this.debuffName = debuffName;
    }
}
