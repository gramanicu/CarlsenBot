package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class King extends Piece {
    public King(PieceColor color, Position position, int id) {
        super(Double.MAX_VALUE, color, position, "King", id);
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9812);
        } else {
            return unicodeToChar(9818);
        }
    }

    @Override
    public boolean move(Position target, Table table) {
        return false;
    }
}
