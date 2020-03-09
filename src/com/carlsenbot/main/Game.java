/*
 * © 2020 Grama Nicolae, Radu Ionita, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

import com.carlsenbot.pieces.*;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Game {
    private static Game instance = null;
    private boolean isWhiteTurn;
    private int round;
    private Table table;
    private Piece[][] pieces;

    /**
     * All the pieces are stored in a matrix, as it can offer faster access
     * to the pieces.
     *
     * First row of the matrix [0][*] - white pieces
     * Second row of the matrix [1][*] - black pieces
     *
     * The convention for the pieces indexes is:
     * [*][0] - King
     * [*][1] - Queen
     * [*][2,3] - Rooks
     * [*][4,5] - Knights
     * [*][6,7] - Bishops
     * [*][8-15] - Pawns
     *
     * ! IMPORTANT !
     * The id's of the pieces are a bit different. Because the value of a empty
     * cell in the matrix of "positions" is 0, we need to use the 1 -> 16 range
     * for white pieces, respectively -1 -> -16 range for the black pieces.
     */


    private Piece[] initWhitePieces(){
        PieceColor color = PieceColor.White;
        Piece[] newPiece = new Piece[16];

        // Main pieces
        newPiece[2] = new Rook(color, "a1", 3);
        newPiece[4] = new Knight(color, "b1", 5);
        newPiece[6] = new Bishop(color, "c1", 7);
        newPiece[1] = new Queen(color, "d1", 2);
        newPiece[0] = new King(color, "e1", 1);
        newPiece[7] = new Bishop(color, "f1", 8);
        newPiece[5] = new Knight(color, "g1", 6);
        newPiece[3] = new Rook(color, "h1", 4);

        // Pawns
        newPiece[8] = new Pawn(color, "a2", 9);
        newPiece[9] = new Pawn(color, "b2", 10);
        newPiece[10] = new Pawn(color, "c2", 11);
        newPiece[11] = new Pawn(color, "d2", 12);
        newPiece[12] = new Pawn(color, "e2", 13);
        newPiece[13] = new Pawn(color, "f2", 14);
        newPiece[14] = new Pawn(color, "g2", 15);
        newPiece[15] = new Pawn(color, "h2", 16);

        return newPiece;
    }

    private Piece[] initBlackPieces(){
        PieceColor color = PieceColor.Black;
        Piece[] newPiece = new Piece[16];

        // Main pieces
        newPiece[2] = new Rook(color, "a8", -3);
        newPiece[4] = new Knight(color, "b8", -5);
        newPiece[6] = new Bishop(color, "c8", -7);
        newPiece[1] = new Queen(color, "d8", -2);
        newPiece[0] = new King(color, "e8", -1);
        newPiece[7] = new Bishop(color, "f8", -8);
        newPiece[5] = new Knight(color, "g8", -6);
        newPiece[3] = new Rook(color, "h8", -4);

        // Pawns
        newPiece[8] = new Pawn(color, "a7", -9);
        newPiece[9] = new Pawn(color, "b7", -10);
        newPiece[10] = new Pawn(color, "c7", -11);
        newPiece[11] = new Pawn(color, "d7", -12);
        newPiece[12] = new Pawn(color, "e7", -13);
        newPiece[13] = new Pawn(color, "f7", -14);
        newPiece[14] = new Pawn(color, "g7", -15);
        newPiece[15] = new Pawn(color, "h7", -16);

        return newPiece;
    }


    private Game() {
        pieces = new Piece[2][16];
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Piece getPieceByID(int id) {
        if(id > 0) {
            id -= 1;
            return pieces[0][id];
        } else {
            id += 1;
            return pieces[1][-id];
        }

    }

    private String getSymbolOfPiece(int id) {
        if (id == 0) {
            return " ";
        } else {
            return getPieceByID(id).getSymbol();
        }
    }

    // NOTE - probably will change name (or remove completely)
    public boolean movePieceGame(Piece piece, Position target) {
        return table.movePiece(piece, target);
    }

    public boolean initializeGame() {
        pieces[0] = initWhitePieces();
        pieces[1] = initBlackPieces();
        table = new Table(pieces);

        return true;
    }

    public boolean startGame() {
        return false;
    }

    public String printPiecesPositions() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 16; ++i) {
            sb.append(pieces[0][i].getSymbol());
            sb.append(pieces[0][i].getPosition());
            sb.append("\n");
        }

        for (int i = 0; i < 16; ++i) {
            sb.append(pieces[1][i].getSymbol());
            sb.append(pieces[1][i].getPosition());
            sb.append("\n");
        }
        return sb.toString();
    }

    public Table getTable() {
        return table;
    }


    public String printTable() {
        StringBuilder sb = new StringBuilder();
        byte[][] positions = table.getPositions();

        for (int i = 0; i < 8; ++i) {
            sb.append(8 - i);
            sb.append(" ");
            for (int j = 0; j < 8; ++j) {
                sb.append("[");
                sb.append(getSymbolOfPiece(positions[i][j]));
                sb.append("]");
            }
            sb.append("\n");
        }
        sb.append("   A  B  C  D  E  F  G  H\n");
        return sb.toString();
    }
}
