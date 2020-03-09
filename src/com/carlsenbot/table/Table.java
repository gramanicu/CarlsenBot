/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.table;


import com.carlsenbot.pieces.Piece;
import com.carlsenbot.position.Position;

public class Table {
    private byte[][] positions;

    /**
     * Initialise an empty table
     */
    public Table() {
        positions = new byte[8][8];
    }

    /**
     * Initialise a table using a matrix of pieces
     * The format of the matrix ([2][16]) is:
     * - First line - white pieces
     * - Second line - black pieces
     * @param pieces The matrix of pieces
     */
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

    public byte[][] getPositions() { return positions; }

    /**
     * Add a piece to the table
     * @param piece The piece to add
     * @return If the piece could be added
     */
    public boolean addPiece(Piece piece) {
        Position p = piece.getPosition();
        if(isEmptyCell(p)) {
            positions[p.getRow()][p.getCol()] = piece.getId();
            return true;
        }
        return false;
    }

    /**
     * Check if a cell is empty at the specified position
     * @param target The position to check
     * @return If the cell is empty
     */
    public boolean isEmptyCell(Position target) {
        return positions[target.getRow()][target.getCol()] == 0;
    }

    /**
     * Check if a cell is empty at the specified coordinates
     * @param row The row of the cell to check
     * @param col The column of the cell to check
     * @return If the cell is empty
     */
    public boolean isEmptyCell(int row, int col) {
        return positions[row][col] == 0;
    }

    /**
     * Check if a cell is empty at the specified "chess coordinates"
     * @param target The chess coordinates to check
     * @return If the cell is empty
     */
    public boolean isEmptyCell(String target) {
        Position pos = new Position(target);
        return positions[pos.getRow()][pos.getCol()] == 0;
    }

    /**
     * Move a piece to a position
     * @param piece The piece to move
     * @param target The position to move to
     * @return if the piece was moved
     */
    public boolean movePiece(Piece piece, Position target) {
        setCell(piece.getPosition(), (byte) 0);
        setCell(target, piece.getId());
        return true;
    }

    /**
     * Set the value of the cell to one specified
     * @param target The position of the cell
     * @param value The new value
     */
    private void setCell(Position target, byte value) {
        positions[target.getRow()][target.getCol()] = value;
    }

    /**
     * Return the contents of the positions matrix as a string
     * It contains the id's of the pieces
     * @return A string containing the position matrix
     */
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
