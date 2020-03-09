package com.carlsenbot.pieces;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Rook extends Piece {
    public Rook(PieceColor color, Position position, int id) {
        super(5d, color, position,"Rook", id);
    }

    public Rook(PieceColor color, String position, int id) {
        this(color, new Position(position), id);
    }

    @Override
    public String getSymbol() {
        if(isWhite()) {
            return unicodeToChar(9814);
        } else {
            return unicodeToChar(9820);
        }
    }

    @Override
    public boolean move(Position target) {
        return false;
    }

    @Override
    public boolean isValidMove(Position target) {
        return false;
    }
}
