package com.carlsenbot.pieces;

import com.carlsenbot.main.Game;
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
        Table table = Game.getInstance().getTable();
        Position source = getPosition();
        int currRow = source.getRow();
        int currCol = source.getCol();
        int targetRow = target.getRow();
        int targetCol = target.getCol();
        int rowMoveDif = 1;
        int colMoveDif = 1;
        int sumRowDif = 0;
        int sumColDif = 0;

        if (currRow == targetRow || currCol == targetCol){
            // Did not move diagonally
            return false;
        }

        if (Math.abs(targetRow - currRow) != Math.abs(targetCol - currCol)){
            // a3 -> b4
            // The difference of the rows must not be different from the difference of the columns
            // When the bishop is moving, it is moving equally on the row and column.
            return false;
        }

        if (currRow > targetRow){
            rowMoveDif = -1;
        }

        if (currCol > targetCol){
            colMoveDif = -1;
        }

        sumRowDif = currRow + rowMoveDif;
        sumColDif = currCol + colMoveDif;

        for (int i = sumRowDif; i != targetRow; i += rowMoveDif){
            if (table.getPositions()[sumColDif][sumRowDif] != 0){
                return false;
                // If the bishop doesn't meet any piece on its trajectory
            }
            sumColDif += colMoveDif;
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
