package com.sperrone.containedrpg.models;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Fight {

    private Player player;
    private Monster monster;
    private int rounds;

    public Fight (Player newPlayer, Monster newMonster) {
        player = newPlayer;
        monster = newMonster;
        setRounds(1);
    }

    public int getRounds() {
        return rounds;
    }

    private void setRounds(int rounds) {
        this.rounds = rounds;
    }
    public void increaseRounds () {
        this.rounds ++;
    }

    private int monsterAttackRound () {
        Random rand = new Random();
        double monsterRandMod = rand.nextDouble();
        double monsterAttackBase = monster.getAttackModifier() + monster.getStrength();
        double monsterAttack = monsterAttackBase * monsterRandMod;
        if ((int)monsterAttack < player.getPhysicalShield()) {
            System.out.println(player.getName() + "'s Physical Shield absorbed all the damage");
            return 0;
        } else if (player.getPhysicalShield() == 0) {
            int playerNewHealth = player.getCurrentHealth() - (int) monsterAttack;
            System.out.println(player.getName() + " was hit for " + (int)monsterAttack + ".");
            if (playerNewHealth <= 0) {
                //Return Player dead
                System.out.println(player.getName() + " has died!");
                player.setCurrentHealth(0);
                return 2;
            } else {
                player.setCurrentHealth(playerNewHealth);
                return 0;
            }
        } else {
            int playerNewHealth = player.getCurrentHealth() - ( (int) monsterAttack - player.getPhysicalShield() );
            System.out.println(player.getName() + " was hit for " + (int)monsterAttack + ". Physical Shield blocked " +
                    player.getPhysicalShield() + " damage.");
            if (playerNewHealth <= 0) {
                //Return Player dead
                System.out.println(player.getName() + " has died!");
                player.setCurrentHealth(0);
                return 2;
            } else {
                player.setCurrentHealth(playerNewHealth);
                return 0;
            }
        }
    }

    private int playerAttackRound () {
        Random rand = new Random();
        //get min and max damage of weapon
        int minDamage = player.getMinWeaponDamage();
        int maxDamage = player.getMaxWeaponDamage();
        int playerAttack = rand.nextInt((maxDamage - minDamage) + 1) + minDamage;
        playerAttack += player.getMeleeAttackMod();
//        System.out.println("Player Min: " + minDamage + " Player Max: " + maxDamage + " Rand: " + playerAttack +
//                "MAM: " + player.getMeleeAttackMod());
        int monsterNewHealth = monster.getCurrentHealth() - playerAttack;
        System.out.println(monster.getName() + " was hit for " + playerAttack);

        if (monsterNewHealth <= 0) {
            //Return Monster dead
            monster.setCurrentHealth(0);
            return 1;
        } else {
            monster.setCurrentHealth(monsterNewHealth);
            return 0;
        }
    }

    private Spell getSpell (String spellCast) {
        ArrayList<Spell> spellList = player.getSpellList();
        for (Spell spell : spellList) {
            if (spell.getName().equals(spellCast)) {
                return spell;
            }
        }
        return null;
    }

    private int playerSpellAttackRound (String spellCast) {
        Spell spell = getSpell(spellCast);
        if (spell == null) {
            System.out.println("Unknown Spell - no damage done!");
            return 0;
        }
        int spellDamage = player.getMagicMod();
        String spellType = spell.getSpellType();

        switch (spellType) {
            case "FireSpell":
                System.out.println("Fireball = " + spell.toString());
                spellDamage += spell.getSpellValue();
                System.out.println("spell damage = " + spellDamage);
                int spellTick = spell.getTickValue() + player.getMagicMod()/2;
                System.out.println(monster.getName() + " is now Smoldering for " + spellTick + " per round");
                monster.addDebuff("Smolder", spellTick);
                break;
            case "FrostSpell":
                spellDamage += spell.getSpellValue();
                int slowedAmount = spell.getTickValue();
                System.out.println(monster.getName() + " is now slowed for " + slowedAmount);
                monster.addDebuff("Slowed", slowedAmount);
                break;
            case "HealSpell":
                int healAmount = spell.getTickValue();
                if ((healAmount + player.getCurrentHealth()) > player.getMaxHealth()) {
                    player.setCurrentHealth(player.getMaxHealth());
                } else {
                    player.setCurrentHealth(player.getCurrentHealth() + healAmount);
                }
                System.out.println(player.getName() + " healed for " + healAmount);
                return 0;
        }

        int monsterNewHealth = monster.getCurrentHealth() - spellDamage;
        System.out.println(monster.getName() + " was hit for " + spellDamage);

        if (monsterNewHealth <= 0) {
            //Return Monster dead
            monster.setCurrentHealth(0);
            return 1;
        } else {
            monster.setCurrentHealth(monsterNewHealth);
            return 0;
        }
    }

    public int fightRound () {
        int outcome = 0; //0 = both alive, 1 = monster dead, 2 = player dead
        //see who attacks first
        outcome = resolveDebuffs();

        System.out.println("Attack with your weapon (W) or with a spell (S)?");
        Scanner input = new Scanner(System.in);
        String attackOption = input.nextLine().toUpperCase();
        switch (attackOption) {
            case "WEAPON":
            case "W":
                if (player.getSpeed() >= monster.getSpeed()) {
                    //Player Attacks First
                    outcome = (playerAttackRound() == 0) ? monsterAttackRound() : 1;
                } else {
                    //Monster Attacks First
                    outcome = (monsterAttackRound() == 0) ? playerAttackRound() : 2;
                }
                return outcome;
            case "SPELL":
            case "S":
                System.out.println("Which Spell would you like to cast?");
                System.out.println(player.getSpellList());
                String spellOption = input.nextLine();
                if (player.getSpeed() >= monster.getSpeed()) {
                    //Player Attacks First
                    outcome = (playerSpellAttackRound(spellOption) == 0) ? monsterAttackRound() : 1;
                } else {
                    //Monster Attacks First
                    outcome = (monsterAttackRound() == 0) ? playerAttackRound() : 2;
                }
                return outcome;
        }
        return 0;
    }

    private int resolveDebuffs () {
        int outcome = 0;
        for (Map.Entry debuff : monster.getDebuffs().entrySet()) {
            String debuffName = (String)debuff.getKey();
            int debuffValue = ((int)debuff.getValue());
            switch (debuffName) {
                case "Slowed":
                    int currentSpeed = monster.getSpeed();
                    monster.setSpeed(currentSpeed/debuffValue);
                    System.out.println(monster.getName() + " is slowed this round");
                    break;
                case "Smolder":
                    int mch = monster.getCurrentHealth();
                    if (mch < debuffValue) {
                        monster.setCurrentHealth(0);
                        outcome = 1;
                    } else {
                        monster.setCurrentHealth(mch-debuffValue);
                        System.out.println(monster.getName() + " smolders - life now: " +
                                monster.getCurrentHealth() + "/" + monster.getMaxHealth());
                    }
                    break;
            }
        }
        return outcome;
    }
}
