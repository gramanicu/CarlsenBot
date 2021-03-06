/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class Pawn extends Piece {
    boolean enPassante;
    boolean moved;

    /**
     * Helper function, to compute if the pawn is moving forward
     * @param target The position to move to
     * @return If the specified position is in the front
     */
    private boolean isForward(Position target) {
        Position source = getPosition();

        if(isBlack()) {
            return source.getRow() <= target.getRow();
        } else {
            return source.getRow() >= target.getRow();
        }
    }

    /**
     * Create a new pawn, with the specified position and id
     * @param color The color of the pawn
     * @param position The position of the pawn
     */
    public Pawn(PieceColor color, Position position) {
        super(10d, color, position, "Pawn");
        moved = false;
        enPassante = false;
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Pawn(PieceColor color, String position) {
        this(color, new Position(position));
    }

   public Pawn(Piece other) {
        this(other.getColor(), new Position(other.getPosition()));
        enPassante = ((Pawn) other).enPassante;
        moved = ((Pawn) other).moved;
    }

    @Override
    public String getSymbol() {
        if (isWhite()) {
            return unicodeToChar(9817);
        } else {
            return unicodeToChar(9823);
        }
    }

    /*
     * Check if pawn can move to the specified position
     */
    @Override
    public MoveInfo isValidMove(final Position target) {
        MoveInfo info = new MoveInfo(target);

        Position source = getPosition();

        // If the target is the same cell
        if(source.getDistance(target) == 0) {
            return info;
        }

        // Make sure the position is in the front of the pawn
        if(!isForward(target)) {
            return info;
        }

        // Make sure the pawn is moving along the "Y" axis
        if (source.getDiffCol(target) == 0) {
            // Not taking a piece
             if(!assignedTable.isEmptyCell(target)) {
                 return info;
             }

             // Check if it is moving 2 cells at max
            if (source.getDiffRow(target) > 2) {
                return info;
            }

            if (source.getDiffRow(target) == 1) {
                info.setMove();
            } else if (!moved) {
                // Check if is moving 2 positions and it can do so
                // (first move of the game)
                Position inter = new Position(target.toString());
                inter.setRow((inter.getRow() + source.getRow())/2);
                if(assignedTable.isEmptyCell(inter)) {
                    info.setMove();
                }
            }
        } else if (!isSameColor(target) && !assignedTable.isEmptyCell(target)) {
            // Check if the pawn can attack and move diagonally
            if(source.getDistance(target) == Math.sqrt(2)) {
                info.setMove();
                info.setAttack();
            }
        } else if (assignedTable.isEmptyCell(target)) {
            // This could be an "en passant"

            // Check if it is 1 cell one the diagonal
            if(source.getDistance(target) == Math.sqrt(2)) {
                int rowDiff = 1;
                if(isBlack()) {
                    rowDiff = -1;
                }

                // Check if we can do an "en passant"
                Position tpos = new Position(target.getCol(), target.getRow() + rowDiff);
                if(assignedTable.canBeEnPassanted(tpos, getColor())) {
                    // Do the en passant
                    info.setAttackedPiece(tpos);
                    info.setMove();
                    info.setAttack();
                }
            }
        }
        return info;
    }

    /*
     * Move to the desired position
     */
    @Override
    public MoveInfo move(Position target) {
        MoveInfo info = isValidMove(target);

        if (info.canMove) {
            moved = true;

            if (target.getRow() == 0) {
                assignedTable.getPieces()[0][(this.getId()) - 1] = new Queen(PieceColor.White, target);
                assignedTable.getPieces()[0][(this.getId()) - 1].setId(getId());
                assignedTable.getPieces()[0][(this.getId()) - 1].setOnBoard(true);
                assignedTable.getPieces()[0][(this.getId()) - 1].setAssignedTable(assignedTable);
                assignedTable.getPieces()[0][(this.getId()) - 1].setValue(90d);
            } else if (target.getRow() == 7) {
                assignedTable.getPieces()[1][(this.getId() * -1) - 1] = new Queen(PieceColor.Black, target);
                assignedTable.getPieces()[1][(this.getId() * -1) - 1].setId(getId());
                assignedTable.getPieces()[1][(this.getId() * -1) - 1].setOnBoard(true);
                assignedTable.getPieces()[1][(this.getId() * -1) - 1].setAssignedTable(assignedTable);
                assignedTable.getPieces()[1][(this.getId() * -1) - 1].setValue(90d);
            }

            if(info.attacking) {
                capturePiece(target, info.attackedPiece);
            } else {
                movePiece(target);
            }
        }
        return info;
    }
}
