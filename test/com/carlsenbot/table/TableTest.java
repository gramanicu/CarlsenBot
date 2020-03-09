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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TableTest {

    @Test
    void movementAndEmptyCells() {
        Table table = new Table();
        Piece piece = new Pawn(PieceColor.White, "a1", 16);
        GameManager.getInstance().setTable(table);
        table.addPiece(piece);
        assertTrue(table.isEmptyCell("a2"), "The cell should be empty");
        assertFalse(table.isEmptyCell("a1"), "The cell should not be empty");
        piece.move("a2");
        assertFalse(table.isEmptyCell("a2"), "The cell should not be empty");
        assertTrue(table.isEmptyCell("a1"), "The cell should be empty");
    }
}