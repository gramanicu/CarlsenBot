package com.carlsenbot.main;

import com.carlsenbot.pieces.Pawn;
import com.carlsenbot.position.Position;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game game = Game.getInstance();

        game.initializeGame();
        System.out.println(game.printTable());
        
        game.getPieceByID(9).move("a4");
        game.getPieceByID(10).move("b3");
        game.getPieceByID(11).move("c4");
        game.getPieceByID(12).move("d3");
        game.getPieceByID(13).move("e4");
        game.getPieceByID(14).move("f3");
        game.getPieceByID(15).move("g4");
        game.getPieceByID(16).move("h3");

        System.out.println(game.printTable());

        game.getPieceByID(-9).move("a6");
        game.getPieceByID(-10).move("b5");
        game.getPieceByID(-11).move("c6");
        game.getPieceByID(-12).move("d5");
        game.getPieceByID(-13).move("e6");
        game.getPieceByID(-14).move("f5");
        game.getPieceByID(-15).move("g6");
        game.getPieceByID(-16).move("h5");
        game.getPieceByID(-5).move("d7");
        game.getPieceByID(-1).move("e7");
        game.getPieceByID(-1).move("d7");
        game.getPieceByID(7).move("d2");

        System.out.println(game.printTable());

    }
}
