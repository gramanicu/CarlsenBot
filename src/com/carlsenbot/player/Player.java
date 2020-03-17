/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.player;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.position.Position;

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
        String move = ai.think();
        if(move.equals("")) {
            return false;
        }
        Position start = new Position(Character.toString(move.charAt(0)) + Character.toString(move.charAt(1)));
        Position target = new Position(Character.toString(move.charAt(2)) + Character.toString(move.charAt(3)));
        GameManager.getInstance().moveAndSend(start, target);
        return true;
    }
}
