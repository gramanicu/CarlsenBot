package com.carlsenbot.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void initializeGame() {
        Game g = Game.getInstance();
        g.initializeGame();
        String correctInitial = " -3 -5 -7 -2 -1 -8 -6 -4\n" +
                                " -9-10-11-12-13-14-15-16\n" +
                                "  0  0  0  0  0  0  0  0\n" +
                                "  0  0  0  0  0  0  0  0\n" +
                                "  0  0  0  0  0  0  0  0\n" +
                                "  0  0  0  0  0  0  0  0\n" +
                                "  9 10 11 12 13 14 15 16\n" +
                                "  3  5  7  2  1  8  6  4\n";
        assertEquals(g.getTable().toString(), correctInitial, "Some pieces are not in the correct position");
    }
}