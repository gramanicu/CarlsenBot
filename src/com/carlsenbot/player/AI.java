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

    private String randomAI() {
        GameManager gameManager = GameManager.getInstance();
        Table table = gameManager.getTable();

        Random rand = new Random();
        Position source = null, target = null;

        boolean validMove = false;

        while(!validMove) {
            int id = rand.nextInt(16);
            Piece piece;
            Piece king;

            if(assignedPlayer.isWhite()) {
                piece = table.getPieces()[0][id];
                king = table.getPieces()[0][0];
            } else {
                piece = table.getPieces()[1][id];
                king = table.getPieces()[1][0];
            }

            if(gameManager.getTable().getCheckSystem().isInCheck(assignedPlayer.getColor(), king.getPosition())) {
                gameManager.resign();
                return "";
            }
            if(piece == null) { continue; }


            // Try to execute a move
            source = piece.getPosition();
            target = new Position(rand.nextInt(8),rand.nextInt(8));
            validMove = piece.isValidMove(target).canMove;
        }

        return source.toString() + target.toString();
    }


    public AI(Player player) {
        this.assignedPlayer = player;
    }

    public String think() {
//        return randomAI();
        Table minmaxTable = new Table(assignedPlayer)
    }
}
