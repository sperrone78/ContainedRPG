package com.sperrone.containedrpg.models;

import com.sperrone.containedrpg.models.spells.FireSpell;
import com.sperrone.containedrpg.models.spells.FrostSpell;
import com.sperrone.containedrpg.models.spells.HealSpell;
import com.sperrone.containedrpg.models.spells.Spell;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Actor {
    private String charClass;
    private int essence;
    private HashMap<String, Integer> essenceToLevel = new HashMap<>();
    private ArrayList<Spell> spellList = new ArrayList<>();
    private int maxHealth;
    private int currentHealth;
    private int weaponAttackModifier; //weapon
    private int armorDefModifier;
    private int speed;
    private int meleeAttackMod; //essence level
    private int rangedAttackMod;
    private int magicMod;
    private int physicalShield;
    private int magicShield;
    private int charisma;
    private int gold;

    public Player (String startName, String startClass) {
        super(startName);
        setCharClass(startClass);
        setEssenceToLevel("physical shield", 10);
        setEssenceToLevel("magic shield", 10);
        setEssenceToLevel("ranged attack", 10);
        setEssenceToLevel("magic bonus", 10);
        setEssenceToLevel("melee attack", 10);
        setEssenceToLevel("vitality", 10);
        setEssenceToLevel("charisma", 10);
        setEssence(0);
        setMaxHealth(100);
        setCurrentHealth(100);
        setWeaponAttackModifier(1);
        setArmorDefModifier(1);
        setSpeed(20);
        setMeleeAttackMod(1);
        setRangedAttackMod(1);
        setMagicMod(1);
        setPhysicalShield(0);
        setMagicShield(0);
        setCharisma(1);
        gold=1;
        Spell healSpell = new HealSpell("Minor Heal", "Heal", 100);
        Spell fireball = new FireSpell("Fireball", "Damage", 10,5);
        Spell frostBolt = new FrostSpell("Frostbolt", "Damage", 15,2);
        addSpell(healSpell);
        addSpell(fireball);
        addSpell(frostBolt);
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public int getEssence() {
        return essence;
    }

    public void setEssence(int essence) {
        this.essence = essence;
    }

    public void gainEssence (int essence) {
        this.essence += essence;
    }

    public void spendEssence(String stat) {
        System.out.println("Leveling: " + stat);
        boolean isStatPresent = this.essenceToLevel.containsKey(stat);
        if (!isStatPresent) {
            System.out.println("Unknown Stat");
        } else {
            int essenceNeeded = this.essenceToLevel.get(stat);
            if (this.essenceToLevel.get(stat) <= this.essence) {
                System.out.println("We have enough essence!");
                this.essence -= essenceNeeded;
                this.essenceToLevel.put(stat, this.essenceToLevel.get(stat) + 10);
                switch (stat) {
                    case "melee attack":
                        setMeleeAttackMod(this.getMeleeAttackMod() + 5);
                        break;
                }
            } else {
                System.out.println("Insufficient Essence.");
            }
        }
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

    public int getWeaponAttackModifier() {
        return weaponAttackModifier;
    }

    public void setWeaponAttackModifier(int weaponAttackModifier) {
        this.weaponAttackModifier = weaponAttackModifier;
    }

    public int getArmorDefModifier() {
        return armorDefModifier;
    }

    public void setArmorDefModifier(int armorDefModifier) {
        this.armorDefModifier = armorDefModifier;
    }

    public int getMeleeAttackMod() {
        return meleeAttackMod;
    }

    public void setMeleeAttackMod(int meleeAttackMod) {
        this.meleeAttackMod = meleeAttackMod;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRangedAttackMod() {
        return rangedAttackMod;
    }

    public void setRangedAttackMod(int rangedAttackMod) {
        this.rangedAttackMod = rangedAttackMod;
    }

    public int getMagicMod() {
        return magicMod;
    }

    public void setMagicMod(int magicMod) {
        this.magicMod = magicMod;
    }

    public int getPhysicalShield() {
        return physicalShield;
    }

    public void setPhysicalShield(int physicalShield) {
        this.physicalShield = physicalShield;
    }

    public int getMagicShield() {
        return magicShield;
    }

    public void setMagicShield(int magicShield) {
        this.magicShield = magicShield;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public HashMap<String, Integer> getEssenceToLevel() {
        return essenceToLevel;
    }

    public void setEssenceToLevel(String stat, int statValue) {
        this.essenceToLevel.put(stat, statValue);
    }

    public int getGold() {
        return gold;
    }

    public void gainGold(int gold) {
        this.gold += gold;
    }

    public void displayPlayer () {
        String output = "Character Info: " + this.getName() + "\n" +
                "Class: " + this.getCharClass() + "\n" +
                "Current Unspent Essence: " + this.getEssence() + "\n" +
                "Health: " + this.getCurrentHealth() + "/" + this.getMaxHealth() + "\n" +
                "Melee Attack Bonus: " + this.getMeleeAttackMod() +  "\n" +
                "Magic Bonus: " + this.getMagicMod() + "\n" +
                "Ranged Attack Bonus: " + this.getRangedAttackMod() + "\n" +
                "Physical Shield: " + this.getPhysicalShield() + "\n" +
                "Magic Shield: " + this.getMagicShield() + "\n" +
                "Spells" + this.displaySpells() + "\n" +
                "Gold: " + this.getGold();
        System.out.println(output);
    }

    public ArrayList<Spell> getSpellList() {
        return spellList;
    }

    public void addSpell (Spell spell) {
        spellList.add(spell);
    }

    public String displaySpells () {
        return spellList.toString();
    }
}
