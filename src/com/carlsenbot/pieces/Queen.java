package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

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

    @Override
    public boolean move(Position target, Table table) {
        return false;
    }
}
