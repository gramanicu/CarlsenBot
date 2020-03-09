/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public abstract class Piece {
    private double value;
    private boolean isWhite;
    private Position position;
    private String name;
    private byte id;
    private boolean onBoard;

    // Constructor

    /**
     * Create a new piece, with the specified properties
     * @param value The value of the piece
     * @param color The color (white/black) of the piece
     * @param position The position of the piece
     * @param name The name of the piece
     * @param id The id of the piece
     */
    public Piece(double value, PieceColor color, Position position, String name, int id) {
        setValue(value);
        setColor(color);
        setPosition(position);
        setName(name);
        setId((byte) id);
        setOnBoard(onBoard);
    }

    // Getters
    public boolean isOnBoard() {
        return onBoard;
    }
    public boolean isWhite() {
        return isWhite;
    }
    public boolean isBlack() {
        return !isWhite;
    }
    public byte getId() {
        return id;
    }
    public Position getPosition() { return position; }
    public String getName() { return name; }
    public double getValue() { return value; }

    // Setters
    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }
    public void setColor(PieceColor color) {
        isWhite = color == PieceColor.White;
    }
    public void setId(byte id) {
        this.id = id;
    }
    public void setPosition(Position position) { this.position = position; }
    public void setName(String name) { this.name = name; }
    public void setValue(double value) { this.value = value; }

    /**
     * Notify the movement to the game manager
     * @param target The position to move to
     */
    protected void movePiece(Position target) {
        GameManager.getInstance().movePieceGame(this, target);
        setPosition(target);
    }

    /**
     * Shows the character associated with a unicode
     * @param unicode The unicode of the wanted char
     * @return The desired character
     */
    protected String unicodeToChar(int unicode) {
        return Character.toString((char) unicode);
    }

    /*
     * Abstract methods, that must pe implemented by each specific piece
     */
    public abstract String getSymbol();
    public abstract boolean isValidMove(Position target);
    public abstract boolean move(Position target);

    /**
     * A wrapper for the abstract, to use "chess coordinates" for movement
     * @param target The "chess coordinates" to move to
     * @return If the move was possible
     */
    public boolean move(String target) {
        return move(new Position(target));
    }

    public boolean isSameColor(Position target, Piece[][] pieces) {
        Table table = GameManager.getInstance().getTable();
        if(table.getPieceByPosition(target, pieces).getId() * this.getId() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public abstract boolean attack(Position target);
}
