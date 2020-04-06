/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
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
        super(Double.MAX_VALUE, color, position, "King");
        firstMove = true;
        castled = false;
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public King(PieceColor color, String position) {
        this(color, new Position(position));
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
        // Every move is legal in forced mode

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

            // TODO - Check if the rook can castle
            /*
            check if we can castle
            if we can castle, we set castled true
             */

            boolean flag = false;
            int diffCol = 1;

            if(source.getDiffCol(target) == 2) {
                diffCol = -1;
            }

            for(int i = getPosition().getCol(); i <=target.getCol(); i += diffCol) {
                if(assignedTable.getAssignedGameManager().getCheckSystem().isInCheck(getColor(), new Position(getPosition().getRow(), i))) {
                    flag = true;
                }
            }

            if(!flag) {
                if (!castled) {
                    castled = true;
                }
            }

        }

        // If the king has moved once or has castled, we are guaranteed to move only 1 cell at a time
        if (source.getDistance(target) > Math.sqrt(2)) {
            return info;
        }

        // Check if the position is in check
//        if (isInCheck(target)) {
//            return info;
//        }
        if(castled) {
            /**
            we should teleport them
             */
        } else {
            // If the target has a piece in it
            if (!assignedTable.isEmptyCell(target)) {
                // Check for enemy piece, to attack, else, don't move at all
                if (!isSameColor(target)) {
                    info.setMove();
                    info.setAttack();
                }
                return info;
            }
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
            if(info.attacking) {
                capturePiece(target);
            } else {
                movePiece(target);
            }
        }
        return info;
    }
}
