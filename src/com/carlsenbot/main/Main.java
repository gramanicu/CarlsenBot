package com.carlsenbot.main;

public class Main {
    public static void main(String[] args) {
        Game g = new Game();
        g.initializeGame();
        System.out.println(g.getPiecesPositions());
        g.startGame();
    }
}
