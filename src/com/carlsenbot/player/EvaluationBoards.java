/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.player;

import com.carlsenbot.pieces.*;
import com.carlsenbot.table.Table;

public class EvaluationBoards {

    private static double[][] pawnEvalWhite = {
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0},
            {1.0, 1.0, 2.0, 3.0, 3.0, 2.0, 1.0, 1.0},
            {0.5, 0.5, 1.0, 2.5, 2.5, 1.0, 0.5, 0.5},
            {0.0, 0.0, 0.0, 2.0, 2.0, 0.0, 0.0, 0.0},
            {0.5, -0.5, -1.0, 0.0, 0.0, -1.0, -0.5, 0.5},
            {0.5, 1.0, 1.0, -2.0, -2.0, 1.0, 1.0, 0.5},
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
    };

    private static double[][] pawnEvalBlack = reverseBoard(pawnEvalWhite);

    private static double[][] knightEval = {
            {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0},
            {-4.0, -2.0, 0.0, 0.0, 0.0, 0.0, -2.0, -4.0},
            {-3.0, 0.0, 1.0, 1.5, 1.5, 1.0, 0.0, -3.0},
            {-3.0, 0.5, 1.5, 2.0, 2.0, 1.5, 0.5, -3.0},
            {-3.0, 0.0, 1.5, 2.0, 2.0, 1.5, 0.0, -3.0},
            {-3.0, 0.5, 1.0, 1.5, 1.5, 1.0, 0.5, -3.0},
            {-4.0, -2.0, 0.0, 0.5, 0.5, 0.0, -2.0, -4.0},
            {-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0}
    };

    private static double[][] bishopEvalWhite = {
            {-2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0},
            {-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0},
            {-1.0, 0.0, 0.5, 1.0, 1.0, 0.5, 0.0, -1.0},
            {-1.0, 0.5, 0.5, 1.0, 1.0, 0.5, 0.5, -1.0},
            {-1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, -1.0},
            {-1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1.0},
            {-1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5, -1.0},
            {-2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0}
    };

    private static double[][] bishopEvalBlack = reverseBoard(bishopEvalWhite);

    private static double[][] rookEvalWhite = {
            {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
            {0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5},
            {0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5},
            {0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5},
            {0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5},
            {0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5},
            {0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5},
            {0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.0, 0.0}
    };

    private static double[][] rookEvalBlack = reverseBoard(rookEvalWhite);

    private static double[][] queenEval = {
            {-2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0},
            {-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0},
            {-1.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -1.0},
            {-0.5, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -0.5},
            {0.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -0.5},
            {-1.0, 0.5, 0.5, 0.5, 0.5, 0.5, 0.0, -1.0},
            {-1.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, -1.0},
            {-2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0}
    };

    private static double[][] kingEvalWhite = {
            {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            {-3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0},
            {-2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0},
            {-1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0},
            {2.0, 2.0, 0.0, 0.0, 0.0, 0.0, 2.0, 2.0},
            {2.0, 3.0, 1.0, 0.0, 0.0, 1.0, 3.0, 2.0}
    };

    private static double[][] kingEvalBlack = reverseBoard(kingEvalWhite);

    public static double[][] reverseBoard(double[][] my_array) {
        int my_rows = my_array.length;
        int my_cols = my_array[0].length;
        double[][] array = new double[my_rows][my_cols];
        for (int i = my_rows - 1; i >= 0; i--) {
            for (int j = my_cols - 1; j >= 0; j--) {
                array[my_rows - 1 - i][my_cols - 1 - j] = my_array[i][j];
            }
        }
        return array;
    }

    public static double evaluateBoard(Table table) {
        double result = 0d;
        for (Piece[] whiteAndBlack : table.getPieces()) {
            for (Piece p : whiteAndBlack) {
                if (p != null) {
                    result += getPieceValue(p);
                }
            }
        }
        return table.getTurnColor() == PieceColor.White ? -result : result;
    }

    // double[][] kingEvalBlack = reverse

    public static double getPieceValue(Piece piece) {
        double value = piece.isWhite() ? piece.getValue() : -piece.getValue();

        int x = piece.getPosition().getCol();
        int y = piece.getPosition().getRow();

        if (piece instanceof King) {
            value += piece.isWhite() ? kingEvalWhite[y][x] : -kingEvalBlack[y][x];
        } else if (piece instanceof Queen) {
            value += piece.isWhite() ? queenEval[y][x] : -queenEval[y][x];
        } else if (piece instanceof Knight) {
            value += piece.isWhite() ? knightEval[y][x] : -knightEval[y][x];
        } else if (piece instanceof Bishop) {
            value += piece.isWhite() ? bishopEvalWhite[y][x] : -bishopEvalBlack[y][x];
        } else if (piece instanceof Rook) {
            value += piece.isWhite() ? rookEvalWhite[y][x] : -rookEvalBlack[y][x];
        } else if (piece instanceof Pawn) {
            value += piece.isWhite() ? pawnEvalWhite[y][x] : -pawnEvalBlack[y][x];
        }

        return value;
    }
}
