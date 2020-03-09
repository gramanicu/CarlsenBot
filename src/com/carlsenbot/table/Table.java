package com.carlsenbot.table;

import com.carlsenbot.pieces.Piece;
import com.carlsenbot.position.Position;

public class Table {
    private byte[][] positions;

    public Table() {
        positions = new byte[8][8];
    }

    public Table(Piece[][] pieces) {
        this();
        // Whites
        for (int i = 0; i < 16; ++i) {
            Position pos = pieces[0][i].getPosition();
            positions[pos.getY()][pos.getX()] = pieces[0][i].getId();
        }

        // Blacks
        for (int i = 0; i < 16; ++i) {
            Position pos = pieces[1][i].getPosition();
            positions[pos.getY()][pos.getX()] = pieces[1][i].getId();
        }
    }

    public byte[][] getPositions() {
        return positions;
    }

    public boolean isEmptyCell(Position target) {
        return positions[target.getRow()][target.getCol()] == 0;
    }

    public boolean movePiece(Piece piece, Position target) {
        setCell(piece.getPosition(), (byte) 0);
        setCell(target, piece.getId());
        return true;
    }

    private void setCell(Position target, byte value) {
        positions[target.getRow()][target.getCol()] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                sb.append(String.format("%3s", positions[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
