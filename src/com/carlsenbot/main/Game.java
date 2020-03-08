package com.carlsenbot.main;

import com.carlsenbot.pieces.*;
import com.carlsenbot.position.Position;

public class Game {
    private boolean isWhiteTurn;
    private int round;

    /**
     * All the pieces are stored in a matrix, as it can offer faster access to the pieces.
     * First row of the matrix [0][*] - white pieces
     * Second row of the matrix [1][*] - black pieces
     * The convention for the pieces indexes is:
     * [*][0] - King
     * [*][1] - Queen
     * [*][2,3] - Rooks
     * [*][4,5] - Knights
     * [*][6,7] - Bishops
     * [*][8-15] - Pawns
     */
    private Piece[][] pieces;

    private Piece[] initWhitePieces(){
        PieceColor color = PieceColor.White;
        Piece[] newPiece = new Piece[16];

        // Main pieces
        newPiece[2] = new Rook(color, new Position("a1"));
        newPiece[4] = new Knight(color, new Position("b1"));
        newPiece[6] = new Bishop(color, new Position("c1"));
        newPiece[1] = new Queen(color, new Position("d1"));
        newPiece[0] = new King(color, new Position("e1"));
        newPiece[7] = new Bishop(color, new Position("f1"));
        newPiece[5] = new Knight(color, new Position("g1"));
        newPiece[3] = new Rook(color, new Position("h1"));

        // Pawns
        newPiece[8] = new Pawn(color, new Position("a2"));
        newPiece[9] = new Pawn(color, new Position("b2"));
        newPiece[10] = new Pawn(color, new Position("c2"));
        newPiece[11] = new Pawn(color, new Position("d2"));
        newPiece[12] = new Pawn(color, new Position("e2"));
        newPiece[13] = new Pawn(color, new Position("f2"));
        newPiece[14] = new Pawn(color, new Position("g2"));
        newPiece[15] = new Pawn(color, new Position("h2"));

        return newPiece;
    }

    private Piece[] initBlackPieces(){
        PieceColor color = PieceColor.Black;
        Piece[] newPiece = new Piece[16];

        // Main pieces
        newPiece[2] = new Rook(color, new Position("a8"));
        newPiece[4] = new Knight(color, new Position("b8"));
        newPiece[6] = new Bishop(color, new Position("c8"));
        newPiece[1] = new Queen(color, new Position("d8"));
        newPiece[0] = new King(color, new Position("e8"));
        newPiece[7] = new Bishop(color, new Position("f8"));
        newPiece[5] = new Knight(color, new Position("g8"));
        newPiece[3] = new Rook(color, new Position("h8"));

        // Pawns
        newPiece[8] = new Pawn(color, new Position("a7"));
        newPiece[9] = new Pawn(color, new Position("b7"));
        newPiece[10] = new Pawn(color, new Position("c7"));
        newPiece[11] = new Pawn(color, new Position("d7"));
        newPiece[12] = new Pawn(color, new Position("e7"));
        newPiece[13] = new Pawn(color, new Position("f7"));
        newPiece[14] = new Pawn(color, new Position("g7"));
        newPiece[15] = new Pawn(color, new Position("h7"));

        return newPiece;
    }


    public Game() {
        pieces = new Piece[2][16];
    }

    public boolean initializeGame() {
        pieces[0] = initWhitePieces();
        pieces[1] = initBlackPieces();

        return true;
    }

    public boolean startGame() {
        return false;
    }

    public String getPiecesPositions() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < 16; ++i) {
            s.append(pieces[0][i].getSymbol());
            s.append(pieces[0][i].getPosition());
            s.append("\n");
        }

        for (int i = 0; i < 16; ++i) {
            s.append(pieces[1][i].getSymbol());
            s.append(pieces[1][i].getPosition());
            s.append("\n");
        }
        return s.toString();
    }
}
