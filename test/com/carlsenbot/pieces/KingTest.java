/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KingTest {

    King k1 = new King(PieceColor.White, "b2");

    Pawn p1 = new Pawn(PieceColor.Black, "b1");
    Pawn p2 = new Pawn(PieceColor.Black, "a1");
    Pawn p3 = new Pawn(PieceColor.Black, "c1");
    Pawn p4 = new Pawn(PieceColor.Black, "d2");

    Pawn p5 = new Pawn(PieceColor.White, "a3");
    Pawn p6 = new Pawn(PieceColor.White, "b3");

    Table table;

    @Test
    void isValidMove() {
        table = new Table();
        table.addPiece(k1);
        table.addPiece(p1);
        table.addPiece(p2);
        table.addPiece(p3);
        table.addPiece(p4);
        table.addPiece(p5);
        table.addPiece(p6);

        assertFalse(k1.isValidMove(new Position("b3")).canMove, "The king should not be able to move.");
        assertFalse(k1.isValidMove(new Position("a3")).canMove, "The king should not be able to move.");
        assertFalse(k1.isValidMove(new Position("d2")).attacking, "The king should not be able to move. (and especially capture)");

        assertTrue(k1.isValidMove(new Position("a2")).canMove, "The king should be able to move.");
        assertTrue(k1.isValidMove(new Position("a1")).attacking, "The king should be able to move and capture.");
        assertTrue(k1.isValidMove(new Position("b1")).attacking, "The king should be able to move and capture.");
        assertTrue(k1.isValidMove(new Position("c1")).attacking, "The king should be able to move and capture.");
        assertTrue(k1.isValidMove(new Position("c2")).canMove, "The king should be able to move.");
        assertTrue(k1.isValidMove(new Position("c3")).canMove, "The king should be able to move.");
    }

    @Test
    void move() {
        table = new Table();
        table.addPiece(k1);
        table.addPiece(p1);
        table.addPiece(p2);
        table.addPiece(p3);
        table.addPiece(p4);
        table.addPiece(p5);
        table.addPiece(p6);

        table.movePiece(k1, "b3");
        table.movePiece(k1, "a3");
        table.movePiece(k1, "d2");
        assertEquals(table.getPiece(new Position("b2")), k1,"The king shouldn't have moved.");

        table.movePiece(k1, "a2");
        table.movePiece(k1, "a1");
        table.movePiece(k1, "b1");
        table.movePiece(k1, "c1");
        table.movePiece(k1, "c2");
        table.movePiece(k1, "c3");
        table.movePiece(k1, "c4");
        table.movePiece(k1, "c5");
        assertEquals(table.getPiece(new Position("c5")), k1,"The king should have moved and captured pieces.");
        assertFalse(p1.isOnBoard(), "The pawn should not be on the table");
        assertFalse(p2.isOnBoard(), "The pawn should not be on the table");
        assertFalse(p3.isOnBoard(), "The pawn should not be on the table");
    }
}
