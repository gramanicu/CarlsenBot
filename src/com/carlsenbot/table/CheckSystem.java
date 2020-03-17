/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.table;

import com.carlsenbot.main.GameManager;
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
        Position kingPosWhite = GameManager.getInstance().getTable().getPieces()[0][0].getPosition();
        Position kingPosBlack = GameManager.getInstance().getTable().getPieces()[1][0].getPosition();
        if (playerColor.equals("White")){
            for (int i = 1; i < 16; i++){
            }
        }
        return true;
    }
}
