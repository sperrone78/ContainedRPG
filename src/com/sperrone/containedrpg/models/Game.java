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
                10,10);
        getGameMonsters().add(firstMonster);
    }

    public void fight (Monster monster) {
        Fight newFight = new Fight (newPlayer, monster);
        int outcome = 0;
        outcome = newFight.fightRound();
        System.out.println("Outcome was : " + outcome);
    }
}
