/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.player;

import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.position.Move;
import com.carlsenbot.table.Table;

import java.util.ArrayList;

public class MiniMax {
    private static double minimax(int depth, boolean maxPlayer, Table table, double alpha, double beta) {
        ArrayList<Move> possibleMoves = table.getAllPossibleMoves(table.getTurnColor());
        if(depth == 0 || possibleMoves.size() == 0) {
            double val;

            if(possibleMoves.size() != 0) {
                val = EvaluationBoards.evaluateBoard(table);
            } else {
                val = Double.POSITIVE_INFINITY;
            }

            if (!maxPlayer) {
                if(table.getTurnColor() == PieceColor.Black)  {
                    return val;
                } else {
                    return -val;
                }
            } else {
                if(table.getTurnColor() == PieceColor.Black)  {
                    return -val;
                } else {
                    return val;
                }
            }
        }

        double best;
        if(maxPlayer) {
            best = Double.NEGATIVE_INFINITY;

            for(Move move : possibleMoves) {
                Table afterMove = new Table(table);
                afterMove.movePiece(move.getStart(), move.getEnd());
                best = Math.max(best, minimax(depth-1, false, afterMove, alpha, beta));

                alpha = Math.max(alpha, best);
                if(beta <= alpha) {
                    return best;
                }
            }
        } else {
            best = Double.POSITIVE_INFINITY;

            for(Move move : possibleMoves) {
                Table afterMove = new Table(table);
                afterMove.movePiece(move.getStart(), move.getEnd());
                best = Math.min(best, minimax(depth-1, true, afterMove, alpha, beta));

                beta = Math.min(beta, best);
                if(beta <= alpha) {
                    return best;
                }
            }
        }
        return best;
    }

    /**
     * Runs a minimax algorithm (with alpha-beta pruning) to determine the best move
     * @param depth The depth of the search
     * @param maxPlayer If the player is maximizing
     * @param table The table (current piece position)
     * @return The computed move
     */
    public static Move minimax(int depth, boolean maxPlayer, Table table) {
        ArrayList<Move> possibleMoves = table.getAllPossibleMoves(table.getTurnColor());
        double best = Double.NEGATIVE_INFINITY;
        Move bestMove = null;

        for(Move move : possibleMoves) {
            Table afterMove = new Table(table);
            afterMove.movePiece(move.getStart(), move.getEnd());
            double value = minimax(depth - 1, !maxPlayer, afterMove, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

            if(value >= best) {
                best = value;
                bestMove = move;
            }
        }

        return bestMove;
    }


}
