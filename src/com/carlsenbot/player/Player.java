/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.player;

import com.carlsenbot.pieces.PieceColor;

public class Player {
    private boolean isWhite;
    private AI ai;

    public Player() {
        ai = new AI(this);
    }

    public Player(PieceColor color) {
        setColor(color);
        ai = new AI(this);
    }

    // Getters
    public boolean isWhite() { return isWhite; }
    public boolean isBlack() { return !isWhite; }
    public PieceColor getColor() {
        if(isWhite) {
            return PieceColor.White;
        } else {
            return PieceColor.Black;
        }
    }

    // Setters
    public void setColor(PieceColor color) {
        isWhite = color == PieceColor.White;
    }

    public boolean doAMove() {
        ai.think();
        return true;
    }
}
