/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.player;

import com.carlsenbot.pieces.Piece;
import com.carlsenbot.table.Table;

public class EvaluationBoards {

    double[][] pawnEvalWhite = {
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0},
            {1.0, 1.0, 2.0, 3.0, 3.0, 2.0, 1.0, 1.0},
            {0.5, 0.5, 1.0, 2.5, 2.5, 1.0, 0.5, 0.5},
            {0.0, 0.0, 0.0, 2.0, 2.0, 0.0, 0.0, 0.0},
            {0.5, -0.5, -1.0, 0.0, 0.0, -1.0, -0.5, 0.5},
            {0.5, 1.0, 1.0, -2.0, -2.0, 1.0, 1.0, 0.5},
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
    };

    double[][] pawnEvalBlack = reverseBoard(pawnEvalWhite);

    double[][] knightEval = {
            {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0},
            {-4.0, -2.0, 0.0, 0.0, 0.0, 0.0, -2.0, -4.0},
            {-3.0, 0.0, 1.0, 1.5, 1.5, 1.0, 0.0, -3.0},
            {-3.0, 0.5, 1.5, 2.0, 2.0, 1.5, 0.5, -3.0},
            {-3.0, 0.0, 1.5, 2.0, 2.0, 1.5, 0.0, -3.0},
            {-3.0, 0.5, 1.0, 1.5, 1.5, 1.0, 0.5, -3.0},
            {-4.0, -2.0, 0.0, 0.5, 0.5, 0.0, -2.0, -4.0},
            {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0}
    };
    double[][] bishopEvalWhite = {
            {-2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0},
            {-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0},
            {-1.0, 0.0, 0.5, 1.0, 1.0, 0.5, 0.0, -1.0},
            {-1.0, 0.5, 0.5, 1.0, 1.0, 0.5, 0.5, -1.0},
            {-1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, -1.0},
            {-1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1.0},
            {-1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5, -1.0},
            {-2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0}
    };

    double[][] bishopEvalBlack = reverseBoard(bishopEvalWhite);

    double[][] rookEvalWhite = {
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5},
            {0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5},
            {0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5},
            {0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5},
            {0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5},
            {0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5},
            {0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.0, 0.0}
    };

    double[][] rookEvalBlack = reverseBoard(rookEvalWhite);

    double[][] queenEval = {
            {-2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0},
            {-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0},
            {-1.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -1.0},
            {-0.5, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -0.5},
            {0.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -0.5},
            {-1.0, 0.5, 0.5, 0.5, 0.5, 0.5, 0.0, -1.0},
            {-1.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, -1.0},
            {-2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0}
    };

    double[][] kingEvalWhite = {
            {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            {-2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0},
            {-1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0},
            {2.0, 2.0, 0.0, 0.0, 0.0, 0.0, 2.0, 2.0},
            {2.0, 3.0, 1.0, 0.0, 0.0, 1.0, 3.0, 2.0}
    };

    double[][] kingEvalBlack = reverseBoard(kingEvalWhite);

    public static double[][] reverseBoard(double[][] my_array) {
        int my_rows = my_array.length;
        int my_cols = my_array[0].length;
        double array[][] = new double[my_rows][my_cols];
        for (int i = my_rows - 1; i >= 0; i--) {
            for (int j = my_cols - 1; j >= 0; j--) {
                array[my_rows - 1 - i][my_cols - 1 - j] = my_array[i][j];
            }
        }
        return array;
    }

    public static double evaluateBoard(Table table) {
        double result = 0d;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

            }
        }
        return result;
    }

    // double[][] kingEvalBlack = reverse

    double getPieceValue(Piece piece) {
       return piece.getValue();
    }
}