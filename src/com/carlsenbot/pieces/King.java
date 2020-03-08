package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class King extends Piece {
    public King(PieceColor color, Position position) {
        super(Double.MAX_VALUE, color, position, "King");
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9812);
        } else {
            return unicodeToChar(9818);
        }
    }
}
