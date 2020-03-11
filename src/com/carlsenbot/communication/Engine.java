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
                // If the program should stop (because of a command)
                if(receive()) {
                    return;
                } else {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(true);
    }

    /**
     * Read data from xboard
     * @throws IOException In case the read fails
     */
    public boolean receive() throws IOException {
        String command = input.readLine();
        if (!command.isEmpty()) {
            // Interpret command
            return false;
        }

        return true;
    }

}
