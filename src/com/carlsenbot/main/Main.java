package com.carlsenbot.main;

import com.carlsenbot.position.Position;
import com.carlsenbot.table.BeginningTable;

public class Main {
    public static void main(String[] args) {
        BeginningTable beg = new BeginningTable();
        for (int i = 0; i < beg.getWhitePieces().size(); i++) {
            System.out.println(beg.getWhitePieces().get(i).getName() + " " +
                    beg.getWhitePieces().get(i).getPosition());
            System.out.println(beg.getBlackPieces().get(i).getName() + " " +
                    beg.getBlackPieces().get(i).getPosition());
            System.out.println();
        }
    }
}
