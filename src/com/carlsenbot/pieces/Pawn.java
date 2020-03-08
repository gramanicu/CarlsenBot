package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class Pawn extends Piece {
    public Pawn(PieceColor color, Position position) {
        super(1d, color, position,"Pawn");
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9817);
        } else {
            return unicodeToChar(9823);
        }
    }
}
