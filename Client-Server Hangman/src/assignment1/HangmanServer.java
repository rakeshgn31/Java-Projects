/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author admin
 */
public class HangmanServer {

    private static final int PORT = 7865;

    /**
     *
     * @param args No command line arguments are used.
     */
    public static void main(String[] args) {
        // Server Socket that listens on the specified port
        ServerSocket hangmanServerSocket;
        boolean bListening = true;
        try {
            hangmanServerSocket = new ServerSocket(PORT);

            while (bListening) {
                Socket clientSocket;
                clientSocket = hangmanServerSocket.accept();
                System.out.println("Connected to client..!!!");
                Thread clientThread = new Thread(new ClientManager(clientSocket));
                clientThread.start();
            }

            // Close the server socket after listening is complete
            hangmanServerSocket.close();

        } catch (IOException ex) {
            String strError = String.format("Unable to listen on port : %d. Error message : %s.", PORT, ex.getMessage());
            System.err.println(strError);
            System.exit(1);
        }
    }
}
