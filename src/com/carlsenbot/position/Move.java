/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.position;

import com.carlsenbot.pieces.Piece;
import com.carlsenbot.pieces.PieceColor;

public class Move {
    private Position start;
    private Position end;
    private PieceColor who;
    private Piece what;

    /**
     * Describes a move
     * @param start Where the piece was
     * @param end Where the piece is now
     * @param what The piece that was moved
     */
    public Move(Position start, Position end, Piece what) {
        this.start = start;
        this.end = end;
        this.what = what;
        who = what.getColor();
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public double getDistance() {
        return start.getDistance(end);
    }

    public PieceColor getPlayerColor() {
        return who;
    }

    public Piece getPiece() {
        return what;
    }

    @Override
    public String toString() {
        return "" + start + end + what.getSymbol();
    }
}
