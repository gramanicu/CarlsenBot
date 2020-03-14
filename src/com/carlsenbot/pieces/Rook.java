/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Rook extends Piece {
    /**
     * Create a new rook, with the specified position and id
     * @param color The color of the rook
     * @param position The position of the rook
     */
    public Rook(PieceColor color, Position position) {
        super(500d, color, position, "Rook");
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Rook(PieceColor color, String position) {
        this(color, new Position(position));
    }

    /*
     * Returns the bishop symbol
     */
    @Override
    public String getSymbol() {
        if (isWhite()) {
            return unicodeToChar(9814);
        } else {
            return unicodeToChar(9820);
        }
    }

//    protected static MoveInfo validMoveRook() {};

    /*
     * Check if rook can move to the specified position
     */
    @Override
    public MoveInfo isValidMove(Position target) {
        MoveInfo info = new MoveInfo();
        // Every move is legal in forced mode
        if(GameManager.getInstance().isForceMode()) {
            info.setMove();
            return info;
        }

        Position source = getPosition();
        int difPos = 1;
        int difRow = (int) source.getDiffRow(target);
        int difCol = (int) source.getDiffCol(target);


        if(difRow == 0) {
            // Moving along a row (horizontally)
            if(source.getCol() > target.getCol()) {
                difPos = -1;
            }

            // Check the cells along the row
            for (int i = source.getCol() + difPos; i != target.getCol(); i += difPos) {
                // Check if the cells are empty
                if (assignedTable.isNotEmptyCell(source.getRow(), i)) {
                    return info;
                }
            }

            // If the target has a piece in it
            if(!assignedTable.isEmptyCell(target)) {
                // Check for enemy piece, to attack, else, don't move at all
                if(!isSameColor(target)) {
                    info.setMove();
                    info.setAttack();
                }
                return info;
            }

            // If we reached the end, and we didn't have to attack, just move
            info.setMove();
            return info;
        } else if (difCol == 0) {
            // Moving along a column (vertically)
            if(source.getRow() > target.getRow()) {
                difPos = -1;
            }

            // Check the cells along the column
            for (int i = source.getRow() + difPos; i != target.getRow(); i += difPos) {
                // Check if the cells are empty
                if (assignedTable.isNotEmptyCell(i, source.getCol())) {
                    return info;
                }
            }

            // If the target has a piece in it
            if(!assignedTable.isEmptyCell(target)) {
                // Check for enemy piece, to attack, else, don't move at all
                if(!isSameColor(target)) {
                    info.setMove();
                    info.setAttack();
                }
                return info;
            }

            // If we reached the end, and we didn't have to attack, just move
            info.setMove();
            return info;
        } else {
            // If it didn't move along a row/columns
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
