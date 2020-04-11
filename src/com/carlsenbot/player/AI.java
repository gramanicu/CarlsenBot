/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.player;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.pieces.Piece;
import com.carlsenbot.position.Move;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;

import java.util.ArrayList;
import java.util.Random;

public class AI {
    private Player assignedPlayer;

    private String randomAI() {
        GameManager gameManager = GameManager.getInstance();
        Table table = gameManager.getTable();

        Random rand = new Random();
        Position source , target;

        Piece king;
        ArrayList<Move> possibleMoves = table.getAllPossibleMoves();
        int id = rand.nextInt(possibleMoves.size());

        if (assignedPlayer.isWhite()) {
            king = table.getPieces()[0][0];
        } else {
            king = table.getPieces()[1][0];
        }

        if (gameManager.getTable().getCheckSystem().isInCheck(assignedPlayer.getColor(), king.getPosition())) {
            gameManager.resign();
            return "";
        }

        source = possibleMoves.get(id).getStart();
        target = possibleMoves.get(id).getEnd();

        return source.toString() + target.toString();
    }


    public AI(Player player) {
        this.assignedPlayer = player;
    }

    public String think() {
//        return randomAI();
        GameManager gameManager = GameManager.getInstance();
        Table minimaxTable = new Table(gameManager.getTable());

//        Piece king;
//        if(assignedPlayer.isWhite()) {
//            king = minimaxTable.getPieces()[0][0];
//        } else {
//            king = minimaxTable.getPieces()[1][0];
//        }
//
//        if(gameManager.getTable().getCheckSystem().isInCheck(assignedPlayer.getColor(), king.getPosition())) {
//            gameManager.resign();
//            return "";
//        }

        int depth = 1;

        Move move = MiniMax.minimax(depth, true, minimaxTable);
        return move.getStart().toString() + move.getEnd().toString();
    }
}
