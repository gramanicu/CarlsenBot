package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class Pawn extends Piece {
    public Pawn(Position position, boolean isWhite) {
        super(1d, isWhite, position);
    }
}
