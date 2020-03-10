/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Queen extends Piece {
    /**
     * Create a new queen, with the specified position and id
     * @param color The color of the queen
     * @param position The position of the queen
     * @param id The id of the queen
     */
    public Queen(PieceColor color, Position position, int id) {
        super(880d, color, position,"Queen", id);
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Queen(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    /*
     * Implementation of the get symbol
     */
    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9813);
        } else {
            return unicodeToChar(9819);
        }
    }

    /*
     * Check if queen can move to the specified position
     */
    @Override
    public boolean isValidMove(Position target, boolean isAttacking) {
        // Every move is legal in forced mode
        if(GameManager.getInstance().isForceMode()) {
            return true;
        }

        // A queen theoretically is a rook combined with a bishop
        // So we are going to use the two mechanics that are already implemented

        Table table = GameManager.getInstance().getTable();
        Position source = getPosition();
        int currRow = source.getRow();
        int currCol = source.getCol();
        int targetRow = target.getRow();
        int targetCol = target.getCol();
        int difPos = 1, difRow = 0, difCol = 0, rowDiff = 1, colDiff = 1;

        if (currRow != targetRow && currCol != targetCol) {
            // If it didn't move along the file/rank
            return false;
        }

        // When the queen is moving along the rows.
        if (currRow != targetRow) {
            if (currRow > targetRow) {
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
        // When the queen is moving along the columns.
        if (currCol != targetCol) {
            if (currCol > targetCol) {
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

        // Evaluate the direction of movement in the Y axis
        if (currRow > targetRow){
            rowDiff = -1;
        }

        // Evaluate the direction of movement in the X axis
        if (currCol > targetCol){
            colDiff = -1;
        }

        // Check the existence of pieces along the path
        // Move to the first position
        currCol += colDiff;

        for(currRow += rowDiff; currRow != targetRow; currRow += rowDiff) {
            // Check if empty cell
            if (!table.isEmptyCell(currRow, currCol)) {
                return false;
            }
            currCol += colDiff;
        }
        return true;
    }

    /*
     * Move to the desired position
     */
    @Override
    public boolean move(Position target) {
        if(isValidMove(target, false)) {
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
        if(isValidMove(target, true)) {
            movePiece(target);
            return true;
        }
        return false;
    }
}
