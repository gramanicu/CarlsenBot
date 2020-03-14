/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

public class Main {
    public static void main(String[] args) {
        GameManager game = GameManager.getInstance();

        game.startGame();
        game.printTable();
    }
}
