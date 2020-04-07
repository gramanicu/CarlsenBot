/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.table;

import com.carlsenbot.pieces.King;
import com.carlsenbot.pieces.Piece;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.position.Position;

public class CheckSystem {
    Table table;

    public CheckSystem(Table table) {
        this.table = table;
    }

    public boolean isInCheck(PieceColor playerColor, Position pos) {
        Piece[] pieces;
        if(playerColor == PieceColor.White) {
            pieces = table.getPieces()[1];
        } else {
            pieces = table.getPieces()[0];
        }

        for(Piece p : pieces) {
            if (p == null || p instanceof King) {
                continue;
            }
            if(p.isValidMove(pos).attacking) {
                return true;
            }
        }
        return false;
    }
}
