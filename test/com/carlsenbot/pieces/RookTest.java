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
    Rook r4 = new Rook(PieceColor.White, "b2");
    Rook r5 = new Rook(PieceColor.White, "c2");
    Rook r6 = new Rook(PieceColor.Black, "f2");
    Rook r7 = new Rook(PieceColor.Black, "a6");
    Rook r8 = new Rook(PieceColor.Black, "c4");
    Table table;

    @Test
    void isValidMove() {
        table = new Table();
        table.addPiece(r1);
        table.addPiece(r2);
        table.addPiece(r3);
        table.addPiece(r4);
        table.addPiece(r5);
        table.addPiece(r6);
        table.addPiece(r7);
        table.addPiece(r8);


        assertFalse(r1.isValidMove(new Position("a3")).canMove, "Position is already occupied");
        assertFalse(r1.isValidMove(new Position("a5")).canMove, "There are pieces along the trajectory");
        assertFalse(r1.isValidMove(new Position("c3")).canMove, "Cannot move diagonally");
        assertFalse(r1.isValidMove(new Position("b2")).canMove, "Position is already occupied");
        assertFalse(r1.isValidMove(new Position("d2")).canMove, "There are pieces along the trajectory");
        assertTrue(r1.isValidMove(new Position("a1")).canMove, "The rook should be able to move");


        assertFalse(r2.isValidMove(new Position("a2")).canMove, "Position is already occupied");
        assertFalse(r2.isValidMove(new Position("a4")).canMove, "Position is already occupied");

        assertFalse(r3.isValidMove(new Position("a2")).canMove, "Position is already occupied");
        assertTrue(r3.isValidMove(new Position("a5")).canMove, "The rook should be able to move");
        assertTrue(r3.isValidMove(new Position("b4")).canMove, "The rook should be able to move");
        assertTrue(r3.isValidMove(new Position("a6")).attacking, "The rook should be able to move and capture");
        assertTrue(r3.isValidMove(new Position("c4")).attacking, "The rook should be able to move and capture");

        assertFalse(r4.isValidMove(new Position("a2")).canMove, "Position is already occupied");
        assertFalse(r4.isValidMove(new Position("c2")).canMove, "Position is already occupied");

        assertTrue(r5.isValidMove(new Position("e2")).canMove, "The rook should be able to move");
        assertTrue(r5.isValidMove(new Position("f2")).attacking, "The rook should be able to move and capture");
        assertTrue(r5.isValidMove(new Position("c4")).attacking, "The rook should be able to move and capture");

        assertTrue(r8.isValidMove(new Position("a4")).attacking, "The rook should be able to move and capture");
        assertTrue(r8.isValidMove(new Position("c2")).attacking, "The rook should be able to move and capture");
        assertTrue(r8.isValidMove(new Position("b4")).canMove, "The rook should be able to move");
        assertTrue(r8.isValidMove(new Position("c3")).canMove, "The rook should be able to move");
        assertTrue(r8.isValidMove(new Position("c8")).canMove, "The rook should be able to move");
        assertTrue(r8.isValidMove(new Position("h4")).canMove, "The rook should be able to move");
    }

    @Test
    void move() {
        table = new Table();
        table.addPiece(r1);
        table.addPiece(r2);
        table.addPiece(r3);
        table.addPiece(r4);
        table.addPiece(r5);
        table.addPiece(r6);
        table.addPiece(r7);
        table.addPiece(r8);

        table.movePiece(r1, "a3");
        table.movePiece(r1, "a5");
        table.movePiece(r1, "c3");
        table.movePiece(r1, "b2");
        table.movePiece(r1, "d2");
        assertEquals(table.getPiece(new Position("a2")), r1,"The rook shouldn't have moved");
        table.movePiece(r1, "a1");
        assertEquals(table.getPiece(new Position("a1")), r1,"The rook should have moved");

        table.movePiece(r2, "a4");
        assertEquals(table.getPiece(new Position("a3")), r2,"The rook shouldn't have moved");
        table.movePiece(r2, "a2");
        assertEquals(table.getPiece(new Position("a2")), r2,"The rook should have moved");

        table.movePiece(r3, "a2");
        assertEquals(table.getPiece(new Position("a4")), r3,"The rook shouldn't have moved");
        table.movePiece(r3, "a6");
        assertEquals(table.getPiece(new Position("a6")), r3,"The rook should have moved and captured");
        assertFalse(r7.isOnBoard(), "The rook should not be on the table");

        table.movePiece(r4, "a2");
        table.movePiece(r4, "c2");
        assertEquals(table.getPiece(new Position("b2")), r4,"The rook shouldn't have moved");

        table.movePiece(r5, "f2");
        assertEquals(table.getPiece(new Position("f2")), r5,"The rook should have moved and captured");
        assertFalse(r6.isOnBoard(), "The rook should not be on the table");
    }
}