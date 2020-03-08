package com.carlsenbot.table;

import com.carlsenbot.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class BeginningTable extends Table {

    public List<Piece> setWhitePieces(){
         List<Piece> whitePieces = new ArrayList<>();
         // TODO: trebuie facut o lista cu piesele albe, care sa contina pozitia lor si numele lor
         return whitePieces;
    }

    public List<Piece> setBlackPieces(){
        List<Piece> blackPieces = new ArrayList<>();
        // TODO: Same
        return blackPieces;
    }

    public void setBoard(byte[][] matrix){
        List<Piece> whitePieces = setWhitePieces();
        List<Piece> blackPieces = setBlackPieces();
        // TODO: tb puse piesele pe tabla
        setMatrix(matrix);
    }
}
