/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class Queen extends Piece {
    /**
     * Create a new queen, with the specified position and id
     * @param color    The color of the queen
     * @param position The position of the queen
     */
    public Queen(PieceColor color, Position position) {
        super(90d, color, position, "Queen");
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Queen(PieceColor color, String position) {
        this(color, new Position(position));
    }

    public Queen(Queen other) {
        this(other.getColor(), new Position(other.getPosition()));
    }
    /*
     * Implementation of the get symbol
     */
    @Override
    public String getSymbol() {
        if (isWhite()) {
            return unicodeToChar(9813);
        } else {
            return unicodeToChar(9819);
        }
    }

    /*
     * Check if queen can move to the specified position
     */
    @Override
    public MoveInfo isValidMove(Position target) {
        Position source = getPosition();
        MoveInfo info = new MoveInfo(target);

        // If the target is the same cell
        if(source.getDistance(target) == 0) {
            return info;
        }

        MoveInfo diagonal = Rook.isValidRookMove(source, target, assignedTable);
        MoveInfo line = Bishop.isValidBishopMove(source, target, assignedTable);

        info.canMove = diagonal.canMove || line.canMove;
        info.attacking = diagonal.attacking || line.attacking;

        return info;
    }

    /*
     * Move to the desired position
     */
    @Override
    public MoveInfo move(Position target) {
        MoveInfo info = isValidMove(target);

        if (info.canMove) {
            if (info.attacking) {
                capturePiece(target);
            } else {
                movePiece(target);
            }
        }
        return info;
    }
}
