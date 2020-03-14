/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;
import org.junit.jupiter.api.Test;

import java.io.PipedOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    Queen q1 = new Queen(PieceColor.White, "d4");

    Pawn p1 = new Pawn(PieceColor.White, "g7");
    Pawn p2 = new Pawn(PieceColor.White, "a4");
    Pawn p3 = new Pawn(PieceColor.White, "d2");
    Pawn p4 = new Pawn(PieceColor.White, "g4");

    Pawn p5 = new Pawn(PieceColor.Black, "h8");
    Pawn p6 = new Pawn(PieceColor.Black, "h4");

    Pawn p7 = new Pawn(PieceColor.Black, "a7");
    Pawn p8 = new Pawn(PieceColor.Black, "d8");
    Pawn p9 = new Pawn(PieceColor.Black, "c4");
    Pawn p10 = new Pawn(PieceColor.Black, "g1");

    Table table;
    @Test
    void isValidMove() {
        table = new Table();
        table.addPiece(q1);
        table.addPiece(p1);
        table.addPiece(p2);
        table.addPiece(p3);
        table.addPiece(p4);
        table.addPiece(p5);
        table.addPiece(p6);
        table.addPiece(p7);
        table.addPiece(p8);
        table.addPiece(p9);
        table.addPiece(p10);

        assertFalse(q1.isValidMove(new Position("h8")).attacking, "There are other pieces on the trajectory.");
        assertFalse(q1.isValidMove(new Position("b4")).canMove, "There are other pieces on the trajectory.");
        assertFalse(q1.isValidMove(new Position("c6")).canMove, "The queen shouldn't be able to move to that position.");
        assertFalse(q1.isValidMove(new Position("c2")).canMove, "The queen shouldn't be able to move to that position.");
        assertFalse(q1.isValidMove(new Position("d1")).canMove, "There are other pieces on the trajectory.");
        assertFalse(q1.isValidMove(new Position("h4")).canMove, "There are other pieces on the trajectory.");
        assertFalse(q1.isValidMove(new Position("d1")).canMove, "There are other pieces on the trajectory.");

        assertTrue(q1.isValidMove(new Position("d6")).canMove, "The queen should be able to move");
        assertTrue(q1.isValidMove(new Position("c5")).canMove, "The queen should be able to move");
        assertTrue(q1.isValidMove(new Position("c4")).attacking, "The queen should be able to move and capture");
        assertTrue(q1.isValidMove(new Position("c3")).canMove, "The queen should be able to move");
        assertTrue(q1.isValidMove(new Position("d3")).canMove, "The queen should be able to move");
        assertTrue(q1.isValidMove(new Position("f2")).canMove, "The queen should be able to move");
        assertTrue(q1.isValidMove(new Position("g1")).attacking, "The queen should be able to move and capture");
        assertTrue(q1.isValidMove(new Position("f2")).canMove, "The queen should be able to move");
        assertTrue(q1.isValidMove(new Position("f6")).canMove, "The queen should be able to move");
        assertTrue(q1.isValidMove(new Position("d8")).attacking, "The queen should be able to move and capture.");
    }

    @Test
    void move() {
    }
}