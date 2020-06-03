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

    public int getInputInt (String preText) {
        Scanner input = new Scanner(System.in);
        System.out.println(preText);
        return input.nextInt();
    }

    public String getInputString (String preText) {
        Scanner input = new Scanner(System.in);
        System.out.println(preText);
        return input.nextLine();
    }

    public int getNextAction () {
        Scanner input = new Scanner(System.in);
        boolean isValid = false;
        int nextAction=4;
        do {
            System.out.println("What would you like to do " + newPlayer.getName() + " (" + newPlayer.getCurrentHealth() +
                    "/" + newPlayer.getMaxHealth() + ") ?");
            System.out.println("1: Exit \n" +
                    "2: Fight\n" +
                    "3: Inventory\n" +
                    "4: Character Information\n" +
                    "5: Spend Essence\n" +
                    "6: Save Game\n" +
                    "7: Show Equipment\n" +
                    "8: Visit the Store");
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
        String essenceOption = getInputString("Please Choose a Category:").toLowerCase();
        newPlayer.spendEssence(essenceOption);
    }

    public void fight (Monster monster) {
        Fight newFight = new Fight (newPlayer, monster);
        int outcome = 0;

        //Fight! Rounds until one actor dies or player flees
        while (outcome < 1) {
            System.out.println("Fight Round #" + newFight.getRounds());
            System.out.println(newPlayer.getName() + " (" + newPlayer.getCurrentHealth() + "/" +
                    newPlayer.getMaxHealth() + ")"  + " vs " + monster.getName() + " (" + monster.getCurrentHealth() +
                    "/" + monster.getMaxHealth() + ")");
            String fightOption = getInputString("Choose: Fight(F) or Run(R)").toUpperCase();
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
    public void backpackChoice() {
        Boolean activeBackpack = true;
        while (activeBackpack) {
            newPlayer.displayBackpack();
            String backpackChoice = getInputString("What item would you like to use or equip? " +
                    "Type 0 to go back to the main menu");
            if (backpackChoice.equals("0")) {
                activeBackpack = false;
            } else {
                ArrayList<Item> backpack = newPlayer.getBackpack();
                boolean isItemInBackpack = backpack.contains(backpackChoice);
                if (isItemInBackpack) {
                    System.out.println("I found the item i n the backpack");
                } else {
                    System.out.println("I don't recognize that item, please try again");
                }
            }
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
        Boolean inStore = true;

        while (inStore) {
            System.out.println("#: Name(Cost)");
            System.out.println("----------");
            int itemNumber = 1;
            for (Item item : inventory) {
                System.out.println(itemNumber + ":" + item.getName() + "(" + item.getCost() + ")");
                itemNumber++;
            }
            System.out.println("0: Exit Store");
            System.out.println(newPlayer.getName() + " has " + newPlayer.getGold() + " gold");

            int storeOption = getInputInt("Which item number would you like to purchase? Press '0' to Exit");
            if (storeOption == 0) {
                inStore = false;
            } else if (storeOption > inventory.size()) {
                System.out.println("Invalid option chosen");
            } else {
                Item itemSelected = inventory.get(storeOption-1);
                Boolean canPurchase = newPlayer.checkIfCanPurchase(itemSelected);
                if (canPurchase) {
                    String validateOption = getInputString("Purchase " + itemSelected.getName() + "? (Y or N)");
                    switch (validateOption.toUpperCase()) {
                        case "Y":
                            newPlayer.addItemToBackpack(itemSelected);
                            newPlayer.spendGold(itemSelected.getCost());
                            break;
                        default:
                            System.out.println("Make another choice");
                    }
                } else {
                    System.out.println("Unable to purchase - insufficient gold");
                }
            }
        }

    }
}
