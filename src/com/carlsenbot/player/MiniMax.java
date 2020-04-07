/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.player;

public class MiniMax {

    static double minimax(int depth, int nodeIndex, Boolean maximizingPlayer, int[] values, double alpha, double beta) {
        // Terminating condition. i.e
        // leaf node is reached
        if (depth == 3)
            return values[nodeIndex];

        if (maximizingPlayer) {
            double best = Double.NEGATIVE_INFINITY;

            // Recur for left and
            // right children
            for (int i = 0; i < 2; ++i) {
                double val = minimax(depth + 1, nodeIndex * 2 + i,
                        false, values, alpha, beta);
                best = Math.max(best, val);
                alpha = Math.max(alpha, best);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        } else {
            double best = Double.POSITIVE_INFINITY;

            // Recur for left and
            // right children
            for (int i = 0; i < 2; ++i) {

                double val = minimax(depth + 1, nodeIndex * 2 + i,
                        true, values, alpha, beta);
                best = Math.min(best, val);
                beta = Math.min(beta, best);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
    }


}
