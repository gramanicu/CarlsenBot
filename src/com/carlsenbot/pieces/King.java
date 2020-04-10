/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class King extends Piece {
    // The king can't castle if he did his first move
    public boolean firstMove;
    // If the king has castled, he is guaranteed to move 1 cell at max
    public boolean castled;

    /**
     * Create a new king, with the specified position and id
     * @param color The color of the king
     * @param position The position of the king
     */
    public King(PieceColor color, Position position) {
        super(900d, color, position, "King");
        firstMove = true;
        castled = false;
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public King(PieceColor color, String position) {
        this(color, new Position(position));
    }

    public King(Piece other) {
        this(other.getColor(), new Position(other.getPosition()));
        firstMove = ((King) other).firstMove;
        castled = ((King) other).castled;
    }
    /*
     * Implementation of the get symbol
     */
    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9812);
        } else {
            return unicodeToChar(9818);
        }
    }

    /*
     * Check if king can move to the specified position
     */
    @Override
    public MoveInfo isValidMove(Position target) {
        MoveInfo info = new MoveInfo(target);

        Position source = getPosition();

        // If the target is the same cell
        if(source.getDistance(target) == 0) {
            return info;
        }

        // If the king hasn't castled and has it's first move, check if
        // the movement can be a castle
        if (!castled && firstMove && source.getDistance(target) > Math.sqrt(2)) {
            // Check for castle

            // Check if we move only along the x axis
            if (source.getDiffRow(target) != 0) {
                return info;
            }

            // Check if we move 2 cells
            if (source.getDistance(target) != 2) {
                return info;
            }

            // See if any position the king will pass through is in check or occupied
            // -1   - moving left
            //  1   - moving right
            int direction = (int) ((target.getCol() - source.getCol()) / source.getDiffCol(target));

            for(int i = source.getCol(); i != target.getCol() + direction; i+= direction) {
                Position interPos =  new Position(i, source.getRow());
                // If the intermediary positions are in check
                if(assignedTable.getCheckSystem().isInCheck(getColor(),interPos)) {
                    return info;
                }

                // If the intermediary position are not empty (except the starting one)
                if(!assignedTable.isEmptyCell(interPos) && !interPos.equals(source)) {
                    return info;
                }
            }

            Rook rook = null;
            Position rookPos;
            if(direction > 0) {
                // We do O-O
                rookPos = new Position(7, source.getY());
            } else {
                // We do O-O-O
                rookPos = new Position(0, source.getY());
            }

            Piece piece = assignedTable.getPiece(rookPos);
            if(piece instanceof Rook) {
                rook = (Rook) piece;
            }
            if(rook == null) {
                return info;
            } else if (rook.hasMoved) {
                return info;
            } else {
                // The rook can also castle
                info.setMove();

                // This is a trick we use
                info.attackedPiece = rookPos;
                return info;
            }
        }

        // If the king has moved once or has castled, we are guaranteed to move only 1 cell at a time
        if (source.getDistance(target) > Math.sqrt(2)) {
            return info;
        }

        // Check if the position is in check
        // TODO - deal with the case where a infinite loop is created (king checks king)
        if (assignedTable.getCheckSystem().isInCheck(getColor(), target)) {
            return info;
        }

        // If the target has a piece in it
        if (!assignedTable.isEmptyCell(target)) {
            // Check for enemy piece, to attack, else, don't move at all
            if (!isSameColor(target)) {
                info.setMove();
                info.setAttack();
            }
            return info;
        }

        // If the cell was empty, move
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
            firstMove = false;
            if(info.attacking) {
                capturePiece(target);
            } else {
                // If we need to castle
                Rook rook = (Rook) assignedTable.getPiece(info.attackedPiece);
                if(rook != null) {
                    Position newRookPos = new Position((getPosition().getX() + target.getX())/2, getPosition().getY());
                    assignedTable.teleportRookCastle(rook, newRookPos);
                    castled = true;
                }
                movePiece(target);
            }
        }
        return info;
    }
}
