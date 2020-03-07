package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public abstract class Piece {
    private double value;
    private boolean isWhite;
    private Position position;

    public Piece(double value, boolean isWhite, Position position) {
        this.value = value;
        this.isWhite = isWhite;
        this.position = position;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
}
