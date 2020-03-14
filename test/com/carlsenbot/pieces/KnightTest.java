/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    Knight k1 = new Knight(PieceColor.White, "d4");

    Knight k2 = new Knight(PieceColor.White, "a1");
    Knight k3 = new Knight(PieceColor.Black, "c2");
    Knight k4 = new Knight(PieceColor.White, "b3");

    Knight k5 = new Knight(PieceColor.Black, "e6");
    Table table;


    @Test
    void isValidMove() {
        table = new Table();
        table.addPiece(k1);
        table.addPiece(k2);
        table.addPiece(k3);
        table.addPiece(k4);
        table.addPiece(k5);


        assertFalse(k1.isValidMove(new Position("d5")).canMove, "The knight should not be able to move.");
        assertFalse(k1.isValidMove(new Position("c5")).canMove, "The knight should not be able to move.");
        assertFalse(k1.isValidMove(new Position("c4")).canMove, "The knight should not be able to move.");
        assertFalse(k1.isValidMove(new Position("c3")).canMove, "The knight should not be able to move.");
        assertFalse(k1.isValidMove(new Position("d3")).canMove, "The knight should not be able to move.");
        assertFalse(k1.isValidMove(new Position("e3")).canMove, "The knight should not be able to move.");
        assertFalse(k1.isValidMove(new Position("e4")).canMove, "The knight should not be able to move.");
        assertFalse(k1.isValidMove(new Position("e5")).canMove, "The knight should not be able to move.");


        assertFalse(k1.isValidMove(new Position("d6")).canMove, "The knight should not be able to move.");
        assertFalse(k1.isValidMove(new Position("b4")).canMove, "The knight should not be able to move.");
        assertFalse(k1.isValidMove(new Position("d2")).canMove, "The knight should not be able to move.");
        assertFalse(k1.isValidMove(new Position("f4")).canMove, "The knight should not be able to move.");

        assertTrue(k1.isValidMove(new Position("c6")).canMove, "The knight should be able to move.");
        assertTrue(k1.isValidMove(new Position("b5")).canMove, "The knight should be able to move.");
        assertFalse(k1.isValidMove(new Position("b3")).canMove, "The knight should not be able to move. (same color piece)");
        assertTrue(k1.isValidMove(new Position("c2")).attacking, "The knight should be able to move and capture.");
        assertTrue(k1.isValidMove(new Position("e2")).canMove, "The knight should be able to move.");
        assertTrue(k1.isValidMove(new Position("f3")).canMove, "The knight should be able to move.");
        assertTrue(k1.isValidMove(new Position("f5")).canMove, "The knight should be able to move.");
        assertTrue(k1.isValidMove(new Position("e6")).attacking, "The knight should be able to move and capture.");

        assertFalse(k2.isValidMove(new Position("a2")).canMove, "The knight should not be able to move.");
        assertFalse(k2.isValidMove(new Position("b1")).canMove, "The knight should not be able to move.");
        assertFalse(k2.isValidMove(new Position("b2")).canMove, "The knight should not be able to move.");
        assertFalse(k2.isValidMove(new Position("a3")).canMove, "The knight should not be able to move.");
        assertFalse(k2.isValidMove(new Position("c1")).canMove, "The knight should not be able to move.");
        assertFalse(k2.isValidMove(new Position("c3")).canMove, "The knight should not be able to move.");

        assertTrue(k2.isValidMove(new Position("c2")).attacking, "The knight should be able to move and capture.");
        assertFalse(k2.isValidMove(new Position("b3")).canMove, "The knight should not be able to move. (same color piece)");

        assertTrue(k3.isValidMove(new Position("a1")).attacking, "The knight should be able to move and capture.");
        assertTrue(k3.isValidMove(new Position("d4")).attacking, "The knight should be able to move and capture.");
        assertTrue(k5.isValidMove(new Position("d4")).attacking, "The knight should be able to move and capture.");
    }

    @Test
    void move() {
        table = new Table();
        table.addPiece(k1);
        table.addPiece(k2);
        table.addPiece(k3);
        table.addPiece(k4);
        table.addPiece(k5);

        table.movePiece(k1, "d5");
        table.movePiece(k1, "d6");
        table.movePiece(k1, "b3");
        assertEquals(table.getPiece(new Position("d4")), k1,"The knight shouldn't have moved");
        table.movePiece(k1, "e6");
        assertEquals(table.getPiece(new Position("e6")), k1,"The knight should have moved and captured");
        assertFalse(k5.isOnBoard(), "The bishop should not be on the table");

        table.movePiece(k1, "d4");
        assertEquals(table.getPiece(new Position("d4")), k1,"The knight should have moved");

        table.movePiece(k2, "a3");
        table.movePiece(k2, "b3");
        assertEquals(table.getPiece(new Position("a1")), k2,"The knight shouldn't have moved");


        table.movePiece(k3, "d4");
        table.movePiece(k3, "c2");
        table.movePiece(k3, "a1");
        assertEquals(table.getPiece(new Position("a1")), k3,"The knight should have moved and captured");
        assertFalse(k1.isOnBoard(), "The bishop should not be on the table");
        assertFalse(k2.isOnBoard(), "The bishop should not be on the table");

    }
}