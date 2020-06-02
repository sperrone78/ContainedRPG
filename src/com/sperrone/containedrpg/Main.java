package com.sperrone.containedrpg;

import com.google.gson.Gson;
import com.sperrone.containedrpg.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static void saveGame(Game game) {
        System.out.println("Saving Game...");
        Gson gson = new Gson();
        String json = gson.toJson(game);
        try {
            FileWriter fileWriter = new FileWriter("save.json");
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error in Saving file.");
            e.printStackTrace();
        }
    }

    private static Game loadGame() {
        System.out.println("Loading Game...");
        Gson gson = new Gson();
        try {
            File saveFile = new File("save.json");
            Scanner myReader = new Scanner(saveFile);
            String data = myReader.nextLine();
            Game newGame = gson.fromJson(data, Game.class);
            Player updatePlayer = newGame.getNewPlayer();
            ArrayList<Spell> spellList = updatePlayer.getSpellList();
            for (Spell spell : spellList) {

            }
            return newGame;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find save file");
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Contained RPG!");
        System.out.println("1: New Game\n" +
                "2: Load Game");
        Scanner input = new Scanner(System.in);
        int gameChoice = input.nextInt();
        Game newGame;
        Player newPlayer;
        ArrayList<Monster> monsters;

        switch (gameChoice) {
            case 1:
                newGame = new Game();
                newGame.buildNewGame();
                newPlayer = newGame.getNewPlayer();
                monsters = newGame.getGameMonsters();
                System.out.println(newPlayer.getName() + " is a " + newPlayer.getCharClass());
                break;
            case 2:
                newGame = loadGame();
                newPlayer = newGame.getNewPlayer();
                System.out.println("Class = " + newPlayer.getClass());
                monsters = newGame.getGameMonsters();
                break;
            default:
                System.out.println("I didn't understand - creating new game");
                newGame = new Game();
                newPlayer = newGame.getNewPlayer();
                monsters = newGame.getGameMonsters();
                System.out.println(newPlayer.getName() + " is a " + newPlayer.getCharClass());
                break;
        }


        while (newGame.getGameActive()) {
            int nextAction = newGame.getNextAction();
            switch (nextAction) {
                case 1:
                    newGame.setGameActive(false);
                    break;
                case 2:
                    //need code for fighting more than one round
                    //put that in the fight object
                    newGame.fight(monsters.get(0));
                    break;
                case 3:
                    newGame.backpackChoice();
                    break;
                case 4:
                    newPlayer.displayPlayer();
                    break;
                case 5:
                    newGame.spendEssenceInterface();
                    break;
                case 6:
                    saveGame(newGame);
                    break;
                case 7:
                    newPlayer.displayEquipment();
                    break;
                case 8: //Display Store
                    newGame.displayStore();
                    break;
                default:
                    System.out.println("I didn't understand your choice - try again");
            }

        }
    }
}