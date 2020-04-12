/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.table;

import com.carlsenbot.pieces.King;
import com.carlsenbot.pieces.Pawn;
import com.carlsenbot.pieces.Piece;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.position.Position;

public class CheckSystem {
    Table table;

    public CheckSystem(Table table) {
        this.table = table;
    }

    private boolean inKingArea(King piece, Position target) {
        Position kingPos = piece.getPosition();

        Position topLeft = new Position(kingPos.getX()-1, kingPos.getY()-1);
        Position topCenter = new Position(kingPos.getX(), kingPos.getY()-1);
        Position topRight = new Position(kingPos.getX()+1, kingPos.getY()-1);
        Position middleLeft = new Position(kingPos.getX()-1, kingPos.getY());
        Position middleRight = new Position(kingPos.getX()+1, kingPos.getY());
        Position bottomLeft = new Position(kingPos.getX()-1, kingPos.getY()+1);
        Position bottomCenter= new Position(kingPos.getX(), kingPos.getY()+1);
        Position bottomRight = new Position(kingPos.getX() + 1, kingPos.getY()+1);

        return target.equals(topLeft) || target.equals(topRight) || target.equals(topCenter) ||
                target.equals(middleLeft) || target.equals(middleRight) || target.equals(bottomLeft) ||
                target.equals(bottomCenter) || target.equals(bottomRight);
    }

    public boolean kingIsInCheck(PieceColor kingColor) {
        Position kingPos = null;
        if(kingColor == PieceColor.White) {
            for(Piece p : table.getPieces()[0]) {
                if(p instanceof King) {
                    kingPos = p.getPosition();
                }
            }
        } else {
            for(Piece p : table.getPieces()[1]) {
                if(p instanceof King) {
                    kingPos = p.getPosition();
                }
            }
        }

        if(kingPos != null) {
            return isInCheck(kingColor, kingPos);
        }
        return  false;
    }

    public boolean isInCheck(PieceColor playerColor, Position pos) {
        Piece[] pieces;
        if(playerColor == PieceColor.White) {
            pieces = table.getPieces()[1];
        } else {
            pieces = table.getPieces()[0];
        }

        for(Piece p : pieces) {
            if (p == null) {
                continue;
            } else if (p instanceof King) {
                if(inKingArea((King) p, pos)) {
                    return true;
                }
            }

            // Every piece, except the pawn can move to the cells it attacks
            if(p instanceof Pawn) {
                if(p.isValidMove(pos).attacking) {
                    return true;
                }
            } else {
                if(p.isValidMove(pos).canMove) {
                    return true;
                }
            }


        }
        return false;
    }
}
