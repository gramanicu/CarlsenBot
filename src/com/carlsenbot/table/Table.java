package com.carlsenbot.table;

import com.carlsenbot.pieces.Piece;

public class Table {
    private byte[][] matrix;

    public Table() {
        matrix = new byte[8][8];
    }

    public Table(Piece[][] pieces) {
        super();
    }

    public byte[][] getMatrix() {
        return matrix;
    }


}
