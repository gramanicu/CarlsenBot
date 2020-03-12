/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    Rook r1 = new Rook(PieceColor.White, "a2");
    Rook r2 = new Rook(PieceColor.White, "a3");
    Rook r3 = new Rook(PieceColor.White, "a4");
    Rook r4 = new Rook(PieceColor.Black, "b2");
    Table table;
    @Test
    void isValidMove() {
        table = new Table();
        table.addPiece(r1);
        table.addPiece(r2);
        table.addPiece(r3);
        table.addPiece(r4);

        assertFalse(r1.isValidMove(new Position("a8")).canMove, "There are other pieces on the trajectory.");
        assertTrue(r1.isValidMove(new Position("b2")).canMove, "R1 rook captures the piece, so it moves.");
        assertFalse(r1.isValidMove(new Position("g8")).canMove, "Rooks don't go on diagonal.");
        assertTrue(r1.isValidMove(new Position("a1")).canMove, "Rook can move there");
    }

    @Test
    void move() {
    }
}