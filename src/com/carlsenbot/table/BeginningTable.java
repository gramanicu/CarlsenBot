package com.carlsenbot.table;

import com.carlsenbot.pieces.*;
import com.carlsenbot.position.Position;

import java.util.ArrayList;
import java.util.List;

public class BeginningTable extends Table {

    public List<Piece> getWhitePieces(){
         List<Piece> whitePieces = new ArrayList<>();

        // Main pieces
        whitePieces.add(new Rook(true, new Position("a1")));
        whitePieces.add(new Knight(true, new Position("b1")));
        whitePieces.add(new Bishop(true, new Position("c1")));
        whitePieces.add(new Queen(true, new Position("d1")));
        whitePieces.add(new King(true, new Position("e1")));
        whitePieces.add(new Bishop(true, new Position("f1")));
        whitePieces.add(new Knight(true, new Position("g1")));
        whitePieces.add(new Rook(true, new Position("h1")));

        // Pawns
        whitePieces.add(new Pawn(true,new Position("a2")));
        whitePieces.add(new Pawn(true,new Position("b2")));
        whitePieces.add(new Pawn(true,new Position("c2")));
        whitePieces.add(new Pawn(true,new Position("d2")));
        whitePieces.add(new Pawn(true,new Position("e2")));
        whitePieces.add(new Pawn(true,new Position("f2")));
        whitePieces.add(new Pawn(true,new Position("g2")));
        whitePieces.add(new Pawn(true,new Position("h2")));

        return whitePieces;
    }

    public List<Piece> getBlackPieces(){
        List<Piece> blackPieces = new ArrayList<>();

        // Main pieces
        blackPieces.add(new Rook(false, new Position("a8")));
        blackPieces.add(new Knight(false, new Position("b8")));
        blackPieces.add(new Bishop(false, new Position("c8")));
        blackPieces.add(new Queen(false, new Position("d8")));
        blackPieces.add(new King(false, new Position("e8")));
        blackPieces.add(new Bishop(false, new Position("f8")));
        blackPieces.add(new Knight(false, new Position("g8")));
        blackPieces.add(new Rook(false, new Position("h8")));

        // Pawns
        blackPieces.add(new Pawn(false,new Position("a7")));
        blackPieces.add(new Pawn(false,new Position("b7")));
        blackPieces.add(new Pawn(false,new Position("c7")));
        blackPieces.add(new Pawn(false,new Position("d7")));
        blackPieces.add(new Pawn(false,new Position("e7")));
        blackPieces.add(new Pawn(false,new Position("f7")));
        blackPieces.add(new Pawn(false,new Position("g7")));
        blackPieces.add(new Pawn(false,new Position("h7")));

        return blackPieces;
    }

    public void setBoard(byte[][] matrix){
        List<Piece> whitePieces = getWhitePieces();
        List<Piece> blackPieces = getBlackPieces();
        // TODO: Set on matrix the pieces
        setMatrix(matrix);
    }
}
