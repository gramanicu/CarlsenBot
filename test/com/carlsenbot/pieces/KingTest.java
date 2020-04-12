/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.CheckSystem;
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

    King kw = new King(PieceColor.White, "e1");
    Rook rw1 = new Rook(PieceColor.White, "a1");
    Rook rw2 = new Rook(PieceColor.White, "h1");

    King kb = new King(PieceColor.Black, "e8");
    Rook rb1 = new Rook(PieceColor.Black, "a8");
    Rook rb2 = new Rook(PieceColor.Black, "h8");

    @Test
    void castle() {
        table = new Table();
        table.addPiece(kw);
        table.addPiece(kb);
        table.addPiece(rw1);
        table.addPiece(rw2);
        table.addPiece(rb1);
        table.addPiece(rb2);

        // Check valid castling both sides
        assertTrue(kw.isValidMove(new Position("c1")).canMove, "The white king should be able to castle queen Side.");
        assertTrue(kw.isValidMove(new Position("g1")).canMove, "The white king should be able to castle king Side.");
        assertTrue(kb.isValidMove(new Position("c8")).canMove, "The black king should be able to castle queen Side.");
        assertTrue(kb.isValidMove(new Position("g8")).canMove, "The black king should be able to castle king Side.");

        // Check that castling can't happen if any of the two pieces was moved
        table.movePiece(rw1, "b1");
        table.movePiece(rw1, "a1");
        assertFalse(kb.isValidMove(new Position("b1")).canMove, "This is not a castling target position.");
        assertFalse(kb.isValidMove(new Position("d1")).canMove, "This is not a castling target position.");
        assertFalse(kw.isValidMove(new Position("c1")).canMove, "The rook was moved.");
        table.movePiece(kw, "e2");
        table.movePiece(kw, "e1");
        assertFalse(kw.isValidMove(new Position("g1")).canMove, "The king was moved.");

        // Check that pieces end the correct position
        table.movePiece(kb, "c8");
        assertEquals(table.getPiece(new Position("d8")), rb1, "The rook is not in the correct position.");

        // Reset pieces (to "check the check system")
        kw = new King(PieceColor.White, "e1");
        rw1 = new Rook(PieceColor.White, "a1");
        rw2 = new Rook(PieceColor.White, "h1");
        rb1 = new Rook(PieceColor.Black, "c8");
        rb2 = new Rook(PieceColor.Black, "f8");
        Rook rb3 = new Rook(PieceColor.Black, "e8");
        table = new Table();
        table.addPiece(kw);
        table.addPiece(rw1);
        table.addPiece(rw2);
        table.addPiece(rb1);
        table.addPiece(rb2);
        table.addPiece(rb3);

        assertFalse(kw.isValidMove(new Position("c1")).canMove, "The king is in check.");
        rb3.move("e7");
        rb3.move("a7");
        assertFalse(kw.isValidMove(new Position("c1")).canMove, "The target position is in check.");
        rb1.move("a8");
        assertTrue(kw.isValidMove(new Position("c1")).canMove, "The white king should be able to castle queen Side.");
        assertFalse(kw.isValidMove(new Position("g1")).canMove, "The king passes through check.");
        rb2.move("h8");
        assertTrue(kw.isValidMove(new Position("g1")).canMove, "The white king should be able to castle king Side.");
    }

    @Test
    void checksPosition() {
        table = new Table();
        King whiteK = new King(PieceColor.White, "a1");
        King blackK = new King(PieceColor.Black, "b7");
        table.addPiece(whiteK);
        table.addPiece(blackK);

        CheckSystem cs = table.getCheckSystem();
        assertTrue(cs.isInCheck(PieceColor.Black, new Position("a2")), "The white king should keep that position in check.");
        assertTrue(cs.isInCheck(PieceColor.Black, new Position("b2")), "The white king should keep that position in check.");
        assertTrue(cs.isInCheck(PieceColor.Black, new Position("b1")), "The white king should keep that position in check.");
        assertFalse(cs.isInCheck(PieceColor.Black, new Position("a3")), "No piece should attack that position.");
        assertFalse(cs.isInCheck(PieceColor.Black, new Position("b3")), "No piece should attack that position.");

        assertTrue(cs.isInCheck(PieceColor.White, new Position("a7")), "The black king should keep that position in check.");
        assertTrue(cs.isInCheck(PieceColor.White, new Position("a8")), "The black king should keep that position in check.");
        assertTrue(cs.isInCheck(PieceColor.White, new Position("b8")), "The black king should keep that position in check.");
        assertTrue(cs.isInCheck(PieceColor.White, new Position("c8")), "The black king should keep that position in check.");
        assertTrue(cs.isInCheck(PieceColor.White, new Position("c7")), "The black king should keep that position in check.");
        assertTrue(cs.isInCheck(PieceColor.White, new Position("c6")), "The black king should keep that position in check.");
        assertTrue(cs.isInCheck(PieceColor.White, new Position("b6")), "The black king should keep that position in check.");
        assertTrue(cs.isInCheck(PieceColor.White, new Position("a6")), "The black king should keep that position in check.");
        assertFalse(cs.isInCheck(PieceColor.White, new Position("d7")), "No piece should attack that position.");
    }
}
