/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.communication;

import com.carlsenbot.main.GameManager;
import com.carlsenbot.pieces.PieceColor;
import com.carlsenbot.player.Player;

public abstract class Command {
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

        command = args[0];
        parameter = args[1];
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
        switch (command) {
            case "black":
                return changeColors(PieceColor.Black);
            case "white":
                return changeColors(PieceColor.White);
            case "force":
                return force();

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
        player.setColor(color);
        return true;
    }
}
