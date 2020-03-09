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
     * @param id The id of the rook
     */
    public Rook(PieceColor color, Position position, int id) {
        super(5d, color, position, "Rook", id);
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Rook(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
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

    /*
     * Check if rook can move to the specified position
     */
    @Override
    public boolean isValidMove(Position target, boolean isAttacking) {
        // Every move is legal in forced mode
        if(GameManager.getInstance().isForceMode()) {
            return true;
        }

        Table table = GameManager.getInstance().getTable();
        Position source = getPosition();
        int currRow = source.getRow();
        int currCol = source.getCol();
        int targetRow = target.getRow();
        int targetCol = target.getCol();
        int difPos = 1, difRow = 0, difCol = 0;

        if (currRow != targetRow && currCol != targetCol) {
            // If it didn't move along the file/rank
            return false;
        }

        // When the rook is moving along the rows.
        if (currRow != targetRow) {
            if (currRow >= targetRow) {
                difPos = -1;
            }
            difRow = currRow + difPos;
            // I am going to iterate from currentRow to targetRow
            // I check every space to be empty
            for (int i = difRow; i != targetRow; i += difPos) {
                if (!table.isEmptyCell(difRow, currCol)) {
                    return false;
                }
            }
        }
        difPos = -1;
        // When the rook is moving along the columns.
        if (currCol != targetCol) {
            if (currCol >= targetCol) {
                difPos = -1;
            }
            difCol = currCol + difPos;
            // I am going to iterate from currentCol to targetCol
            // I check every space to be empty
            for (int i = difCol; i != targetCol; i += difPos) {
                if (!table.isEmptyCell(currRow, difCol)) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Move to the desired position
     */
    @Override
    public boolean move(Position target) {
        if (isValidMove(target, false)) {
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
        if (isValidMove(target, true)) {
            movePiece(target);
            return true;
        }
        return false;
    }
}
