package com.sperrone.containedrpg.models;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Random;

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

    private int monsterAttackRound () {
        Random rand = new Random();
        double monsterRandMod = rand.nextDouble();
        double monsterAttackBase = monster.getAttackModifier() + monster.getStrength();
        double monsterAttack = monsterAttackBase * monsterRandMod;
        int playerNewHealth = player.getCurrentHealth() - (int) monsterAttack;
        System.out.println(player.getName() + " was hit for " + (int)monsterAttack);

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

    private int playerAttackRound () {
        Random rand = new Random();
        double playerRandMod = rand.nextDouble();
        double playerAttackBase = player.getAttackModifier() + player.getStrength();
        double playerAttack = playerAttackBase * playerRandMod;
        int monsterNewHealth = monster.getCurrentHealth() - (int) playerAttack;
        System.out.println(monster.getName() + " was hit for " + (int)playerAttack);

        if (monsterNewHealth <= 0) {
            //Return Monster dead
            monster.setCurrentHealth(0);
            return 1;
        } else {
            monster.setCurrentHealth(monsterNewHealth);
            System.out.println(monster.getName() + " : " + monster.getCurrentHealth() + " / " + monster.getMaxHealth());
            return 0;
        }
    }

    public int fightRound () {
        int outcome = 0; //0 = both alive, 1 = monster dead, 2 = player dead
        //see who attacks first
        if (player.getSpeed() >= monster.getSpeed()) {
            //Player Attacks First
            outcome = (playerAttackRound() == 0) ? monsterAttackRound() : 1;
        } else {
            //Monster Attacks First
            outcome = (monsterAttackRound() == 0) ? playerAttackRound() : 2;
        }
        return outcome;
    }

}
