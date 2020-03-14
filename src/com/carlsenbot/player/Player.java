/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.player;

import com.carlsenbot.pieces.PieceColor;

public class Player {
    private boolean isWhite;

    // Getters
    public boolean isWhite() { return isWhite; }
    public boolean isBlack() { return !isWhite; }

    // Setters
    public void setColor(PieceColor color) {
        isWhite = color == PieceColor.White;
    }
}
