/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.table;

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

    public boolean isInCheck(Position position, PieceColor playerColor) {
        if(playerColor == PieceColor.White) {
            return dangerW[position.getRow()][position.getCol()] != 0;
        } else {
            return dangerB[position.getRow()][position.getCol()] != 0;
        }
    }
}
