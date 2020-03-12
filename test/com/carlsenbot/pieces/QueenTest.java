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
    Queen q1 = new Queen(PieceColor.White, "a2");
    Queen q2 = new Queen(PieceColor.Black, "a3");
    Table table;
    @Test
    void isValidMove() {
        table = new Table();
        table.addPiece(q1);
        table.addPiece(q2);

        assertFalse(q1.isValidMove(new Position("a8")).canMove, "There are other pieces on the trajectory.");
        assertTrue(q1.isValidMove(new Position("g8")).canMove, "Queen moves on diagonal.");
    }

    @Test
    void move() {
    }
}