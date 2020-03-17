/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.communication;

import java.io.*;

public class Engine {
    private static Engine instance = null;
    private BufferedReader input;
    private BufferedWriter output;
    private Command lastReceived;

    private String[] settings = new String[] {
            "usermove=1",
            "sigint=0",
            "reuse=1",
            "myname=\"CarlsenBot\"",
            "pause=0",
            "debug=1",
            "time=0"
    };

    // Constructor, made private for singleton
    private Engine() {
        input = new BufferedReader(new InputStreamReader(System.in));
        output = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    // Get (and initialise if needed) the instance of the singleton
    public static Engine getInstance() {
        if (instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    public void sendSettings() {
        StringBuilder builder = new StringBuilder();
        builder.append("feature");
        for(String s : settings) {
            builder.append(" ");
            builder.append(s);
        }
        sendCommand(builder.toString());
    }

    public void sendResign() {
        sendCommand("resign");
    }

    public void sendInvalidMove(String move) {
        sendCommand("Illegal move: " + move);
    }

    /**
     * Listen to input from xboard
     */
    public void listen() {
        do {
            try {
                // Stop if the receive returns true
                if(!receive()) {
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(true);
    }

    /**
     * Send command (and a debug message)
     * @param command The command sent
     */
    public void sendCommand(String command) {
        sendDebug("Sent " + command);
        System.out.println(command);
    }

    /**
     * Send a debug message
     * @param debugMessage The debug message to be sent
     */
    public void sendDebug(String debugMessage) {
        System.out.println("#" + debugMessage);
    }

    /**
     * Read data from xboard
     * @throws IOException In case the read fails
     * @return If the read should continue
     */
    public boolean receive() throws IOException {
        String xMessage = input.readLine();
        if (!xMessage.isEmpty()) {
            Command command = new Command(xMessage);
            return command.execute();
        }
        return true;
    }

}
