/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.position;

import com.carlsenbot.pieces.*;

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

    public Move(Move other) {
        this.start = new Position(other.start);
        this.end = new Position(other.end);
        Piece p = other.what;
        if (p instanceof King) {
            what = new King(p);
        } else if (p instanceof Queen) {
            what = new Queen(p);
        } else if (p instanceof Knight) {
            what = new Knight(p);
        } else if (p instanceof Bishop) {
            what = new Bishop(p);
        } else if (p instanceof Rook) {
            what = new Rook(p);
        } else if (p instanceof Pawn) {
            what = new Pawn(p);
        }
        assert what != null;
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

    public Piece getPiece() {
        return what;
    }

    @Override
    public String toString() {
        return "" + start + end + what.getSymbol();
    }
}
