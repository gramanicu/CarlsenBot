/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    Pawn p1 = new Pawn(PieceColor.White, "a2");
    Pawn p2 = new Pawn(PieceColor.White, "a3");
    Pawn p3 = new Pawn(PieceColor.White, "a4");
    Pawn p4 = new Pawn(PieceColor.Black, "b3");
    Pawn p5 = new Pawn(PieceColor.Black, "b4");
    Table table;

    @Test
    void isValidMove() {
        table = new Table();
        table.addPiece(p1);
        table.addPiece(p2);
        table.addPiece(p3);
        table.addPiece(p4);
        table.addPiece(p5);

        assertFalse(p1.isValidMove(new Position("a3")).canMove, "The pawn should not be able to move");
        assertFalse(p1.isValidMove(new Position("a4")).canMove, "The pawn should not be able to move");
        assertFalse(p2.isValidMove(new Position("a4")).canMove, "The pawn should not be able to move");

        assertTrue(p3.isValidMove(new Position("a5")).canMove, "The pawn should be able to move");
        assertTrue(p3.isValidMove(new Position("a6")).canMove, "The pawn should be able to move");
        assertTrue(p1.isValidMove(new Position("b3")).canMove, "The pawn should be able to move and capture");

        assertFalse(p1.isValidMove(new Position("b4")).canMove, "The pawn should not be able to move");
        assertFalse(p5.isValidMove(new Position("b3")).canMove, "The pawn should not be able to move");
        assertFalse(p5.isValidMove(new Position("b2")).canMove, "The pawn should not be able to move");

        assertTrue(p4.isValidMove(new Position("b2")).canMove, "The pawn should be able to move");
        assertTrue(p4.isValidMove(new Position("b1")).canMove, "The pawn should be able to move");
    }

    @Test
    void move() {
        table = new Table();
        table.addPiece(p1);
        table.addPiece(p2);
        table.addPiece(p3);
        table.addPiece(p4);
        table.addPiece(p5);

        // To move the pieces, we use the table. Two pieces can have
        // the same position on different tables (or non-existent ones).
        // However, they must have different positions on a table

        table.movePiece(p1, "a3");
        assertEquals(table.getPiece(new Position("a2")), p1,"The pawn shouldn't have moved");
        table.movePiece(p1, "a4");
        table.movePiece(p2, "a4");
        assertEquals(table.getPiece(new Position("a2")), p1,"The pawn shouldn't have moved");
        assertEquals(table.getPiece(new Position("a3")), p2,"The pawn shouldn't have moved");

        table.movePiece(p3, "a6");
        table.movePiece(p1, "b3");
        assertEquals(table.getPiece(new Position("a6")), p3,"The pawn should have moved");
        assertEquals(table.getPiece(new Position("b3")), p1,"The pawn should have moved and captured");
        table.movePiece(p1, "a4");
        assertEquals(table.getPiece(new Position("b3")), p1,"The pawn shouldn't have moved");

        table.movePiece(p5, "a3");
        assertEquals(table.getPiece(new Position("a3")), p5,"The pawn should have moved");
        table.movePiece(p5, "a2");
        assertEquals(table.getPiece(new Position("a2")), p5,"The pawn should have moved");

        table.movePiece(p4, "b1");
        table.movePiece(p5, "b1");
        assertFalse(p4.isOnBoard(), "The pawn should be on the table");
        assertEquals(table.getPiece(new Position("a2")), p5,"The pawn shouldn't have moved");
        table.movePiece(p5, "a1");
        assertTrue(table.getPiece(new Position("a1")) instanceof Queen,"The pawn should have moved and promoted");
    }

    @Test
    void enPassant() {
        Pawn pawnW = new Pawn(PieceColor.White, "e5");
        Pawn pawnB = new Pawn(PieceColor.Black, "f7");
        table = new Table();
        table.addPiece(pawnW);
        table.addPiece(pawnB);

        assertFalse(pawnW.isValidMove(new Position("f6")).canMove, "No pawn to be taken");
        table.movePiece(pawnB, "f5");
        assertTrue(pawnW.isValidMove(new Position("f6")).canMove, "En passant is possible");
        table.movePiece(pawnW, "f6");

        assertEquals(table.getPiece(new Position("f6")), pawnW, "The pawn should have moved");
        assertFalse(pawnB.isOnBoard(), "This pawn should have been captured");

        // Same tests, other color
        pawnW = new Pawn(PieceColor.White, "f2");
        pawnB = new Pawn(PieceColor.Black, "e4");
        table = new Table();
        table.addPiece(pawnW);
        table.addPiece(pawnB);

        assertFalse(pawnB.isValidMove(new Position("f3")).canMove, "No pawn to be taken");
        table.movePiece(pawnW, "f4");
        assertTrue(pawnB.isValidMove(new Position("f3")).canMove, "En passant is possible");
        table.movePiece(pawnB, "f3");

        assertEquals(table.getPiece(new Position("f3")), pawnB, "The pawn should have moved");
        assertFalse(pawnW.isOnBoard(), "This pawn should have been captured");
    }

    @Test
    void promotion() {
        Piece ptoQueen = new Pawn(PieceColor.White, "e7");
        table = new Table();
        table.addPiece(ptoQueen);

        table.movePiece(ptoQueen, "e8");
        ptoQueen = table.getPiece(new Position("e8"));
        assertTrue(ptoQueen instanceof Queen, "Should have promoted.");

        assertTrue(ptoQueen.isValidMove(new Position("a4")).canMove, "Should have move like a Queen.");
        assertTrue(ptoQueen.isValidMove(new Position("h5")).canMove, "Should have move like a Queen.");
        assertTrue(ptoQueen.isValidMove(new Position("a8")).canMove, "Should have move like a Queen.");
        assertTrue(ptoQueen.isValidMove(new Position("e1")).canMove, "Should have move like a Queen.");
        assertTrue(ptoQueen.isValidMove(new Position("h8")).canMove, "Should have move like a Queen.");
        assertFalse(ptoQueen.isValidMove(new Position("h6")).canMove, "That move is impossible even for a Queen.");
        assertFalse(ptoQueen.isValidMove(new Position("h7")).canMove, "That move is impossible even for a Queen.");
    }

}