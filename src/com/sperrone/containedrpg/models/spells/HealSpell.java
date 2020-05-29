package com.sperrone.containedrpg.models.spells;

import com.sperrone.containedrpg.models.spells.Spell;

public class HealSpell extends Spell {
    private int healAmount;

    public HealSpell (String startName, String startSpellType, int startHealAmount) {
        super(startName, startSpellType);
        setHealAmount(startHealAmount);
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }
}
