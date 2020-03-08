package com.carlsenbot.position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {
    @Test
    void setX() {
        Position pos = new Position();
        pos.setX(0);
        assertEquals( 0, pos.getX());
        pos.setX(128);
        assertEquals( 127, pos.getX(), "Should not accept values over 127");
        pos.setX(-1);
        assertEquals( 0, pos.getX(), "Should not accept negative values");
    }

    @Test
    void setY() {
        Position pos = new Position();
        pos.setY(0);
        assertEquals( 0, pos.getY());
        pos.setY(127);
        assertEquals( 127, pos.getY(), "Should not accept values over 127");
        pos.setY(-1);
        assertEquals( 0, pos.getY(), "Should not accept negative values");
    }

    @Test
    void setCoordinates() {
        Position pos = new Position();

        pos.setCoordinates("a1");
        assertEquals( 0, pos.getX(), "Not correct x value for lowercase");
        assertEquals( 7, pos.getY(), "Not correct y value for lowercase");

        pos.setCoordinates("A1");
        assertEquals( 0, pos.getX(), "Not correct x value for uppercase");
        assertEquals( 7, pos.getY(), "Not correct y value for uppercase");

        pos.setCoordinates("1a");
        assertEquals( 0, pos.getX(), "Not correct x value for reverse lowercase");
        assertEquals( 7, pos.getY(), "Not correct y value for reverse lowercase");

        pos.setCoordinates("1A");
        assertEquals( 0, pos.getX(), "Not correct x value for reverse uppercase");
        assertEquals( 7, pos.getY(), "Not correct y value for reverse uppercase");

        pos.setCoordinates("I1");
        assertEquals( 0, pos.getX(), "X value isn't bound to a upper limit");

        pos.setCoordinates("A9");
        assertEquals( 0, pos.getY(), "Y value isn't bound to a upper limit");

        pos.setCoordinates("A0");
        assertEquals( 0, pos.getY(), "Y value isn't bound to a lower limit");

        pos.setCoordinates("1I");
        assertEquals( 0, pos.getX(), "X value isn't bound to a upper limit (reverse)");

        pos.setCoordinates("9A");
        assertEquals( 0, pos.getY(), "Y value isn't bound to a upper limit (reverse)");

        pos.setCoordinates("0A");
        assertEquals( 0, pos.getY(), "Y value isn't bound to a lower limit (reverse)");

        pos.setCoordinates("");
        assertEquals( 0, pos.getX(), "Not correct x value for empty coordinates");
        assertEquals( 0, pos.getY(), "Not correct y value for empty coordinates");

        pos.setCoordinates("A");
        assertEquals( 0, pos.getX(), "Not correct x value for invalid coordinates (less)");
        assertEquals( 0, pos.getY(), "Not correct y value for invalid coordinates (less)");

        pos.setCoordinates("something");
        assertEquals( 0, pos.getX(), "Not correct x value for invalid coordinates");
        assertEquals( 0, pos.getY(), "Not correct y value for invalid coordinates");
    }
}