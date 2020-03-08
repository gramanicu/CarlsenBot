package com.carlsenbot.position;

public class Position {
    private int x;
    private int y;

    public Position() {
        setX(0);
        setY(0);
    }

    public Position(int x, int y) {
        setX(x);
        setY(y);
    }

    public Position(String coordinates) {
        setCoordinates(coordinates);
    }

    public Position(String a, boolean valid) {
        if (valid) {
            setY(Character.toLowerCase(a.charAt(0)) - 97);
            setX(8 - Character.getNumericValue(a.charAt(1)));
        }
    }

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = Math.min(Byte.MAX_VALUE, Math.max(x, 0));
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = Math.min(Byte.MAX_VALUE, Math.max(y, 0));
    }

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

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}