/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.table;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.pieces.Piece;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.position.Position;

public class CheckSystem {
    private byte[][] dangerW;
    private byte[][] dangerB;


    /**
     * Initialise an empty table
     */
    public CheckSystem() {
        dangerW = new byte[8][8];
        dangerB = new byte[8][8];
    }

    /**
     * Recompute the check table for the white player (who he is attacked by)
     * @param color The player
     */
    public void computeCheck(PieceColor color) {

        if(color == PieceColor.White) {
            dangerW = new byte[8][8];
        } else {
            dangerB = new byte[8][8];
        }

    }

    public boolean isInCheck(PieceColor playerColor) {
        Table table = GameManager.getInstance().getTable();
        Piece[] pieces;
        Position kingPos;
        if(playerColor.equals("White")) {
            pieces = table.getPieces()[1];
            kingPos = table.getPieces()[0][0].getPosition();
        } else {
            pieces = table.getPieces()[0];
            kingPos = table.getPieces()[1][0].getPosition();
        }
        for(Piece p : pieces) {
            if(p.isValidMove(kingPos).attacking) {
                return true;
            }
        }
        return false;
    }
}
