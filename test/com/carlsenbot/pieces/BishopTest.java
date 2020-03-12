/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    Bishop b1 = new Bishop(PieceColor.White, "a2");
    Bishop b2 = new Bishop(PieceColor.White, "a3");
    Bishop b3 = new Bishop(PieceColor.White, "a4");
    Bishop b4 = new Bishop(PieceColor.Black, "b3");
    Bishop b5 = new Bishop(PieceColor.Black, "b4");
    Bishop b6 = new Bishop(PieceColor.White, "h1");
    Pawn p1 = new Pawn(PieceColor.White,"g2");
    Pawn p2 = new Pawn(PieceColor.Black, "e4");
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

        assertFalse(b1.isValidMove(new Position("a3")).canMove, "The bishop should not be able to move");
        assertFalse(b1.isValidMove(new Position("a4")).canMove, "The bishop should not be able to move");
        assertFalse(b2.isValidMove(new Position("a4")).canMove, "The bishop should not be able to move");

        assertFalse(b3.isValidMove(new Position("a5")).canMove, "The bishop should not be able to move there");
        assertFalse(b3.isValidMove(new Position("a6")).canMove, "The bishop should not be able to move there");

        assertTrue(b6.isValidMove(new Position("a8")).canMove, "The bishop should pass the whole board on diagonal");
        table.addPiece(p1);
        assertFalse(b6.isValidMove(new Position("a8")).canMove, "The bishop should not pass the whole board on diagonal.");
        table.removePiece(p1);
        table.addPiece(p2);
        assertFalse(b6.isValidMove(new Position("a8")).canMove, "The bishop should not pass the board on diagonal.");
        table.removePiece(p2);
        assertFalse(b6.isValidMove(new Position ("a7")).canMove, "A7 isn't on bishop's diagonal.");


    }

    @Test
    void move() {
    }
}