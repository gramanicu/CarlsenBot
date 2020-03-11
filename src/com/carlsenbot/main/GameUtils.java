/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

import com.carlsenbot.pieces.*;

/**
 * This is a class that contains things like:
 * - the default layout of a chess table
 */
public class GameUtils {
    /**
     * Set the white pieces to the normal, initial position
     * @return The array of the pieces
     */
    public static Piece[] initWhitePieces(){
        PieceColor color = PieceColor.White;
        Piece[] newPiece = new Piece[16];

        // Main pieces
        newPiece[0] = new King(color, "e1");
        newPiece[1] = new Queen(color, "d1");
        newPiece[2] = new Rook(color, "a1");
        newPiece[3] = new Rook(color, "h1");
        newPiece[4] = new Knight(color, "b1");
        newPiece[5] = new Knight(color, "g1");
        newPiece[6] = new Bishop(color, "c1");
        newPiece[7] = new Bishop(color, "f1");

        // Pawns
        newPiece[8] = new Pawn(color, "a2");
        newPiece[9] = new Pawn(color, "b2");
        newPiece[10] = new Pawn(color, "c2");
        newPiece[11] = new Pawn(color, "d2");
        newPiece[12] = new Pawn(color, "e2");
        newPiece[13] = new Pawn(color, "f2");
        newPiece[14] = new Pawn(color, "g2");
        newPiece[15] = new Pawn(color, "h2");

        return newPiece;
    }

    /**
     * Set the black pieces to the normal, initial position
     * @return The array of the pieces
     */
    public static Piece[] initBlackPieces(){
        PieceColor color = PieceColor.Black;
        Piece[] newPiece = new Piece[16];

        // Main pieces
        newPiece[0] = new King(color, "e8");
        newPiece[1] = new Queen(color, "d8");
        newPiece[2] = new Rook(color, "a8");
        newPiece[3] = new Rook(color, "h8");
        newPiece[4] = new Knight(color, "b8");
        newPiece[5] = new Knight(color, "g8");
        newPiece[6] = new Bishop(color, "c8");
        newPiece[7] = new Bishop(color, "f8");

        // Pawns
        newPiece[8] = new Pawn(color, "a7");
        newPiece[9] = new Pawn(color, "b7");
        newPiece[10] = new Pawn(color, "c7");
        newPiece[11] = new Pawn(color, "d7");
        newPiece[12] = new Pawn(color, "e7");
        newPiece[13] = new Pawn(color, "f7");
        newPiece[14] = new Pawn(color, "g7");
        newPiece[15] = new Pawn(color, "h7");

        return newPiece;
    }
}
