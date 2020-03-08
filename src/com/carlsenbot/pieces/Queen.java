package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class Queen extends Piece {
    public Queen(PieceColor color, Position position, int id) {
        super(9d, color, position,"Queen", id);
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9813);
        } else {
            return unicodeToChar(9819);
        }
    }
}
