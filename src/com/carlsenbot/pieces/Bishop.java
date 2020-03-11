/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;

public class Bishop extends Piece {
    /**
     * Create a new bishop, with the specified position and id
     * @param color The color of the bishop
     * @param position The position of the bishop
     */
    public Bishop(PieceColor color, Position position) {
        super(325d, color, position, "Bishop");
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
    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9815);
        } else {
            return unicodeToChar(9821);
        }
    }


    /*
     * Check if bishop can move to the specified position
     */
    @Override
    protected MoveInfo isValidMove(Position target) {
        MoveInfo info = new MoveInfo();
        // Every move is legal in forced mode
        if(GameManager.getInstance().isForceMode()) {
            info.setMove();
            return info;
        }

        Position source = getPosition();
        int currRow = source.getRow();
        int currCol = source.getCol();
        int targetRow = target.getRow();
        int targetCol = target.getCol();
        int rowDiff = 1;
        int colDiff = 1;

        // Should move the same amount on both axes ( != 0 )
        if (source.getDiffRow(target) != source.getDiffCol(target) || source.getDiffRow(target) == 0) {
            return info;
        }

        // Evaluate the direction of movement in the Y axis
        if (currRow > targetRow){
            rowDiff = -1;
        }

        // Evaluate the direction of movement in the X axis
        if (currCol > targetCol){
            colDiff = -1;
        }

        // Check the existence of pieces along the path
        // Move to the first position
        currCol += colDiff;


        // TODO - check this
        for(currRow += rowDiff; currRow <= targetRow; currRow += rowDiff) {
            // Check if empty cell
            if (assignedTable.isNotEmptyCell(currRow, currCol)) {
                // If it is attacking and the target position was reached
                // we can capture the piece
                if(currRow == targetRow && isSameColor(target)) {
                    info.setMove();
                    info.setAttack();
                }
            }

            currCol += colDiff;
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
            if(info.attacking) {
                capturePiece(target);
            } else {
                movePiece(target);
            }
        }
        return info;
    }
}
