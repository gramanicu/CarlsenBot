/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

import com.carlsenbot.communication.Engine;
import com.carlsenbot.pieces.*;
import com.carlsenbot.player.Player;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.CheckSystem;
import com.carlsenbot.table.Table;

public class GameManager {
    private static GameManager instance = null;
    private boolean isWhiteTurn;
    private boolean forceMode;
    private int round;
    private Table table;
    private Engine commEngine;
    private Player player;
    private CheckSystem checkSystem;

    //region "Dangerous methods"
    /* ----------------------------------------
     * Attention when using the following methods, as they
     * can be a bit dangerous to use
     */

    /**
     * Assign a new table to the game table
     * @param table The new table
     */
    public void setTable(Table table) { this.table = table; }

    /**
     * Remove the game instance
     */
    public void removeInstance() {
        instance = null;
    }

    //endregion

    // Private, helper methods

    // Constructor, made private for singleton
    private GameManager() {
        round = 0;
        isWhiteTurn = true;
        commEngine = Engine.getInstance();
        forceMode = false;
        player = new Player();
        checkSystem = new CheckSystem();
    }

    // Get (and initialise if needed) the instance of the singleton
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // Getters
    public Table getTable() { return table; }
    public Player getPlayer() { return player; }
    public boolean isForceMode() { return forceMode; }
    public CheckSystem getCheckSystem() { return checkSystem; }
    public PieceColor getTurnColor() {
        if(isWhiteTurn) {
            return PieceColor.White;
        } else {
            return PieceColor.Black;
        }
    }

    // Setters
    public void enableForceMode() { forceMode = true; }
    public void disableForceMode() { forceMode = false; }
    public void setTurnColor(PieceColor color) {
        isWhiteTurn = color == PieceColor.White;
    }

    /**
     * Move a piece using the game manager
     * @param start The position of the piece to be moved
     * @param target The position where the piece should be moved
     * @return Whether or not the move was possible
     */
    public boolean move(Position start, Position target) {
        boolean moveWasDone = table.movePiece(start, target);

        // Count the moves only if they were not forced
        if(moveWasDone && !isForceMode()) {
            isWhiteTurn = !isWhiteTurn;

            // Every time black moved, we go to the next round
            if (!isWhiteTurn) {
                round++;
            }
        }
        return moveWasDone;
    }

    /**
     * Initialise a new game
     */
    public void initialize() {
        table = new Table();
        round = 0;
        isWhiteTurn = true;
        player = new Player();
        checkSystem = new CheckSystem();
        player.setColor(PieceColor.Black);
    }

    /**
     * Set the pieces to their normal positions
     */
    public void resetPieces() {
        Piece[][] pieces = new Piece[2][16];
        pieces[0] = GameUtils.initWhitePieces();
        pieces[1] = GameUtils.initBlackPieces();
        table = new Table(pieces);
    }

    /**
     * Start a game ( start the AI, wait for input, etc. )
     * This is used by main
     * @return The game has finished successfully
     */
    protected boolean startGame() {
        initialize();
        resetPieces();

        commEngine.listen();
        return false;
    }

    public void printTable() {
        System.out.println(table.printTable());
    }
}
