package com.carlsenbot.main;

import com.carlsenbot.pieces.*;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Game {
    private boolean isWhiteTurn;
    private int round;
    private Table table;

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
     * cell in the matrix of "positions" is 0, we need to use the 1-16 range
     * for white pieces, respectively 17-32 range for the black pieces.
     *
     * The formula for id is:
     * id = row * 16 + line + 1,
     * where row and line are the position in the pieces matrix.
     */
    private Piece[][] pieces;

    private Piece[] initWhitePieces(){
        PieceColor color = PieceColor.White;
        Piece[] newPiece = new Piece[16];

        // Main pieces
        newPiece[2] = new Rook(color, new Position("a1"), 3);
        newPiece[4] = new Knight(color, new Position("b1"), 5);
        newPiece[6] = new Bishop(color, new Position("c1"), 7);
        newPiece[1] = new Queen(color, new Position("d1"), 2);
        newPiece[0] = new King(color, new Position("e1"), 1);
        newPiece[7] = new Bishop(color, new Position("f1"), 8);
        newPiece[5] = new Knight(color, new Position("g1"), 6);
        newPiece[3] = new Rook(color, new Position("h1"), 4);

        // Pawns
        newPiece[8] = new Pawn(color, new Position("a2"), 9);
        newPiece[9] = new Pawn(color, new Position("b2"), 10);
        newPiece[10] = new Pawn(color, new Position("c2"), 11);
        newPiece[11] = new Pawn(color, new Position("d2"), 12);
        newPiece[12] = new Pawn(color, new Position("e2"), 13);
        newPiece[13] = new Pawn(color, new Position("f2"), 14);
        newPiece[14] = new Pawn(color, new Position("g2"), 15);
        newPiece[15] = new Pawn(color, new Position("h2"), 16);

        return newPiece;
    }

    private Piece[] initBlackPieces(){
        PieceColor color = PieceColor.Black;
        Piece[] newPiece = new Piece[16];

        // Main pieces
        newPiece[2] = new Rook(color, new Position("a8"), 19);
        newPiece[4] = new Knight(color, new Position("b8"), 21);
        newPiece[6] = new Bishop(color, new Position("c8"), 23);
        newPiece[1] = new Queen(color, new Position("d8"), 18);
        newPiece[0] = new King(color, new Position("e8"), 17);
        newPiece[7] = new Bishop(color, new Position("f8"), 24);
        newPiece[5] = new Knight(color, new Position("g8"), 22);
        newPiece[3] = new Rook(color, new Position("h8"), 20);

        // Pawns
        newPiece[8] = new Pawn(color, new Position("a7"), 25);
        newPiece[9] = new Pawn(color, new Position("b7"), 26);
        newPiece[10] = new Pawn(color, new Position("c7"), 27);
        newPiece[11] = new Pawn(color, new Position("d7"), 28);
        newPiece[12] = new Pawn(color, new Position("e7"), 29);
        newPiece[13] = new Pawn(color, new Position("f7"), 30);
        newPiece[14] = new Pawn(color, new Position("g7"), 31);
        newPiece[15] = new Pawn(color, new Position("h7"), 32);

        return newPiece;
    }


    public Game() {
        pieces = new Piece[2][16];
    }

    public Piece getPieceByID(int id) {
        if(id > 16) {
            id -= 17;
            return pieces[1][id];
        } else {
            id -= 1;
            return pieces[0][id];
        }

    }

    private String getSymbolOfPiece(int id) {
        if (id == 0) {
            return " ";
        } else {
            return getPieceByID(id).getSymbol();
        }
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
        sb.append("   A   B   C   D   E   F   G   H\n");
        return sb.toString();
    }
}
