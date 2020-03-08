package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public abstract class Piece {
    private double value;
    private boolean isWhite;
    private Position position;
    private String name;
    private byte id;
    private boolean onBoard;

    public Piece(double value, PieceColor color, Position position, String name, int id) {
        setValue(value);
        setColor(color);
        setPosition(position);
        setName(name);
        setId((byte) id);
        setOnBoard(onBoard);
    }

    public boolean isOnBoard() {
        return onBoard;
    }

    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isBlack() {
        return !isWhite;
    }

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
        return Character.toString((char) unicode);
    }

    public abstract String getSymbol();

    public abstract boolean move(Position target);

}
