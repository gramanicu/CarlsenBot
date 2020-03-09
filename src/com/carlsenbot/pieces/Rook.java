package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Rook extends Piece {
    public Rook(PieceColor color, Position position, int id) {
        super(5d, color, position, "Rook", id);
    }

    public Rook(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    @Override
    public String getSymbol() {
        if (isWhite()) {
            return unicodeToChar(9814);
        } else {
            return unicodeToChar(9820);
        }
    }

    @Override
    public boolean isValidMove(Position target) {
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

    @Override
    public boolean move(Position target) {
        if (isValidMove(target)) {
            movePiece(target);
            return true;
        }
        return false;
    }
}
