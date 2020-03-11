/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;

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
    public Pawn(PieceColor color, Position position) {
        super(100d, color, position, "Pawn");
        firstMove = true;
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Pawn(PieceColor color, String position) {
        this(color, new Position(position));
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
    protected MoveInfo isValidMove(Position target) {
        MoveInfo info = new MoveInfo();

        // Every move is legal in forced mode
        if(GameManager.getInstance().isForceMode()) {
            info.setMove();
            return info;
        }
        Position source = getPosition();

        // Make sure the position is in the front of the pawn
        if(!isForward(target)) {
            return info;
        }

        // Make sure the pawn is moving along the "Y" axis
        if (source.getDiffCol(target) == 0) {
            // Not taking a piece
             if(!assignedTable.isEmptyCell(target)) {
                 return info;
             }

             // Check if it is moving 2 cells at max
            if (source.getDiffRow(target) > 2) {
                return info;
            }

            // Check if is moving 2 positions and it can do so
            // (first move of the game)
            if (source.getDiffRow(target) == 1 || firstMove) {
                info.setMove();
            }
        } else if (!isSameColor(target)){
            // Check if the pawn can attack and move diagonally
            if(source.getDistance(target) == Math.sqrt(2)) {
                info.setMove();
                info.setAttack();
            }
        }
        return info;
    }

    /*
     * Move to the desired position
     */
    @Override
    public MoveInfo move(Position target) {
        MoveInfo info = isValidMove(target);

        if (info.canMove) {
            if(info.attacking) {
                capturePiece(target);
            } else {
                movePiece(target);
            }
        }
        return info;
    }
}
