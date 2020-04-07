/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Bishop extends Piece {
    /**
     * Create a new bishop, with the specified position and id
     * @param color The color of the bishop
     * @param position The position of the bishop
     */
    public Bishop(PieceColor color, Position position) {
        super(30d, color, position, "Bishop");
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Bishop(PieceColor color, String position) {
        this(color, new Position(position));
    }

    /*
     * Returns the bishop symbol
     */

    public Bishop(Bishop other) {
        this(other.getColor(), new Position(other.getPosition()));
    }

    @Override
    public String getSymbol() {
        if (isWhite()) {
            return unicodeToChar(9815);
        } else {
            return unicodeToChar(9821);
        }
    }

    /**
     * A method used to check if a move is valid, from a bishop perspective.
     * As the queen movement combines that of a rook and a bishop, it made
     * sense to use the same method for both.
     * @param source The position of the piece to be moved
     * @param target The point to move to
     * @param table The table the piece is assigned to
     * @return If the move can happen and if the piece needs to
     *         attack to perform it
     */
    protected static MoveInfo isValidBishopMove(Position source, Position target, Table table) {
        MoveInfo info = new MoveInfo();

        int currRow = source.getRow();
        int currCol = source.getCol();
        int targetRow = target.getRow();
        int targetCol = target.getCol();

        int rowDiff = 1;
        int colDiff = 1;

        // Should move the same amount on both axes
        if (source.getDiffRow(target) != source.getDiffCol(target) || source.getDiffRow(target) == 0) {
            return info;
        }

        // Evaluate the direction of movement in the Y axis
        if (currRow > targetRow) {
            rowDiff = -1;
        }

        // Evaluate the direction of movement in the X axis
        if (currCol > targetCol) {
            colDiff = -1;
        }

        // Check the existence of pieces along the path
        // Move to the first position
        int x = currCol + colDiff;

        for (int i = currRow + rowDiff; i != targetRow; i += rowDiff) {
            // Check if empty cell
            if (table.isNotEmptyCell(i, x)) {
                // If it is attacking and the target position was reached
                // we can capture the piece
                return info;
            }
            x += colDiff;
        }


        // If the target has a piece in it
        if(!table.isEmptyCell(target)) {
            // Check for enemy piece, to attack, else, don't move at all
            if(!table.isSameColor(source, target)) {
                info.setMove();
                info.setAttack();
            }
            return info;
        }

        info.setMove();
        return info;
    }

    /*
     * Check if bishop can move to the specified position
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

        return isValidBishopMove(source, target, assignedTable);
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
