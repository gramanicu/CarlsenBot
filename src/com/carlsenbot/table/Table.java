package com.carlsenbot.table;

public class Table {
    private byte[][] matrix;

    public Table() {
        matrix = new byte[8][8];
    }

    public byte[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(byte[][] matrix) {
        this.matrix = matrix;
    }
}
