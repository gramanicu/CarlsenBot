/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

public class Main {
    public static void main(String[] args) {
        GameManager game = GameManager.getInstance();

        game.initializeGame();
        System.out.println(game.printTable());
    }
}
