/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameManagerTest {

    @Test
    void initializeGame() {
        GameManager g = GameManager.getInstance();
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

        // This is used to make sure the other tests are not influenced by the game instance
        g.removeInstance();
    }
}