/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

/*
 * © 2020 Grama Nicolae, Ioniță Radu , Mosessohn Vlad, 322CA
 */

package com.carlsenbot.communication;

import java.io.*;

public class Engine {
    private static Engine instance = null;
    private BufferedReader input;
    private BufferedWriter output;

    // Constructor, made private for singleton
    private Engine() {
        input = new BufferedReader(new InputStreamReader(System.in));
        output = new BufferedWriter(new OutputStreamWriter(System.out));
        receive();
    }

    // Get (and initialise if needed) the instance of the singleton
    public static Engine getInstance() {
        if (instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    public void listen() {
        receive();
    }

    public void receive() {
        do {
            try {
                String command = input.readLine();
                if(!command.isEmpty()) {
                } else {
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(true);
    }

}
