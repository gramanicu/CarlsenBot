package com.carlsenbot.main;

import com.carlsenbot.pieces.Pawn;
import com.carlsenbot.position.Position;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game g = new Game();
        g.initializeGame();
        System.out.println();
        g.startGame();
        System.out.println(g.getPieceByID(16).getPosition());
        g.getPieceByID(16).move(new Position("h3"),g.getTable());
        System.out.println(g.printTable());

    }
}
