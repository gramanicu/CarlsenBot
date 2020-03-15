/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.player;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.pieces.Piece;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

import java.util.Random;

public class AI {
    private Player assignedPlayer;

    public AI(Player player) {
        this.assignedPlayer = player;
    }

    public void think() {
        GameManager gameManager = GameManager.getInstance();
        Table table = gameManager.getTable();

        Random rand = new Random();

        boolean validMove = false;

        while(!validMove) {
            int id = rand.nextInt(16);
            Piece piece;

            if(assignedPlayer.isWhite()) {
                piece = table.getPieces()[0][id];
            } else {
                piece = table.getPieces()[1][id];
            }

            // Try to execute a move
            validMove = GameManager.getInstance().moveAndSend(piece.getPosition(),
                    new Position(rand.nextInt(8),rand.nextInt(8)));
        }
    }
}
