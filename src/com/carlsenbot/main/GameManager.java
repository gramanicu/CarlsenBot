/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

import com.carlsenbot.communication.Engine;
import com.carlsenbot.pieces.Piece;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.player.Player;
import com.carlsenbot.position.Move;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.Table;


public class GameManager {
    private static GameManager instance = null;
    private boolean forceMode;
    private Table table;
    private Engine commEngine;
    private Player player;
    private boolean botActive;
    private Move queuedMove;
    public boolean againstComputer;

    //region "Dangerous methods"
    /* ----------------------------------------
     * Attention when using the following methods, as they
     * can be a bit dangerous to use
     */

    /**
     * Assign a new table to the game table
     * @param table The new table
     */
    public void setTable(Table table) {
        this.table = table;
    }

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
        commEngine = Engine.getInstance();
        forceMode = false;
        player = new Player();
        botActive = false;
        queuedMove = null;
        againstComputer = false;
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
    public Engine getCommEngine() { return commEngine; }
    public PieceColor getTurnColor() {
        return table.getTurnColor();
    }

    // Setters
    public void enableForceMode() { forceMode = true; }
    public void disableForceMode() { forceMode = false; }
    public void setTurnColor(PieceColor color) {
        table.setTurnColor(color);
        checkBotAct();
    }
    public void activateBot() {
        botActive = true;

        if(queuedMove != null) {
            if (move(queuedMove.getStart(), queuedMove.getEnd())) {
                sendCommand("move " + queuedMove.getStart().toString() + queuedMove.getEnd().toString());
            }
        }
    }

    /**
     * Check if it's the bot's turn
     */
    public void checkBotAct() {
        if(table.getTurnColor() == player.getColor()) {
            player.doAMove();
        }
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
            resign();
        }

        return table.movePiece(start, target);
    }

    public boolean moveAndSend(Position start, Position target) {
        if(!botActive && againstComputer) {
            queuedMove = new Move(start, target, table.getPiece(start));
            return false;
        }

        // We assume that the bot gave a correct move and we send it
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
        table.setTurnColor(PieceColor.White);
        player = new Player();
        player.setColor(PieceColor.Black);
        botActive = false;
        queuedMove = null;
        againstComputer = false;
    }

    public void resign() {
        commEngine.sendResign();
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
