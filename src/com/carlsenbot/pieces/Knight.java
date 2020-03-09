package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Knight extends Piece {
    public Knight(PieceColor color, Position position, int id) {
        super(3d, color, position,"Knight", id);
    }

    public Knight(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9816);
        } else {
            return unicodeToChar(9822);
        }
    }

    @Override
    public boolean move(Position target) {
        return false;
    }
}
