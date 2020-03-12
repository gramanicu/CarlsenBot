/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Radu Ioniță, Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

import com.carlsenbot.pieces.Bishop;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.position.Position;

public class Main {
    public static void main(String[] args) {
        GameManager game = GameManager.getInstance();

        game.initialize();
        game.resetPieces();
        //game.startGame();
        Bishop b6 = new Bishop(PieceColor.White, "h1");
        game.getTable().getPiece(new Position("h1")).move("a8");


        game.printTable();
    }
}
