/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.main;

import com.carlsenbot.communication.Engine;
import com.carlsenbot.pieces.Pawn;
import com.carlsenbot.pieces.Piece;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.player.Player;
import com.carlsenbot.position.Move;
import com.carlsenbot.position.Position;
import com.carlsenbot.table.CheckSystem;
import com.carlsenbot.table.Table;

import java.util.ArrayList;


public class GameManager {
    private static GameManager instance = null;
    private boolean isWhiteTurn;
    private boolean forceMode;
    private Table table;
    private Engine commEngine;
    private Player player;
    private CheckSystem checkSystem;
    private boolean botActive;
    private ArrayList<Move> moveHistory;

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
        this.table.setAssignedGameManager(this);
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
        isWhiteTurn = true;
        commEngine = Engine.getInstance();
        forceMode = false;
        player = new Player();
        checkSystem = new CheckSystem();
        botActive = false;
        moveHistory = new ArrayList<>();
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
     * Check if a pawn can attack another pawn at this position using
     * "En Passant"
     * @param position The position of the pawn to be "en passanted"
     * @param myColor The color of the pawn that wants to capture
     * @return If the move is possible
     */
    public boolean canBeEnPassanted(Position position, PieceColor myColor) {
        Piece other = table.getPiece(position);
        Move lastMove = moveHistory.get(moveHistory.size() - 1);

        /**
         * To be able to do an "En Passant"
         * - the other piece must be a pawn
         * - the other pawn must be of a different color
         * - the other pawn must be the last moved piece
         * - he must have done his first move, a double move
         */
        if(other instanceof Pawn &&
                myColor != other.getColor() &&
                other == lastMove.getPiece() &&
                lastMove.getDistance() == 2d) {
            return true;
        }
        return false;
    }

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
            resign();
        }

        boolean moveWasDone = table.movePiece(start, target);

        // Count the moves only if they were not forced
        if(moveWasDone && !isForceMode()) {
            moveHistory.add(new Move(start, target, table.getPiece(target)));
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
        table.setAssignedGameManager(this);
        isWhiteTurn = true;
        player = new Player();
        checkSystem = new CheckSystem();
        player.setColor(PieceColor.Black);
        botActive = false;
    }

    public void resign() {
        commEngine.sendResign();
        printMoveHistory();
    }

    /**
     * Set the pieces to their normal positions
     */
    public void resetPieces() {
        Piece[][] pieces = new Piece[2][16];
        pieces[0] = GameUtils.initWhitePieces();
        pieces[1] = GameUtils.initBlackPieces();
        table = new Table(pieces);
        table.setAssignedGameManager(this);
    }

    public void printTable() {
        System.out.println(table.printTable());
    }

    public void printMoveHistory() {
        for (Move move : moveHistory) {
            System.out.println(move);
        }
    }
}
