/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public abstract class Piece {
    /*
        This class has is used in different scenarios. Generally, we use it
        to know if we can move a piece to a location, if it will do an
        attack to get there and what piece is captured.

        The reason we need to know what piece is captured is to be able
        to handle the special case of the en-passant (when the piece
        captured is not the same as the place where the attacking piece
        will actually move.

        There is another use case. As a convention, when we don't attack,
        but attackedPiece is set, it means we actually do a castle. The
        attacked piece stores the location of the rook that will be moved.
     */
    public static class MoveInfo {

        public boolean canMove;
        public boolean attacking;
        public Position attackedPiece;

        public MoveInfo() {
            canMove = false;
            attacking = false;
        }

        public MoveInfo(Position position) {
            canMove = false;
            attacking = false;
            attackedPiece = position;
        }

        public MoveInfo(boolean canMove, boolean attacking) {
            this.canMove = canMove;
            this.attacking = attacking;
        }

        public MoveInfo(boolean canMove, boolean attacking, Position pos) {
            this.canMove = canMove;
            this.attacking = attacking;
            this.attackedPiece = pos;
        }

        public void setMove() { canMove = true; }
        public void setStay() { canMove = false; }
        public void setAttack() { attacking = true; }
        public void setNotAttack() { attacking = false; }
        public void setAttackedPiece(Position target) { attackedPiece = target; }
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

    public Piece(Piece other) {
        setValue(other.value);
        setColor(other.getColor());
        setPosition(other.position);
        setName(other.name);
        setOnBoard(other.onBoard);
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
    public PieceColor getColor() {
        if(isWhite) {
            return PieceColor.White;
        } else {
            return PieceColor.Black;
        }
    }

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
    public void setAssignedTable(Table table) { this.assignedTable = table; }

    /**
     * Notify the movement to the game manager
     * @param target The position to move to
     */
    protected void movePiece(Position target) { setPosition(target); }

    /**
     * Notify the capture to the game manager
     * @param target The position to move to
     */
    protected void capturePiece(Position target) {
        assignedTable.removePiece(target);
        setPosition(target);
    }

    /**
     * Notify the capture to the game manager
     * @param target The position to move to
     * @param captured The position of the piece that is captured
     */
    protected void capturePiece(Position target, Position captured) {
        assignedTable.removePiece(captured);
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
    public abstract MoveInfo isValidMove(Position target);

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
