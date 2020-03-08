package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class Knight extends Piece {
    public Knight(PieceColor color, Position position) {
        super(3d, color, position,"Knight");
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9816);
        } else {
            return unicodeToChar(9822);
        }
    }
}
