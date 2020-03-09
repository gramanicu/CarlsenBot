/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

public class Main {
    public static void main(String[] args) {
        GameManager game = GameManager.getInstance();

        game.initializeGame();
        System.out.println(game.printTable());
        game.getPieceByID(9).move("a3");
        game.getPieceByID(2).move("d2");
        game.getPieceByID(3).move("a2");
        System.out.println(game.printTable());
//        game.getPieceByID(1).move("d2");
//        System.out.println(game.printTable());
    }
}
