/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.table;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.pieces.Pawn;
import com.carlsenbot.pieces.Piece;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.position.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TableTest {
    static class TestPiece extends Piece {
        public TestPiece(double value, PieceColor color, Position position, String name) {
            super(value, color, position, name);
        }

        @Override
        protected MoveInfo isValidMove(Position target) {
            return null;
        }

        @Override
        public String getSymbol() {
            return null;
        }

        @Override
        public MoveInfo move(Position target) {
            return null;
        }
    }

    Piece p1 = new TestPiece(0d, PieceColor.White, new Position("d5"), "p1");
    Piece p2 = new TestPiece(0d, PieceColor.White, new Position("a5"), "p2");
    Piece p3 = new TestPiece(0d, PieceColor.Black, new Position("b5"), "p3");
    Piece p4 = new TestPiece(0d, PieceColor.Black, new Position("e5"), "p4");

    @Test
    void isSameColor() {
        Table table = new Table();

        table.addPiece(p1);
        table.addPiece(p2);
        table.addPiece(p3);
        table.addPiece(p4);

        assertTrue(table.isSameColor(p1, p2), "The pieces should have the same color");
        assertFalse(table.isSameColor(p1, p3), "The pieces should not have the same color");
        assertFalse(table.isSameColor(p1, p4), "The pieces should not have the same color");
        assertFalse(table.isSameColor(p3, p1), "The pieces should not have the same color");
        assertFalse(table.isSameColor(p3, p2), "The pieces should not have the same color");
        assertTrue(table.isSameColor(p3, p4), "The pieces should have the same color");
    }

    @Test
    void movementAndEmptyCells() {
        Table table = new Table();
        Piece piece = new Pawn(PieceColor.White, "a1");
        table.addPiece(piece);
        assertTrue(table.isEmptyCell("a2"), "The cell should be empty");
        assertFalse(table.isEmptyCell("a1"), "The cell should not be empty");
        table.movePiece(piece, "a2");
        assertFalse(table.isEmptyCell("a2"), "The cell should not be empty");
        assertTrue(table.isEmptyCell("a1"), "The cell should be empty");
    }
}