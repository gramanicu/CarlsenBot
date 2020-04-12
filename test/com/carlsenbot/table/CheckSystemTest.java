/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.table;

import com.carlsenbot.pieces.*;
import com.carlsenbot.position.Move;
import com.carlsenbot.position.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckSystemTest {
    Table table;

    @Test
    void kingIsInCheck() {
        Table table = new Table();
        King king = new King(PieceColor.White, new Position("e1"));
        Pawn pawn1 = new Pawn(PieceColor.White, new Position("d2"));
        Pawn pawn2 = new Pawn(PieceColor.White, new Position("e2"));
        Rook rook = new Rook(PieceColor.White, new Position("f2"));
        table.addPiece(king);
        table.addPiece(pawn1);
        table.addPiece(pawn2);
        table.addPiece(rook);

        Queen eQueen = new Queen(PieceColor.Black, new Position("h1"));
        table.addPiece(eQueen);

        assertTrue(table.getCheckSystem().kingIsInCheck(PieceColor.White), "The king should be in check.");

        ArrayList<Move> possibleMoves = table.getAllPossibleMoves(PieceColor.White);
        assertEquals(possibleMoves.size(), 1, "The rook should be moved to eliminate check.");
    }
}