package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Pawn extends Piece {
    boolean firstMove;

    public Pawn(PieceColor color, Position position, int id) {
        super(1d, color, position, "Pawn", id);
        firstMove = false;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean isValidMove(Table table, int currentRow, int currentCol, int newRow, int newCol) {
        if (isWhite()) {
            if (currentRow > newRow) {
                return false;
            }
        } else {
            if (currentRow < newRow) {
                return false;
            }
        }

        if (currentCol == newCol) {
            //Not taking a piece
            if (isWhite()) {
                if (table.getPositions()[currentRow + 1][currentCol] != 0) {
                    return false;
                }
            } else {
                if (table.getPositions()[currentRow - 1][currentCol] != 0) {
                    return false;
                }
            }

            if (Math.abs(currentCol - newCol) > 2) {
                return false;
            }

            if (Math.abs(currentCol - newCol) == 2) {
                if (firstMove) {
                    return false;
                }
                if (isWhite()) {
                    if (table.getPositions()[currentRow + 2][currentCol] != 0) {
                        return false;
                    }
                } else {
                    if (table.getPositions()[currentRow - 2][currentCol] != 0) {
                        return false;
                    }
                }
            }
        } else {
            if (Math.abs(newCol - currentCol) != 1) {
                return false;
            }
            if (Math.abs(newRow - currentRow) != 1) {
                return false;
            }
        }

        if (table.getPositions()[newRow][newCol] != 0) {
            return false;
        }

        if (newCol < 0 || newCol > 7) {
            return false;
        }

        if(isWhite()) {
            if(newRow < 0) {
                return false;
            }
        } else {
            if(newRow > 7) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getSymbol() {
        if (isWhite()) {
            return unicodeToChar(9817);
        } else {
            return unicodeToChar(9823);
        }
    }

    @Override
    public boolean move(Position target, Table table) {
        if (isValidMove(table, getPosition().getY(), getPosition().getX(), target.getX(), target.getY())) {
            super.setPosition(target);
            return true;
        }
        return false;
    }
}
