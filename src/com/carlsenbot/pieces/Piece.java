package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public abstract class Piece {
    private double value;
    private boolean isWhite;
    private Position position;
    private String name;

    public Piece(double value, boolean isWhite, Position position, String name) {
        this.value = value;
        this.isWhite = isWhite;
        this.position = position;
        this.name = name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
