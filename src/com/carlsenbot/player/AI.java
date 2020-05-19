/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.player;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.position.Move;
import com.carlsenbot.table.Table;

public class AI {
    private Player assignedPlayer;

    public AI(Player player) {
        this.assignedPlayer = player;
    }

    /**
     * Make the AI compute the next move
     * @return The next move
     */
    public String think() {
        GameManager gameManager = GameManager.getInstance();
        Table minimaxTable = new Table(gameManager.getTable());

        int depth = 3;

        Move move = MiniMax.minimax(depth, true, minimaxTable);
        return move.getStart().toString() + move.getEnd().toString();
    }
}
