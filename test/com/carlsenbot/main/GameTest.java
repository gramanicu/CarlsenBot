package com.carlsenbot.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void initializeGame() {
        Game g = new Game();
        g.initializeGame();
        String correctInitial = " 19 21 23 18 17 24 22 20\n" +
                                " 25 26 27 28 29 30 31 32\n" +
                                "  0  0  0  0  0  0  0  0\n" +
                                "  0  0  0  0  0  0  0  0\n" +
                                "  0  0  0  0  0  0  0  0\n" +
                                "  0  0  0  0  0  0  0  0\n" +
                                "  9 10 11 12 13 14 15 16\n" +
                                "  3  5  7  2  1  8  6  4\n";
        assertEquals(g.getTable().toString(), correctInitial, "Some pieces are not in the correct position");
    }
}