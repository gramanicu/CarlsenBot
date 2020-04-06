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

    public boolean isInCheck(PieceColor playerColor, Position pos) {
        Table table = GameManager.getInstance().getTable();
        Piece[] pieces;
        if(playerColor == PieceColor.White) {
            pieces = table.getPieces()[1];
        } else {
            pieces = table.getPieces()[0];
        }

        for(Piece p : pieces) {
            if (p == null) {
                continue;
            }
            if(p.isValidMove(pos).attacking) {
                return true;
            }
        }
        return false;
    }
}
