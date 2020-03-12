/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class KingTest {
    King k1 = new King(PieceColor.White, "a2");
    King k2 = new King(PieceColor.Black, "a3");
    Table table;
    @Test
    void isValidMove() {
        table = new Table();
        table.addPiece(k1);
        table.addPiece(k2);

        assertFalse(k1.isValidMove(new Position("a3")).canMove, "Can't capture the king");
    }

    @Test
    void move() {
    }
}
