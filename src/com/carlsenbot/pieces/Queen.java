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
    public Queen(PieceColor color, Position position) {
        super(880d, color, position,"Queen");
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Queen(PieceColor color, String position) {
        this(color, new Position(position));
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
    public MoveInfo isValidMove(Position target) {
        MoveInfo info = new MoveInfo();
        // Every move is legal in forced mode
        if(GameManager.getInstance().isForceMode()) {
            info.setMove();
            return info;
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
            return info;
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
                    return info;
                }
            }
        }
        difPos = 1;
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
                    return info;
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
                return info;
            }
            currCol += colDiff;
        }

        info.setMove();
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
