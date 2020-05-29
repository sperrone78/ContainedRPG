package com.sperrone.containedrpg.models.spells;

import com.sperrone.containedrpg.models.spells.Spell;

public class FrostSpell extends Spell {
    private int spellDamage;
    private int slowRounds;
    private String debuffName;

    public FrostSpell (String startName, String startSpellType, int startSpellDamage, int startSlowRounds) {
        super(startName, startSpellType);
        setSpellDamage(startSpellDamage);
        setSlowRounds(startSlowRounds);
        setDebuffName("Slowed");
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    public void setSpellDamage(int spellDamage) {
        this.spellDamage = spellDamage;
    }

    public int getSlowRounds() {
        return slowRounds;
    }

    public void setSlowRounds(int slowRounds) {
        this.slowRounds = slowRounds;
    }

    public String getDebuffName() {
        return debuffName;
    }

    public void setDebuffName(String debuffName) {
        this.debuffName = debuffName;
    }
}
