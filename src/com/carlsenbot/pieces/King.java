package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class King extends Piece {
    public King( boolean isWhite, Position position) {
        super(Double.MAX_VALUE, isWhite, position);
    }
}
