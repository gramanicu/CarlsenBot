/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Pawn extends Piece {
    boolean firstMove;

    private boolean isForward(Position target) {
        Position source = getPosition();

        if(isBlack()) {
            return source.getRow() <= target.getRow();
        } else {
            return source.getRow() >= target.getRow();
        }
    }

    public Pawn(PieceColor color, Position position, int id) {
        super(1d, color, position, "Pawn", id);
        firstMove = true;
    }

    public Pawn(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean isValidMove(Position target) {
        Table table = GameManager.getInstance().getTable();
        Position source = getPosition();
        int currRow = source.getRow();
        int currCol = source.getCol();
        int targetRow = target.getRow();
        int targetCol = target.getCol();

        // Make sure the position is in the front of the pawn
        if(!isForward(target)) {
            return false;
        }

        // Make sure the pawn is moving along the "Y" axis
        if (currCol == targetCol) {

            // Not taking a piece
             if(!table.isEmptyCell(target)) {
                 return false;
             }

             // Check if it is moving 2 cells at max
            if (Math.abs(currRow - targetRow) > 2) {
                return false;
            }

            // Check if is moving 2 positions and it can do so
            //(first move of the game)
            return Math.abs(currRow - targetRow) != 2 || firstMove;
        } else {
            // If the pawn is moving diagonally (attack move)

            return Math.abs(targetCol - currCol) == 1
                    && Math.abs(targetRow - currRow) == 1;
        }
    }

    @Override
    public String getSymbol() {
        if (isWhite()) {
            return unicodeToChar(9817);
        } else {
            return unicodeToChar(9823);
        }
    }

    @Override
    public boolean move(Position target) {
        if (isValidMove(target)) {
            movePiece(target);
            return true;
        }
        return false;
    }

    @Override
    public boolean attack(Position target) {
        move(target);
        return false;
    }

}
