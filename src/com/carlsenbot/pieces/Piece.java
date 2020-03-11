/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public abstract class Piece {
    public static class MoveInfo {
        public boolean canMove;
        public boolean attacking;

        public MoveInfo() {
            canMove = false;
            attacking = false;
        }

        public MoveInfo(boolean canMove, boolean attacking) {
            this.canMove = canMove;
            this.attacking = attacking;
        }

        public void setMove() { canMove = true; };
        public void setStay() { canMove = false; };
        public void setAttack() { attacking = true; };
        public void setNotAttack() { attacking = false; };
    }

    private double value;
    private boolean isWhite;
    private Position position;
    private String name;
    private byte id;
    private boolean onBoard;
    protected Table assignedTable;

    // Constructor

    /**
     * Create a new piece, with the specified properties
     * @param value The value of the piece
     * @param color The color (white/black) of the piece
     * @param position The position of the piece
     * @param name The name of the piece
     */
    public Piece(double value, PieceColor color, Position position, String name) {
        setValue(value);
        setColor(color);
        setPosition(position);
        setName(name);
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
    public Table getAssignedTable() { return assignedTable; }

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
    public void setAssignedTable(Table table) { this.assignedTable = table; };

    /**
     * Notify the movement to the game manager
     * @param target The position to move to
     */
    protected void movePiece(Position target) {
        setPosition(target);
    }

    /**
     * Notify the capture to the game manager
     * @param target The position to move to
     */
    protected void capturePiece(Position target) {
        GameManager.getInstance().move(this.position, target);
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


    protected boolean isSameColor(Position target) {
        return assignedTable.isSameColor(this.getPosition(), target);
    }

    /**
     * A wrapper for the abstract, to use "chess coordinates" for movement
     * @param target The "chess coordinates" to move to
     * @return If the move was possible
     */
    public MoveInfo move(String target) {
        return move(new Position(target));
    }

    /*
     * Abstract methods, that must pe implemented by each specific piece
     */

    /**
     * Check if it can move to the specified position
     * @param target The desired position
     * @return If the move can happen and if the piece needs to
     *         attack to perform it
     */
    protected abstract MoveInfo isValidMove(Position target);

    /**
     * Return the symbol of the piece
     * @return The symbol
     */
    public abstract String getSymbol();

    /**
     * Move the piece to the target cell
     * @param target The desired position
     * @return If the move possible
     */
    public abstract MoveInfo move(Position target);
}
