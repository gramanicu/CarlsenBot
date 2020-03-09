package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Bishop extends Piece {
    public Bishop(PieceColor color, Position position, int id) {
        super(3.25d, color, position, "Bishop", id);
    }

    public Bishop(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9815);
        } else {
            return unicodeToChar(9821);
        }
    }

    @Override
    public boolean move(Position target) {
        return false;
    }
}
