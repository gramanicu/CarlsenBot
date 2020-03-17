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
    private Table table;
    private Engine commEngine;
    private Player player;
    private CheckSystem checkSystem;
    private boolean botActive;

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
        isWhiteTurn = true;
        commEngine = Engine.getInstance();
        forceMode = false;
        player = new Player();
        checkSystem = new CheckSystem();
        botActive = false;
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
    public Engine getCommEngine() { return commEngine; }
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
        checkBotAct();
    }
    public void activateBot() { botActive = true; }


    /**
     * Check if it's the bot's turn
     */
    public void checkBotAct() {
        if(getTurnColor() == player.getColor()) {
            player.doAMove();
        }
    }

    /**
     * Change the turn
     */
    public void switchTurn() {
        isWhiteTurn = !isWhiteTurn;
        activateBot();
     }

    /**
     * Send a command to the XBoard
     * @param string The command to be sent
     */
    public void sendCommand(String string) {
        commEngine.sendCommand(string);
    }

    /**
     * Move a piece using the game manager
     * @param start The position of the piece to be moved
     * @param target The position where the piece should be moved
     * @return Whether or not the move was possible
     */
    public boolean move(Position start, Position target) {
        // If the move would promote a pawn
        if(table.getPiece(start).getName().equals("Pawn") && (target.getRow() == 0 || target.getRow() == 8)) {
            // If the pawn promoted
            commEngine.sendResign();
        }

        boolean moveWasDone = table.movePiece(start, target);

        // Count the moves only if they were not forced
        if(moveWasDone && !isForceMode()) {
            switchTurn();
        }
        return moveWasDone;
    }

    public boolean moveAndSend(Position start, Position target) {
        if(!botActive) { return false; }
        // If the piece could be moved, send the command to the server
        if (move(start, target)) {
            sendCommand("move " + start.toString() + target.toString());
            return true;
        }
        return false;
    }

    public void newGame() {
        initialize();
        resetPieces();
        commEngine.listen();
    }

    /**
     * Initialise a new game
     */
    public void initialize() {
        table = new Table();
        isWhiteTurn = true;
        player = new Player();
        checkSystem = new CheckSystem();
        player.setColor(PieceColor.Black);
        botActive = false;
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

    public void printTable() {
        System.out.println(table.printTable());
    }
}
