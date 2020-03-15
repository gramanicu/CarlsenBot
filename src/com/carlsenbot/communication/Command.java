/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.communication;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.main.GameUtils;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.player.Player;
import com.carlsenbot.position.Position;

public class Command {
    private boolean received;
    private String command;
    private String parameter;
    private GameManager gameManager;
    private Player player;

    /**
     * Creates a command object
     * @param command The command string
     * @param wasReceived If the command was received from xboard or not
     */
    public Command(String command, boolean wasReceived) {
        String[] args = command.split(" ");
        this.command = args[0];
        if(args.length > 1) {
            parameter = args[1];
        }
        received = wasReceived;

        gameManager = GameManager.getInstance();
        player = gameManager.getPlayer();
    }

    // Getters
    public boolean isReceived() { return received; }
    public String getCommand() { return command; }
    public String getParameter() { return parameter; }

    // Setters
    public void setReceived(boolean received) { this.received = received; }
    public void setCommand(String command) { this.command = command; }
    public void setParameter(String parameter) { this.parameter = parameter; }

    /**
     * Execute the command
     * @return If the command succeeded
     */
    public boolean execute() {
        if(received) {
            System.err.println("Received: " + command);
            switch (command) {
                case "black":
                    return changeColors(PieceColor.Black);
                case "white":
                    return changeColors(PieceColor.White);
                case "force":
                    return force();
                case "xboard":
                    return xboard();
                case "new":
                    return newGame();
                case "quit":
                    return quit();
                case "move":
                    return move();
                case "go":
                    return go();
                case "stop":
                    return false;
                default:
                    return true;
            }
        } else {
            System.err.println("Sent: " + command);
            // Send from the engine to XBoard
            switch (command) {
                case "resign":
                    return resign();
                default:
                    return true;
            }
        }
    }

    /**
     * The "force" command
     */
    private boolean force() {
        gameManager.enableForceMode();
        return true;
    }

    /**
     * The "white" and "black" commands
     * @param color The specific color
     */
    private boolean changeColors(PieceColor color) {
        player.setColor(GameUtils.otherColor(color));
        gameManager.setTurnColor(color);
        return true;
    }

    private boolean newGame() {
        gameManager.initialize();
        gameManager.resetPieces();
        player.setColor(PieceColor.Black);
        // Reset clocks, etc.
        return true;
    }

    private boolean xboard() {
        // Could send back setup parameters
        return true;
    }

    private boolean go() {
//        gameManager.disableForceMode();
        // Start thinking ?
        gameManager.getPlayer().setColor(gameManager.getTurnColor());
        gameManager.getPlayer().doAMove();
        gameManager.printTable();
        return true;
    }

    private boolean quit() {
        System.exit(0);
        return true;
    }

    private boolean resign() {
        return true;
    }

    private boolean move() {
        String source = parameter.substring(0,2);
        String target = parameter.substring(2,4);
        char promotion;
        if(parameter.length() > 4) {
             promotion = parameter.charAt(4);
        }

        return gameManager.move(new Position(source), new Position(target));
    }

}
