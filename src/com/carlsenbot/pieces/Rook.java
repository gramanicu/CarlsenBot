/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Rook extends Piece {
    /**
     * Create a new rook, with the specified position and id
     * @param color The color of the rook
     * @param position The position of the rook
     */
    public Rook(PieceColor color, Position position) {
        super(500d, color, position, "Rook");
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Rook(PieceColor color, String position) {
        this(color, new Position(position));
    }

    /*
     * Returns the bishop symbol
     */
    @Override
    public String getSymbol() {
        if (isWhite()) {
            return unicodeToChar(9814);
        } else {
            return unicodeToChar(9820);
        }
    }

    /*
     * Check if rook can move to the specified position
     */
    @Override
    public MoveInfo isValidMove(Position target) {
        MoveInfo info = new MoveInfo();
        // Every move is legal in forced mode
        if(GameManager.getInstance().isForceMode()) {
            info.setMove();
            return info;
        }

        Table table = GameManager.getInstance().getTable();
        Position source = getPosition();
        int currRow = source.getRow();
        int currCol = source.getCol();
        int targetRow = target.getRow();
        int targetCol = target.getCol();
        int difPos = 1, difRow, difCol;

        if (currRow != targetRow && currCol != targetCol) {
            // If it didn't move along the file/rank
            return info;
        }

        // When the rook is moving along the rows.
        if (currRow != targetRow) {
            if (currRow > targetRow) {
                difPos = -1;
            }
            difRow = currRow + difPos;
            // I am going to iterate from currentRow to targetRow
            // I check every space to be empty
            for (int i = difRow; i != targetRow; i += difPos) {
                if (assignedTable.isNotEmptyCell(difRow, currCol)) {
                    return info;
                }
            }
        }
        difPos = 1;
        // When the rook is moving along the columns.
        if (currCol != targetCol) {
            if (currCol > targetCol) {
                difPos = -1;
            }
            difCol = currCol + difPos;
            // I am going to iterate from currentCol to targetCol
            // I check every space to be empty
            for (int i = difCol; i != targetCol; i += difPos) {
                if (assignedTable.isNotEmptyCell(currRow, difCol)) {
                    return info;
                }
            }
        }

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
