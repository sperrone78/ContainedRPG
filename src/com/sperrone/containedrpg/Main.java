package com.sperrone.containedrpg;

import com.sperrone.containedrpg.models.Game;
import com.sperrone.containedrpg.models.Monster;
import com.sperrone.containedrpg.models.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game newGame = new Game();
        Player newPlayer = newGame.getNewPlayer();
        ArrayList<Monster> monsters = newGame.getGameMonsters();
        System.out.println(newPlayer.getName() + " is a level " +
                newPlayer.getLevel() + " " + newPlayer.getCharClass());

        while (newGame.getGameActive()) {
            int nextAction = newGame.getNextAction();
            switch (nextAction) {
                case 1:
                    newGame.setGameActive(false);
                    break;
                case 2:
                    //need code for fighting more than one round
                    newGame.fight(monsters.get(0));
                    break;
                case 3:
                    System.out.println("Look at all that cool stuff");
                    break;
                case 4:
                    System.out.println(newPlayer.getInfo());
                    break;
                default:
                    System.out.println("I didn't understand your choice - try again");
            }

        }
    }
}