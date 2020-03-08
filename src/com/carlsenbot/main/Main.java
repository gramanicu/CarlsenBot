package com.carlsenbot.main;

import com.carlsenbot.pieces.Pawn;
import com.carlsenbot.position.Position;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game g = new Game();
        g.initializeGame();
        System.out.println();


//        System.out.println(g.printPiecesPositions());
//        System.out.println(g.getTable());
        System.out.println(g.printTable());
        g.startGame();

    }
}
