package com.carlsenbot.position;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position(String a) {
        setY(Character.toLowerCase(a.charAt(0)) - 97);
        setX(8 - Character.getNumericValue(a.charAt(1)));
    }
}
