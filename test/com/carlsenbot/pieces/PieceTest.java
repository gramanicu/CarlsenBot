package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    void setColor() {
        Piece piece = new Piece(0d, PieceColor.White, new Position("a1"), "test", 0) {
            @Override
            public String getSymbol() {
                return null;
            }
        };

        assertTrue(piece.isWhite(), "The piece was not initialised correctly");
        piece.setColor(PieceColor.Black);
        assertFalse(piece.isWhite(), "Either the color is not black, or the isWhite() function is wrong");
        assertTrue(piece.isBlack(), "Either the color is not black, or the isBlack() function is wrong");
        piece.setColor(PieceColor.White);
        assertTrue(piece.isWhite(), "Either the color is not black, or the isWhite() function is wrong");
        assertFalse(piece.isBlack(), "Either the color is not black, or the isBlack() function is wrong");
    }
}