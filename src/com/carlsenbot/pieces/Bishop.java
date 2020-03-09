/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Bishop extends Piece {
    public Bishop(PieceColor color, Position position, int id) {
        super(3.25d, color, position, "Bishop", id);
    }

    public Bishop(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9815);
        } else {
            return unicodeToChar(9821);
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
        int rowDiff = 1;
        int colDiff = 1;

        // Should move the same amount on both axes ( != 0 )
        if (source.getDiffRow(target) != source.getDiffCol(target) || source.getDiffRow(target) == 0) {
            return false;
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
        if (isValidMove(target)) {
            movePiece(target);
            return true;
        }
        return false;
    }
}
