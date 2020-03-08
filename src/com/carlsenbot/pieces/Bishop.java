package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class Bishop extends Piece {
    public Bishop(PieceColor color, Position position) {
        super(3.25d, color, position, "Bishop");
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9815);
        } else {
            return unicodeToChar(9821);
        }
    }
}
