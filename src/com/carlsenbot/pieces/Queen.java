/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.pieces;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

public class Queen extends Piece {
    /**
     * Create a new queen, with the specified position and id
     *
     * @param color    The color of the queen
     * @param position The position of the queen
     */
    public Queen(PieceColor color, Position position) {
        super(880d, color, position, "Queen");
    }

    /*
     * Same as the other one, is uses a "chess position"
     */
    public Queen(PieceColor color, String position) {
        this(color, new Position(position));
    }

    /*
     * Implementation of the get symbol
     */
    @Override
    public String getSymbol() {
        if (isWhite()) {
            return unicodeToChar(9813);
        } else {
            return unicodeToChar(9819);
        }
    }

    /*
     * Check if queen can move to the specified position
     */
    @Override
    public MoveInfo isValidMove(Position target) {
        MoveInfo info = new MoveInfo();
        Position source = getPosition();

        if (isWhite()) {
            if (new Rook(PieceColor.White, source).isValidMove(target) != null) {
                info.setMove();
                info.setAttack();
            }
            if (new Bishop(PieceColor.White, source).isValidMove(target) != null) {
                info.setMove();
                info.setAttack();
            }
        } else {
            if (new Rook(PieceColor.Black, source).isValidMove(target) != null) {
                info.setMove();
                info.setAttack();
            }
            if (new Bishop(PieceColor.Black, source).isValidMove(target) != null) {
                info.setMove();
                info.setAttack();
            }
        }
        return info;

//        if (isWhite()) {
//            Rook r = new Rook(PieceColor.White, source);
//            table.addPiece(r);
//            if (r.isValidMove(target) != null) {
//                info.setMove();
//                info.setAttack();
//                return info;
//            }
//            table.removePiece(r);
//        } else {
//            Rook r = new Rook(PieceColor.Black, source);
//            table.addPiece(r);
//            if (r.isValidMove(target) != null) {
//                info.setMove();
//                info.setAttack();
//                return info;
//            }
//            table.removePiece(r);
//        }
//
//        if (isWhite()) {
//            Bishop bishop = new Bishop(PieceColor.White, source);
//            table.addPiece(bishop);
//            if (bishop.isValidMove(target) != null) {
//                info.setMove();
//                info.setAttack();
//                return info;
//            }
//            table.removePiece(bishop);
//        } else {
//            Bishop bishop= new Bishop(PieceColor.Black, source);
//            table.addPiece(bishop);
//            if (bishop.isValidMove(target) != null) {
//                info.setMove();
//                info.setAttack();
//                return info;
//            }
//            table.removePiece(bishop);
//        }
//
//        return info;
    }

    /*
     * Move to the desired position
     */
    @Override
    public MoveInfo move(Position target) {
        MoveInfo info = isValidMove(target);

        if (info.canMove) {
            if (info.attacking) {
                capturePiece(target);
            } else {
                movePiece(target);
            }
        }
        return info;
    }
}
