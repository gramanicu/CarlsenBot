package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;

public class Pawn extends Piece {
    public Pawn( boolean isWhite, Position position) {
        super(1d, isWhite, position,"Pawn");
    }
}
