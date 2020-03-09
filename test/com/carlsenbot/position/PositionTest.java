/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

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

    Position p1 = new Position("d5");
    Position p2 = new Position("d5");
    Position p3 = new Position("d6");
    Position p4 = new Position("d4");
    Position p5 = new Position("c5");
    Position p6 = new Position("e5");
    Position p7 = new Position("e6");
    Position p8 = new Position("b3");

    @Test
    void getDiffRow() {
        assertEquals(Position.getDiffRow(p1, p2), 0, "Should be on the same row");
        assertEquals(Position.getDiffRow(p1, p3), 1, "Should be 1 rows difference");
        assertEquals(Position.getDiffRow(p1, p4), 1, "Should be 1 rows difference");
        assertEquals(Position.getDiffRow(p1, p5), 0, "Should be on the same row");
        assertEquals(Position.getDiffRow(p1, p6), 0, "Should be on the same row");
        assertEquals(Position.getDiffRow(p1, p7), 1, "Should be 1 rows difference");
        assertEquals(Position.getDiffRow(p1, p8), 2, "Should be 2 rows difference");
    }

    @Test
    void getDiffCol() {
        assertEquals(Position.getDiffCol(p1, p2), 0, "Should be on the same column");
        assertEquals(Position.getDiffCol(p1, p3), 0, "Should be on the same column");
        assertEquals(Position.getDiffCol(p1, p4), 0, "Should be on the same column");
        assertEquals(Position.getDiffCol(p1, p5), 1, "Should be 1 columns difference");
        assertEquals(Position.getDiffCol(p1, p6), 1, "Should be 1 columns difference");
        assertEquals(Position.getDiffCol(p1, p7), 1, "Should be 1 columns difference");
        assertEquals(Position.getDiffCol(p1, p8), 2, "Should be 2 columns difference");
    }

    @Test
    void getDistance() {
        assertEquals(Position.getDistance(p1, p2), 0, "Should be the same position");
        assertEquals(Position.getDistance(p1, p3), 1, "Should be a distance of 1");
        assertEquals(Position.getDistance(p1, p4), 1, "Should be a distance of 1");
        assertEquals(Position.getDistance(p1, p5), 1, "Should be a distance of 1");
        assertEquals(Position.getDistance(p1, p6), 1, "Should be a distance of 1");
        assertEquals(Position.getDistance(p1, p7), Math.sqrt(2), "Should be a distance of sqrt(2)");
        assertEquals(Position.getDistance(p1, p8), Math.sqrt(8), "Should be a distance of sqrt(8)");
    }
}