/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class King extends Piece {
    public boolean firstMove;
    public boolean castled;

    /**
     * Check if the target position is in check
     * @param position The specified position
     * @return If it is in check
     */
    private boolean isInCheck(Position position) {
        if(isWhite()) {
            return GameManager.getInstance().getCheckSystem().isInCheck(position, PieceColor.White);
        } else {
            return GameManager.getInstance().getCheckSystem().isInCheck(position, PieceColor.Black);
        }
    }

    /**
     * Create a new king, with the specified position and id
     * @param color The color of the king
     * @param position The position of the king
     * @param id The id of the king
     */
    public King(PieceColor color, Position position) {
        super(Double.MAX_VALUE, color, position, "King");
        firstMove = false;
        castled = false;
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public King(PieceColor color, String position) {
        this(color, new Position(position));
    }

    /*
     * Implementation of the get symbol
     */
    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9812);
        } else {
            return unicodeToChar(9818);
        }
    }

    /*
     * Check if king can move to the specified position
     */
    @Override
    public MoveInfo isValidMove(Position target) {
        MoveInfo info = new MoveInfo();
        // Every move is legal in forced mode
        if (GameManager.getInstance().isForceMode()) {
            info.setMove();
            return info;
        }

        Table table = GameManager.getInstance().getTable();
        Position source = getPosition();
        int currRow = source.getRow();
        int currCol = source.getCol();
        int targetRow = target.getRow();
        int targetCol = target.getCol();

        // Check if the target is empty (or at least, we are attacking)
        if (!target.isEmpty()) {
//            if (!isAttacking) {
                return info;
//            }
        }

        if (castled && firstMove) {
            // After castling, the king can move 1 cell at max, which
            // a distance between 1 and sqrt(2) - for diagonal
            if (source.getDistance(target) > Math.sqrt(2)) {
                return info;
            }


            if (isInCheck(target)) {
                return info;
            }

            // If the target is free and the distance is ok, move the king
            info.setMove();
            return info;
        } else {
            // Check if it can castle (because we want to move more than 1 cell

            // Check if we move only along the x axis
            if (source.getDiffRow(target) != 0) {
                return info;
            }

            // Check if we move 2 cells
            if (source.getDistance(target) != 2) {
                return info;
            }

            // TODO - Check if the rook can castle

            info.setMove();
            return info;
        }
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
