/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    Bishop b1 = new Bishop(PieceColor.White, "a1");
    Bishop b2 = new Bishop(PieceColor.White, "a2");
    Bishop b3 = new Bishop(PieceColor.White, "h1");
    Bishop b4 = new Bishop(PieceColor.White, "h2");

    Bishop b5 = new Bishop(PieceColor.Black, "h8");
    Bishop b6 = new Bishop(PieceColor.Black, "b8");
    Bishop b7 = new Bishop(PieceColor.White, "b7");
    Bishop b8 = new Bishop(PieceColor.White, "g8");

    Table table;

    @Test
    void isValidMove() {
        table = new Table();
        table.addPiece(b1);
        table.addPiece(b2);
        table.addPiece(b3);
        table.addPiece(b4);
        table.addPiece(b5);
        table.addPiece(b6);
        table.addPiece(b7);
        table.addPiece(b8);

        assertFalse(b1.isValidMove(new Position("a2")).canMove, "The bishop should not be able to move");
        assertFalse(b1.isValidMove(new Position("b1")).canMove, "The bishop should not be able to move");
        assertFalse(b1.isValidMove(new Position("a8")).canMove, "The bishop should not be able to move");
        assertTrue(b1.isValidMove(new Position("h8")).attacking, "The bishop should be able to move and capture");

        assertFalse(b2.isValidMove(new Position("g7")).canMove, "The bishop should not be able to move");
        assertFalse(b2.isValidMove(new Position("g8")).canMove, "The bishop should not be able to move");
        assertTrue(b2.isValidMove(new Position("f7")).canMove, "The bishop should be able to move");
        assertTrue(b2.isValidMove(new Position("b3")).canMove, "The bishop should be able to move");

        assertFalse(b3.isValidMove(new Position("a8")).canMove, "The bishop should not be able to move");
        assertFalse(b3.isValidMove(new Position("b7")).canMove, "The bishop should not be able to move");
        assertTrue(b3.isValidMove(new Position("g2")).canMove, "The bishop should be able to move");
        assertTrue(b3.isValidMove(new Position("c6")).canMove, "The bishop should be able to move");

        assertFalse(b4.isValidMove(new Position("h1")).canMove, "The bishop should not be able to move");
        assertFalse(b4.isValidMove(new Position("h3")).canMove, "The bishop should not be able to move");
        assertFalse(b4.isValidMove(new Position("a8")).canMove, "The bishop should not be able to move");
        assertTrue(b4.isValidMove(new Position("b8")).attacking, "The bishop should be able to move and capture");

        assertFalse(b5.isValidMove(new Position("g8")).attacking, "The bishop should not be able to move (and especially capture)");
        assertFalse(b5.isValidMove(new Position("h7")).canMove, "The bishop should not be able to move");
        assertTrue(b5.isValidMove(new Position("g7")).canMove, "The bishop should be able to move");
        assertTrue(b5.isValidMove(new Position("a1")).attacking, "The bishop should be able to move and capture");

        assertFalse(b6.isValidMove(new Position("a8")).canMove, "The bishop should not be able to move");
        assertFalse(b6.isValidMove(new Position("h3")).canMove, "The bishop should not be able to move");
        assertTrue(b6.isValidMove(new Position("c7")).canMove, "The bishop should be able to move");
        assertTrue(b6.isValidMove(new Position("h2")).attacking, "The bishop should be able to move and capture");
    }

    @Test
    void move() {
        table = new Table();
        table.addPiece(b1);
        table.addPiece(b2);
        table.addPiece(b3);
        table.addPiece(b4);
        table.addPiece(b5);
        table.addPiece(b6);
        table.addPiece(b7);
        table.addPiece(b8);

        table.movePiece(b1, "a2");
        table.movePiece(b1, "b1");
        table.movePiece(b1, "a8");
        assertEquals(table.getPiece(new Position("a1")), b1,"The bishop shouldn't have moved");
        table.movePiece(b1, "h8");
        assertEquals(table.getPiece(new Position("h8")), b1,"The bishop should have moved and captured");
        assertFalse(b5.isOnBoard(), "The bishop should not be on the table");

        table.movePiece(b2, "g8");
        table.movePiece(b2, "g7");
        assertEquals(table.getPiece(new Position("a2")), b2,"The bishop shouldn't have moved");
        table.movePiece(b2, "b3");
        assertEquals(table.getPiece(new Position("b3")), b2,"The bishop should have moved");

        table.movePiece(b3, "a8");
        table.movePiece(b3, "b7");
        assertEquals(table.getPiece(new Position("h1")), b3,"The bishop shouldn't have moved");
        table.movePiece(b3, "c6");
        assertEquals(table.getPiece(new Position("c6")), b3,"The bishop should have moved");

        table.movePiece(b6, "a8");
        table.movePiece(b6, "h3");
        assertEquals(table.getPiece(new Position("b8")), b6,"The bishop shouldn't have moved");
        table.movePiece(b6, "h2");
        assertEquals(table.getPiece(new Position("h2")), b6,"The bishop should have moved and captured");
        assertFalse(b4.isOnBoard(), "The bishop should not be on the table");
    }
}