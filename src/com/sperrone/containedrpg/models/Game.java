package com.sperrone.containedrpg.models;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player newPlayer;
    private Boolean gameActive;
    private ArrayList<Monster> gameMonsters;

    public Game () {
        buildNewGame();
    }

    private void buildNewGame () {
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
        System.out.println(gameMonsters);

        //Activate the Game
        gameActive = true;
    }

    public int getNextAction () {
        System.out.println("What would you like to do " + newPlayer.getName() + " (" + newPlayer.getCurrentHealth() +
                "/" + newPlayer.getMaxHealth() + ") ?");
        System.out.println("Type: 1: Exit, 2: Fight, 3: Inventory, 4: Char");
        Scanner input = new Scanner(System.in);
        int nextAction = input.nextInt();
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
                100, 100, 10,10,
                10,10, 10, 1);
        getGameMonsters().add(firstMonster);
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
                newPlayer.gainEssence(monster.getEssenceDropped());
                newPlayer.gainGold(monster.getGoldDropped());
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
}
