package com.carlsenbot.main;

import com.carlsenbot.pieces.Pawn;
import com.carlsenbot.position.Position;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game game = Game.getInstance();

        game.initializeGame();
        System.out.println(game.printTable());

        game.getPieceByID(7).move("d2");

        System.out.println(game.printTable());

    }
}
