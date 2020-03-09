/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.position;

import com.carlsenbot.main.GameManager;

public class Position {
    private int x;
    private int y;

    // Constructors
    public Position() { setX(0); setY(0); }

    /**
     * Create a position object, with the specified x and y coordinates
     * @param x The x coordinate (column)
     * @param y The y coordinate (row)
     */
    public Position(int x, int y) { setX(x); setY(y); }

    /**
     * Create a position object, with the specified "chess coordinates"
     * @param coordinates The chess coordinates
     */
    public Position(String coordinates) { setCoordinates(coordinates); }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getRow() {
        return y;
    }
    public int getCol() { return x; }

    // Setters
    public void setX(int x) {
        this.x = Math.min(Byte.MAX_VALUE, Math.max(x, 0));
    }
    public void setY(int y) {
        this.y = Math.min(Byte.MAX_VALUE, Math.max(y, 0));
    }

    /**
     * Check the validity of the coordinates
     * @param coordinates The string with the "chess coordinates"
     * @return Whether or not the coordinates are valid
     */
    private boolean validCoordinates(String coordinates) {
        // Too many or too less coordinates
        if (coordinates.length() != 2) {
            return false;
        }

        char first = coordinates.charAt(0);
        char second = coordinates.charAt(1);

        if (Character.isDigit(first)) {
            if (Character.isDigit(second)) {
                // Both are numbers
                return false;
            }

            // Reverse chars because they are reversed
            char temp = first;
            first = second;
            second = temp;
        }

        if (Character.isDigit(first)) {
            // Both are characters
            return false;
        }

        first = Character.toLowerCase(first);
        if (first < 'a' || first > 'h') {
            // The character "value" is out of the range
            return false;
        }

        int value = Character.getNumericValue(second);

        // Check if the row value is out of the range
        return value >= 1 && value <= 8;
    }

    /**
     * Compute the difference of rows
     * @param p1 First position
     * @param p2 Second position
     * @return The difference
     */
    public static double getDiffRow(Position p1, Position p2) {
        return Math.abs(p2.y - p1.y);
    }

    /**
     * Compute the difference of columns
     * @param p1 First position
     * @param p2 Second position
     * @return The difference
     */
    public static double getDiffCol(Position p1, Position p2) {
        return Math.abs(p2.x - p1.x);
    }

    /**
     * Compute the distance using the Distance Formula
     * @param p1 First position
     * @param p2 Second position
     * @return The distance
     */
    public static double getDistance(Position p1, Position p2) {
        return Math.sqrt((Math.pow(getDiffRow(p1, p2), 2)
                        + Math.pow(getDiffCol(p1, p2), 2)));
    }

    /**
     * Compute the difference of rows
     * @param other The reference position
     * @return The difference
     */
    public double getDiffRow(Position other) { return getDiffRow(this, other); }

    /**
     * Compute the difference of columns
     * @param other The reference position
     * @return The difference
     */
    public double getDiffCol(Position other) { return getDiffCol(this, other); }

    /**
     * Compute the distance using the Distance Formula
     * @param other The reference position
     * @return The distance
     */
    public double getDistance(Position other) { return getDistance(this, other); }

    /**
     * Check in the GameManager table if the position is free
     * @return If the position is occupied
     */
    public boolean isEmpty() {
        return GameManager.getInstance().getTable().isEmptyCell(this);
    }

    /**
     * Set coordinates of the position, based on the "chess coordinate"
     * @param coordinates The string of coordinates [A-H][1-8]
     */
    public void setCoordinates(String coordinates) {
        if (validCoordinates(coordinates)) {
            // If the first char is the character
            if (Character.isDigit(coordinates.charAt(1))) {
                setX(Character.toLowerCase(coordinates.charAt(0)) - 97);
                setY(8 - Character.getNumericValue(coordinates.charAt(1)));
            } else {
                setX(Character.toLowerCase(coordinates.charAt(1)) - 97);
                setY(8 - Character.getNumericValue(coordinates.charAt(0)));
            }
        } else {
            setX(0);
            setY(0);
        }
    }

    /**
     * Return a string with the "chess coordinates"
     * @return The coordinates
     */
    public String toString() {
        String first = Character.toString((char) (x + 97));
        String second = Integer.toString(8 - y);
        return first + second;
    }


}