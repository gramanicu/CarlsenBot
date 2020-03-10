/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Radu Ionita, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Knight extends Piece {

    public Knight(PieceColor color, Position position, int id) {
        super(300d, color, position,"Knight", id);
    }

    public Knight(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    /*
     * Implementation of the get symbol
     */
    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9816);
        } else {
            return unicodeToChar(9822);
        }
    }

    /*
     * Check if knight can move to the specified position
     */
    @Override
    public boolean isValidMove(Position target, boolean isAttacking) {
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
        if(table.getPositions()[targetRow][targetCol] != 0) {
            return false;
        }
        if(Math.abs(targetRow - currRow) == 2 && Math.abs(targetCol - currCol) == 1) {
            return true;
        }
        return Math.abs(targetRow - currRow) == 1 && Math.abs(targetCol - currCol) == 2;
    }
    @Override
    public boolean move(Position target) {
        if(isValidMove(target, false)) {
            movePiece(target);
            return true;
        }
        return false;
    }

    @Override
    public boolean attack(Position target) {
        if(isValidMove(target, true)) {
            capturePiece(target);
            return true;
        }
        return false;
    }
}
