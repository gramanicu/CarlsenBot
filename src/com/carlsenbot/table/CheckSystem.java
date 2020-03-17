/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.table;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.pieces.Piece;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.position.Position;

public class CheckSystem {
    public CheckSystem() { }


    public boolean isInCheck(PieceColor playerColor) {
        Table table = GameManager.getInstance().getTable();
        Piece[] pieces;
        Position kingPos;
        if(playerColor == PieceColor.White) {
            pieces = table.getPieces()[1];
            kingPos = table.getPieces()[0][0].getPosition();
        } else {
            pieces = table.getPieces()[0];
            kingPos = table.getPieces()[1][0].getPosition();
        }

        for(Piece p : pieces) {
            if (p == null) {
                continue;
            }
            if(p.isValidMove(kingPos).attacking) {
                return true;
            }
        }
        return false;
    }
}
