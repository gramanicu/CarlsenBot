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
     * Read data from xboard
     * @throws IOException In case the read fails
     * @return If the read should continue
     */
    public boolean receive() throws IOException {
        String xMessage = input.readLine();
        if (!xMessage.isEmpty()) {
            Command command = new Command(xMessage, true);
            return command.execute();
        }
        return true;
    }

}
