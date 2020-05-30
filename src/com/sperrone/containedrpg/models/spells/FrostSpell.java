package com.sperrone.containedrpg.models.spells;

import com.sperrone.containedrpg.models.spells.Spell;

public class FrostSpell extends Spell {
    private int spellDamage;
    private int slowAmount;
    private String debuffName;

    public FrostSpell (String startName, String startSpellType, int startSpellDamage, int startSlowAmount) {
        super(startName, startSpellType);
        setSpellDamage(startSpellDamage);
        setSlowAmount(startSlowAmount);
        setDebuffName("Slowed");
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    public void setSpellDamage(int spellDamage) {
        this.spellDamage = spellDamage;
    }

    public int getSlowAmount() {
        return slowAmount;
    }

    public void setSlowAmount(int slowAmount) {
        this.slowAmount = slowAmount;
    }

    public String getDebuffName() {
        return debuffName;
    }

    public void setDebuffName(String debuffName) {
        this.debuffName = debuffName;
    }
}
