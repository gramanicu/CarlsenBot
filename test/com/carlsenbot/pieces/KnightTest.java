/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    Knight k1 = new Knight(PieceColor.White, "a2");
    Knight k2 = new Knight(PieceColor.White, "a3");
    Knight k3 = new Knight(PieceColor.White, "a4");
    Knight k4 = new Knight(PieceColor.Black, "b3");
    Knight k5 = new Knight(PieceColor.Black, "b4");
    Knight k6 = new Knight(PieceColor.White, "h1");
    Knight k7 = new Knight(PieceColor.White, "g3");
    Table table;


    @Test
    void isValidMove() {
        table = new Table();
        table.addPiece(k1);
        table.addPiece(k2);
        table.addPiece(k3);
        table.addPiece(k4);
        table.addPiece(k5);
        table.addPiece(k6);
        table.addPiece(k7);

        assertTrue(k1.isValidMove(new Position("c3")).canMove, "The knight should move.");
        assertTrue(k2.isValidMove(new Position("b5")).canMove, "The knight should move.");

        assertTrue(k5.isValidMove(new Position("a2")).canMove, "The position that we want to move is already ocuppied but we attack it and capture it.");
        assertFalse(k1.isValidMove(new Position("b0")).canMove, "The position isn't valid.");

        assertTrue(k1.isValidMove(new Position("b4")).canMove, "The piece on the position should be captured.");
        assertFalse(k7.isValidMove(new Position("h1")).canMove, "The piece on the position is the same color, so it won't move.");

    }

    @Test
    void move() {
    }
}