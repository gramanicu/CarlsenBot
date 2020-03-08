package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Pawn extends Piece {
    public Pawn(PieceColor color, Position position, int id) {
        super(1d, color, position,"Pawn", id);
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9817);
        } else {
            return unicodeToChar(9823);
        }
    }

    @Override
    public boolean move(Position target) {
        Table table = new Table();
    }


}
