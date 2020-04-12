/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.table;

import com.carlsenbot.pieces.King;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.position.Position;
import org.junit.jupiter.api.Test;

class CheckSystemTest {
    Table table;
    @Test
    void kingIsInCheck() {
        Table table = new Table;
        King king = new King(PieceColor.White, new Position("e1"));
    }
}