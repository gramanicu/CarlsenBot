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

    public boolean isValidMove(Table table, Position currentPos, Position newPos) {
        if (isWhite()) {
            if (currentPos.getX() > newPos.getX()) {
                return false;
            }
        } else {

            if (currentPos.getX() < newPos.getX()) {
                return false;
            }
        }
        System.out.println(currentPos.getY());
        System.out.println(currentPos.getX());
        System.out.println(newPos.getY());
        if (currentPos.getY() == newPos.getY()) {
            //Not taking a piece
            if (isWhite()) {
                if (table.getPositions()[currentPos.getX() + 1][currentPos.getY()] != 0) {
                    return false;
                }
            } else {
                if (table.getPositions()[currentPos.getX() - 1][currentPos.getY()] != 0) {
                    return false;
                }
            }

            if (Math.abs(currentPos.getX() - newPos.getX()) > 2) {
                return false;
            }

            if (Math.abs(currentPos.getX() - newPos.getX()) == 2) {
                if (firstMove) {
                    return false;
                }
                if (isWhite()) {
                    if (table.getPositions()[currentPos.getX() + 2][currentPos.getY()] != 0) {
                        return false;
                    }
                } else {
                    if (table.getPositions()[currentPos.getX() - 2][currentPos.getY()] != 0) {
                        return false;
                    }
                }
            }
        } else {
            if (Math.abs(newPos.getY() - currentPos.getY()) != 1 || Math.abs(newPos.getX() - currentPos.getX()) != 1) {
                return false;
            }
        }

        if (table.getPositions()[newPos.getX()][newPos.getY()] != 0) {
            return false;
        }

        if (newPos.getY() < 0 || newPos.getY() > 7) {
            return false;
        }

        if(isWhite()) {
            if(newPos.getX() < 0) {
                return false;
            }
        } else {
            if(newPos.getX() > 7) {
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
        if (isValidMove(table, getPosition(), target)) {
            super.setPosition(target);
            return true;
        }
        return false;
    }
}
