/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Pawn extends Piece {
    boolean firstMove;

    /**
     * Helper function, to compute if the pawn is moving forward
     * @param target The position to move to
     * @return If the specified position is in the front
     */
    private boolean isForward(Position target) {
        Position source = getPosition();

        if(isBlack()) {
            return source.getRow() <= target.getRow();
        } else {
            return source.getRow() >= target.getRow();
        }
    }

    /**
     * Create a new pawn, with the specified position and id
     * @param color The color of the pawn
     * @param position The position of the pawn
     * @param id The id of the pawn
     */
    public Pawn(PieceColor color, Position position, int id) {
        super(100d, color, position, "Pawn", id);
        firstMove = true;
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Pawn(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    /*
     * Returns the bishop symbol
     */
    @Override
    public String getSymbol() {
        if (isWhite()) {
            return unicodeToChar(9817);
        } else {
            return unicodeToChar(9823);
        }
    }

    /*
     * Check if pawn can move to the specified position
     */
    @Override
    protected boolean isValidMove(Position target, boolean isAttacking) {
        // Every move is legal in forced mode
        if(GameManager.getInstance().isForceMode()) {
            return true;
        }

        Table table = GameManager.getInstance().getTable();
        Position source = getPosition();

        // Make sure the position is in the front of the pawn
        if(!isForward(target)) {
            return false;
        }

        // Make sure the pawn is moving along the "Y" axis
        if (source.getDiffCol(target) == 0) {
            // Not taking a piece
             if(!table.isEmptyCell(target)) {
                 return false;
             }

             // Check if it is moving 2 cells at max
            if (source.getDiffRow(target) > 2) {
                return false;
            }

            // Check if is moving 2 positions and it can do so
            // (first move of the game)
            return source.getDiffRow(target) == 1 || firstMove;
        } else {
            // Check if the pawn can attack and move diagonally
            if(isAttacking) {
                // If the pawn is moving diagonally 1 cell
                return source.getDistance(target) == Math.sqrt(2);
            } else {
                return false;
            }
        }
    }

    /*
     * Move to the desired position
     */
    @Override
    public boolean move(Position target) {
        if (isValidMove(target, false) && !isSameColor(target)) {
            movePiece(target);
            return true;
        }
        return false;
    }

    /*
     * Attack the desired position
     */
    @Override
    public boolean attack(Position target) {
        if (isValidMove(target, true) && !isSameColor(target)) {
            capturePiece(target);
            return true;
        }
        return false;
    }

}
