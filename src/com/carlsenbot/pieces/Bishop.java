/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
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
     * @param id The id of the bishop
     */
    public Bishop(PieceColor color, Position position, int id) {
        super(325d, color, position, "Bishop", id);
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Bishop(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
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
    protected boolean isValidMove(Position target, boolean isAttacking) {
        // Every move is legal in forced mode
        if(GameManager.getInstance().isForceMode()) {
            return true;
        }

        Table table = GameManager.getInstance().getTable();
        Position source = getPosition();
        int currRow = source.getRow();
        int currCol = source.getCol();
        int targetRow = target.getRow();
        int targetCol = target.getCol();
        int rowDiff = 1;
        int colDiff = 1;

        // Should move the same amount on both axes ( != 0 )
        if (source.getDiffRow(target) != source.getDiffCol(target) || source.getDiffRow(target) == 0) {
            return false;
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

        for(currRow += rowDiff; currRow <= targetRow; currRow += rowDiff) {
            // Check if empty cell

            if (!table.isEmptyCell(currRow, currCol)) {
                // If it is attacking and the target position was reached
                // we can capture the piece
                return isAttacking && currRow == targetRow;
            }

            currCol += colDiff;
        }

        return true;
    }

    /*
     * Move to the desired position
     */
    @Override
    public boolean move(Position target) {
        if (isValidMove(target, false)) {
            movePiece(target);
            return true;
        }
        return false;
    }

    /*
     * Attack the desired position
     */
    @Override
    public boolean attack(Position target) {
        if (isValidMove(target, true)) {
            capturePiece(target);
            return true;
        }
        return false;
    }
}
