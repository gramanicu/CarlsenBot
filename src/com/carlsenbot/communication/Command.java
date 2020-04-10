/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.communication;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.main.GameUtils;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.position.Position;

// Commands are received from the xboard
public class Command {
    private String command;
    private String parameter;
    private GameManager gameManager;

    /**
     * Creates a command object
     * @param command The command string
     */
    public Command(String command) {
        String[] args = command.split(" ");
        this.command = args[0];
        this.parameter = "";
        if(args.length > 1) {
            parameter = args[1];
        }

        gameManager = GameManager.getInstance();
    }

    // Getters
    public String getCommand() { return command; }
    public String getParameter() { return parameter; }

    // Setters
    public void setCommand(String command) { this.command = command; }
    public void setParameter(String parameter) { this.parameter = parameter; }

    /**
     * Execute the command
     * @return If the command succeeded
     */
    public boolean execute() {
        gameManager.getCommEngine().sendDebug("Received: " + command + " " + parameter);
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
            case "usermove":
                return move();
            case "go":
                return go();
            case "stop":
                gameManager.printTable();
                return false;
            default:
                return true;
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
        gameManager.getPlayer().setColor(GameUtils.otherColor(color));
        gameManager.setTurnColor(color);
        return true;
    }

    private boolean newGame() {
        gameManager.disableForceMode();
        gameManager.newGame();
        return true;
    }

    private boolean xboard() {
        // Could send back setup parameters
        gameManager.getCommEngine().sendSettings();
        return true;
    }

    // When the bot plays against another bot, it should wait for the go command
    // to start
    private boolean computer() {
        gameManager.againstComputer = true;
        return true;
    }

    private boolean go() {
//        gameManager.disableForceMode();
        // Start thinking ?
        gameManager.getPlayer().setColor(gameManager.getTurnColor());
        gameManager.activateBot();
        gameManager.getPlayer().doAMove();
//        gameManager.printTable();
        return true;
    }

    private boolean quit() {
        System.exit(0);
        return true;
    }

    private boolean move() {
        String source = parameter.substring(0,2);
        String target = parameter.substring(2,4);
        char promotion;
        if(parameter.length() > 4) {
             promotion = parameter.charAt(4);
        }

        if(gameManager.move(new Position(source), new Position(target))) {
            gameManager.checkBotAct();
        } else {
            GameManager.getInstance().getCommEngine().sendInvalidMove(source + target);
        }

        return true;
    }
}
