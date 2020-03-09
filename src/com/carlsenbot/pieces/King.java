/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class King extends Piece {
    public boolean firstMove;
    public boolean castled;
    public King(PieceColor color, Position position, int id) {
        super(Double.MAX_VALUE, color, position, "King", id);
        firstMove = false;
        castled = false;
    }

    public King(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9812);
        } else {
            return unicodeToChar(9818);
        }
    }

    @Override
    public boolean isValidMove(Position target){
        Table table = GameManager.getInstance().getTable();
        Position source = getPosition();
        int currRow = source.getRow();
        int currCol = source.getCol();
        int targetRow = target.getRow();
        int targetCol = target.getCol();
        if(Math.abs(targetRow - currRow) >= 2 && Math.abs(targetCol - currCol) >= 2) {
            if(firstMove) {
                return false;
            }
            // castling
            if(targetCol - currCol == 2 && currRow == targetRow) {
                if(table.getPositions()[currRow][targetCol + 1] != 0 || table.getPositions()[currRow][targetCol + 2] != 0) {
                    return false;
                }
            } else if (currCol - targetCol == 3 && currRow == targetRow) {
                    if (table.getPositions()[currRow][targetCol - 1] != 0 || table.getPositions()[currRow][targetCol - 2] != 0 || table.getPositions()[currRow][targetCol - 3] != 0) {
                        return false;
                    }
            } else {
                return false;
            }

            // if we get here, king is ready to castle

            castled = true;
            return true;
        }
        if(table.getPositions()[targetRow][targetCol] != 0) {
            return false;
        }
        return Math.abs(currCol - targetCol) < 2 && Math.abs(currRow - targetRow) < 2;

    }
    @Override
    public boolean move(Position target) {
        if(isValidMove(target)) {
            firstMove = true;
            movePiece(target);
            return true;
        }
        return false;
    }
}
