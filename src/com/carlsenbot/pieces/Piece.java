package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public abstract class Piece {
    private double value;
    private boolean isWhite;
    private Position position;
    private String name;

    public Piece(double value, PieceColor color, Position position, String name) {
        setValue(value);
        setColor(color);
        setPosition(position);
        setName(name);
    }

    public boolean isWhite() {
        return isWhite;
    }
    public boolean isBlack() { return !isWhite; }

    public void setColor(PieceColor color) {
        isWhite = color == PieceColor.White;
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

    protected String unicodeToChar(int unicode) {
        return Character.toString((char)unicode);
    }

    public abstract String getSymbol();

}
