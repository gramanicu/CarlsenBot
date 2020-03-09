package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Queen extends Piece {
    public Queen(PieceColor color, Position position, int id) {
        super(9d, color, position,"Queen", id);
    }

    public Queen(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9813);
        } else {
            return unicodeToChar(9819);
        }
    }

    @Override
    public boolean isValidMove(Position target) {
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

    @Override
    public boolean move(Position target) {
        if(isValidMove(target)) {
            movePiece(target);
            return true;
        }
        return false;
    }

    @Override
    public boolean attack(Position target) {
        return false;
    }
}
