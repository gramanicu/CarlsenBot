/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

public class Main {
    public static void main(String[] args) {
        GameManager game = GameManager.getInstance();

        game.initializeGame();
        //game.startGame();
        game.getPieceByID(16).move("h3");
        game.getPieceByID(4).move("h2");
        game.getPieceByID(5).move("c3");
        game.getPieceByID(5).move("d5");
        game.getPieceByID(5).move("e7");
        game.getPieceByID(10).move("b4");
        game.getPieceByID(7).move("a3");
        game.getPieceByID(12).move("d4");
        game.getPieceByID(2).move("d3");


        System.out.println(game.printTable());
    }
}
