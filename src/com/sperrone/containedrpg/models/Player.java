package com.sperrone.containedrpg.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player extends Actor {
    private String charClass;
    private int essence;
    private HashMap<String, Integer> essenceToLevel = new HashMap<>();
    private ArrayList<Spell> spellList = new ArrayList<>();
    private HashMap<Item, Integer> backpack = new HashMap<>();
    private HashMap<String, Item> equipped = new HashMap<>();
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
        setArmorDefModifier(1);
        setSpeed(20);
        setMeleeAttackMod(1);
        setRangedAttackMod(1);
        setMagicMod(1);
        setPhysicalShield(0);
        setMagicShield(0);
        setCharisma(1);
        gold=1;
        Spell healSpell = new Spell("minor heal", "HealSpell", 100, 0,null);
        Spell fireball = new Spell("fireball", "FireSpell", 10,5, "Smolder");
        Spell frostBolt = new Spell("frostbolt", "FrostSpell", 15,2, "Slowed");
        addSpell(healSpell);
        addSpell(fireball);
        addSpell(frostBolt);

        Item startSword = new Item("Rusty Dagger", 1,1,"1HWeapon",
                1,3,"Poor", false,1);

        equipItem(startSword,"1HWeapon");
        Item apple = new Item("Apple", 1,1,"Consumable",
                1,3,"Poor", true,1);

        addItemToBackpack(apple,4);
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
                    case "magic bonus":
                        setMagicMod(this.getMagicMod() + 5);
                        break;
                    case "physical shield":
                        setPhysicalShield(this.getPhysicalShield() + 2);
                        break;
                    case "magic shield":
                        setMagicShield(this.getMagicShield() + 2);
                        break;
                    case "vitality":
                        setMaxHealth(this.getMaxHealth() + essenceNeeded);
                        setCurrentHealth(this.getMaxHealth());
                        break;
                    case "charisma":
                        setCharisma(this.getCharisma() + 1);
                        break;
                    case "ranged attack":
                        setRangedAttackMod((this.getRangedAttackMod()+5));
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

    public HashMap<Item, Integer> getBackpack() {
        return backpack;
    }

    public void addItemToBackpack(Item newItem, int quantity) {
        if (this.backpack.containsKey(newItem)) {
            if (newItem.getIsStackable()) {
                this.backpack.put(newItem, this.backpack.get(newItem) + quantity);
            } else {
                int counter = 0;
                do {
                    this.backpack.put(newItem, 1);
                    counter++;
                } while (counter < quantity);
            }
        } else {
            this.backpack.put(newItem, quantity);
        }
        this.backpack.put(newItem,quantity);
    }

    public HashMap<String, Item> getEquipped() {
        return equipped;
    }

    public void displayBackpack() {
        this.backpack.forEach((k,v) -> System.out.println(k.getName() + "("+v+")"));
    }

    public void equipItem(Item newItem, String slot) {
        if (this.equipped.containsKey(slot)) {
            Item oldItem = this.equipped.get(slot);
            //replacing another item
            addItemToBackpack(oldItem,1);
        }
        this.equipped.put(slot, newItem);
    }

    public void displayEquipment () {
        this.equipped.forEach((k,v) -> System.out.println(k +": " +v.getName()));
    }

    public int getMinWeaponDamage () {
        if (this.equipped.containsKey("1HWeapon")){
            return this.equipped.get("1HWeapon").getItemMinValue();
        } else if (this.equipped.containsKey("2HWeapon")) {
            return this.equipped.get("2HWeapon").getItemMinValue();
        } else return 0;
    }

    public int getMaxWeaponDamage () {
        if (this.equipped.containsKey("1HWeapon")){
            return this.equipped.get("1HWeapon").getItemMaxValue();
        } else if (this.equipped.containsKey("2HWeapon")) {
            return this.equipped.get("2HWeapon").getItemMaxValue();
        } else return 0;
    }

}
