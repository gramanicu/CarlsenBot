/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Radu Ionita, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

import com.carlsenbot.pieces.*;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class GameManager {
    /*
     * All the pieces are stored in a matrix, as it can offer faster access
     * to the pieces.
     *
     * First row of the matrix [0][*] - white pieces
     * Second row of the matrix [1][*] - black pieces
     *
     * The convention for the pieces indexes is:
     * [*][0] - King
     * [*][1] - Queen
     * [*][2,3] - Rooks
     * [*][4,5] - Knights
     * [*][6,7] - Bishops
     * [*][8-15] - Pawns
     *
     * ! IMPORTANT !
     * The id's of the pieces are a bit different. Because the value of a empty
     * cell in the matrix of "positions" is 0, we need to use the 1 -> 16 range
     * for white pieces, respectively -1 -> -16 range for the black pieces.
     */

    private static GameManager instance = null;
    private boolean isWhiteTurn;
    private int round;
    private Table table;
    private Piece[][] pieces;

    /* ----------------------------------------
     * Attention when using the following methods, as they
     * can be a bit dangerous to use
     */

    /**
     * Assign a new table to the game table
     * @param table The new table
     */
    public void setTable(Table table) { this.table = table; }

    /**
     * Remove the game instance
     */
    public void removeInstance() {
        instance = null;
    }

    // ---- End of the "dangerous" methods ----

    // Private, helper methods

    /**
     * Set the white pieces to the normal, initial position
     * @return The array of the pieces
     */
    private Piece[] initWhitePieces(){
        PieceColor color = PieceColor.White;
        Piece[] newPiece = new Piece[16];

        // Main pieces
        newPiece[2] = new Rook(color, "a1", 3);
        newPiece[4] = new Knight(color, "b1", 5);
        newPiece[6] = new Bishop(color, "c1", 7);
        newPiece[1] = new Queen(color, "d1", 2);
        newPiece[0] = new King(color, "e1", 1);
        newPiece[7] = new Bishop(color, "f1", 8);
        newPiece[5] = new Knight(color, "g1", 6);
        newPiece[3] = new Rook(color, "h1", 4);

        // Pawns
        newPiece[8] = new Pawn(color, "a2", 9);
        newPiece[9] = new Pawn(color, "b2", 10);
        newPiece[10] = new Pawn(color, "c2", 11);
        newPiece[11] = new Pawn(color, "d2", 12);
        newPiece[12] = new Pawn(color, "e2", 13);
        newPiece[13] = new Pawn(color, "f2", 14);
        newPiece[14] = new Pawn(color, "g2", 15);
        newPiece[15] = new Pawn(color, "h2", 16);

        return newPiece;
    }

    /**
     * Set the black pieces to the normal, initial position
     * @return The array of the pieces
     */
    private Piece[] initBlackPieces(){
        PieceColor color = PieceColor.Black;
        Piece[] newPiece = new Piece[16];

        // Main pieces
        newPiece[2] = new Rook(color, "a8", -3);
        newPiece[4] = new Knight(color, "b8", -5);
        newPiece[6] = new Bishop(color, "c8", -7);
        newPiece[1] = new Queen(color, "d8", -2);
        newPiece[0] = new King(color, "e8", -1);
        newPiece[7] = new Bishop(color, "f8", -8);
        newPiece[5] = new Knight(color, "g8", -6);
        newPiece[3] = new Rook(color, "h8", -4);

        // Pawns
        newPiece[8] = new Pawn(color, "a7", -9);
        newPiece[9] = new Pawn(color, "b7", -10);
        newPiece[10] = new Pawn(color, "c7", -11);
        newPiece[11] = new Pawn(color, "d7", -12);
        newPiece[12] = new Pawn(color, "e7", -13);
        newPiece[13] = new Pawn(color, "f7", -14);
        newPiece[14] = new Pawn(color, "g7", -15);
        newPiece[15] = new Pawn(color, "h7", -16);

        return newPiece;
    }

    // Constructor, made private for singleton
    private GameManager() {
        pieces = new Piece[2][16];
        round = 0;
        isWhiteTurn = true;
    }

    // Getters
    public Table getTable() { return table; }

    // Get (and initialise if needed) the instance of the singleton
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Returns a piece with a specific id, managed by the GameManager
     * @param id The id of the piece
     * @return The desired piece
     */
    public Piece getPieceByID(int id) {
        if(id > 0) {
            id -= 1;
            return pieces[0][id];
        } else {
            id += 1;
            return pieces[1][-id];
        }

    }

    /**
     * Returns the symbol of a piece with the specified id, that
     * is managed by the GameManager
     * @param id The id of the piece
     * @return The symbol of the desired piece
     */
    private String getSymbolOfPiece(int id) {
        if (id == 0) {
            return " ";
        } else {
            return getPieceByID(id).getSymbol();

            // Use the following line if you want to show pieces as
            // simple chars : King - K, Queen - Q , etc.

            // return Character.toString(getPieceByID(id).getName().charAt(0));
        }
    }

    /**
     * Move a piece managed by the GameManager
     * NOTE - probably will change name (or remove completely), as
     * this function currently is used to notify the game manager that
     * the piece changed it's position
     *
     * @param piece The piece to be moved
     * @param target The position to be moved to
     * @return Whether or not the move was possible
     */
    public boolean movePieceGame(Piece piece, Position target) {
        return table.movePiece(piece, target);
    }

    /**
     * Initialise a new game
     * @return Whether or not it was possible to do the operation
     */
    public boolean initializeGame() {
        pieces[0] = initWhitePieces();
        pieces[1] = initBlackPieces();
        table = new Table(pieces);
        round = 0;
        isWhiteTurn = true;
        return true;
    }

    /**
     * Start a game ( start the AI, wait for input, etc. )
     * @return The game has finished successfully
     */
    public boolean startGame() {
        // If a game is now going on, reinitialise
        if(round != 0 && !isWhiteTurn) { initializeGame(); }
        return false;
    }

    /**
     * Returns a string containing all the pieces, with their specific position
     * @return The list of pieces
     */
    public String getPiecesPositions() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 16; ++i) {
            // There can be cases where we do not have 32 pieces
            if(pieces[0][i] != null) {
                sb.append(pieces[0][i].getSymbol());
                sb.append(pieces[0][i].getPosition());
                sb.append("\n");
            }
        }

        for (int i = 0; i < 16; ++i) {
            // There can be cases where we do not have 32 pieces
            if(pieces[1][i] != null) {
                sb.append(pieces[1][i].getSymbol());
                sb.append(pieces[1][i].getPosition());
                sb.append("\n");
            }
        }
        return sb.toString();
    }


    /**
     * Return a string containing the chess table, with all the pieces
     * @return The string
     */
    public String printTable() {
        StringBuilder sb = new StringBuilder();
        byte[][] positions = table.getPositions();

        for (int i = 0; i < 8; ++i) {
            sb.append(8 - i);
            sb.append(" ");
            for (int j = 0; j < 8; ++j) {
                sb.append("[");
                sb.append(getSymbolOfPiece(positions[i][j]));
                sb.append("]");
            }
            sb.append("\n");
        }
        sb.append("   A  B  C  D  E  F  G  H\n");
        return sb.toString();
    }
}
