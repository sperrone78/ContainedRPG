package com.sperrone.containedrpg.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Player newPlayer;
    private Boolean gameActive;
    private ArrayList<Monster> gameMonsters;
    private Store startingStore;

    public Game () {

    }

    public void buildNewGame () {
        //Create the Player
        System.out.println("Welcome to the Game!");
        System.out.println("Please Enter your name: ");
        Scanner input = new Scanner(System.in);
        String startName = input.nextLine();
        System.out.println("Please enter your class: ");
        String startClass = input.nextLine();
        newPlayer = new Player (startName, startClass);

        //Add Monsters to the Game
        gameMonsters = new ArrayList<>();
        setGameMonsters();
        //System.out.println(gameMonsters);
        NPC storeOwner = new NPC();
        setStartingStore(new Store("Test Store", "General",storeOwner));
        //Activate the Game
        gameActive = true;
    }

    public int getNextAction () {
        Scanner input = new Scanner(System.in);
        boolean isValid = false;
        int nextAction=4;
        do {
            System.out.println("What would you like to do " + newPlayer.getName() + " (" + newPlayer.getCurrentHealth() +
                    "/" + newPlayer.getMaxHealth() + ") ?");
            System.out.println("Type: 1: Exit, 2: Fight, 3: Inventory, 4: Char, 5: Spend Essence, 6: Save Game, " +
                    "7: Show Equipment");
            if (input.hasNextInt()) {
                nextAction = input.nextInt();
                isValid = true;
            } else {
                input.nextLine();
                System.out.println("Please enter a number between 1 and 5");
            }
        } while (!isValid);
        return nextAction;
    }

    public Player getNewPlayer() { return newPlayer; }

    public Boolean getGameActive () {
        return gameActive;
    }

    public void setGameActive (Boolean newState) {
        gameActive = newState;
    }

    public ArrayList<Monster> getGameMonsters() {
        return gameMonsters;
    }

    public void setGameMonsters() {
        Monster firstMonster = new Monster("Shrek", "Ogre",
                1, 10, 10,10,
                10,10, 10, 1);
        getGameMonsters().add(firstMonster);
    }

    public void spendEssenceInterface () {
        HashMap essenceToLevel = newPlayer.getEssenceToLevel();
        System.out.println("Available Essence Categories: (amount needed) \n" +
                "Melee Attack: ("+ essenceToLevel.get("melee attack") + ")\n" +
                "Ranged Attack: (" + essenceToLevel.get("ranged attack") + ")\n" +
                "Magic Bonus: (" + essenceToLevel.get("magic bonus") + ")\n" +
                "Physical Shield: (" + essenceToLevel.get("physical shield") + ")\n" +
                "Magic Shield: (" + essenceToLevel.get("magic shield") + ")\n" +
                "Vitality: (" + essenceToLevel.get("vitality") + ")\n" +
                "Charisma: (" + essenceToLevel.get("charisma") + ")");
        System.out.println("Please Choose a Category:");
        Scanner input = new Scanner(System.in);
        String essenceOption = input.nextLine().toLowerCase();
        newPlayer.spendEssence(essenceOption);
    }

    public void fight (Monster monster) {
        Fight newFight = new Fight (newPlayer, monster);
        Scanner input = new Scanner(System.in);

        int outcome = 0;

        //Fight! Rounds until one actor dies or player flees
        while (outcome < 1) {
            System.out.println("Fight Round #" + newFight.getRounds());
            System.out.println(newPlayer.getName() + " (" + newPlayer.getCurrentHealth() + "/" +
                    newPlayer.getMaxHealth() + ")"  + " vs " + monster.getName() + " (" + monster.getCurrentHealth() +
                    "/" + monster.getMaxHealth() + ")");
            System.out.println("Choose: Fight(F) or Run(R)");
            String fightOption = input.nextLine().toUpperCase();
            switch (fightOption) {
                case "FIGHT":
                case "F":
                    outcome = newFight.fightRound();
                    newFight.increaseRounds();
                    break;
                case "RUN":
                case "R":
                    outcome = 3;
                    break;
            }
        }

        //Figure out what to do after the fight
        switch (outcome) {
            case 1: //Player Wins
                System.out.println(newPlayer.getName() + " defeated " + monster.getName() + "!");
                System.out.println(newPlayer.getName() + " gains: \n" +
                        monster.getEssenceDropped() + " Essence \n" +
                        monster.getGoldDropped() + " Gold");
                newPlayer.gainEssence(monster.getEssenceDropped());
                newPlayer.gainGold(monster.getGoldDropped());
                monster.setCurrentHealth(10);
                monster.clearDebuffs();
                break;
            case 2: //Monster Wins
                System.out.println(newPlayer.getName() + " was defeated by " + monster.getName() + ".");
                //update revive player somewhere
                break;
            case 3: //Player Flees
                //Probably need to add code to rand chance to flee - for now it succeeds
                System.out.println(newPlayer.getName() + " flees from " + monster.getName() + ".");
                break;
        }
    }

    public Store getStartingStore() {
        return startingStore;
    }

    public void setStartingStore(Store startingStore) {
        this.startingStore = startingStore;
    }

    public void displayStore() {
        ArrayList<Item> inventory = this.startingStore.getInventory();
        int itemNumber = 1;
        System.out.println("#: Name(Cost)");
        System.out.println("----------");

        for (Item item : inventory) {
            System.out.println(itemNumber + ":" + item.getName() + "(" + item.getCost() + ")");
            itemNumber++;
        }

        System.out.println("Which item number would you like to purchase?");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
    }
}
