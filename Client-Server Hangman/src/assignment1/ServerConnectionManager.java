/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 * Manages connection and communication with server.
 * 
 * @author admin
 */
public class ServerConnectionManager {
        
    private Socket clientSocket;
    private final HangmanClient clientGUI;
    
    private int nServerPort;
    private String strHostName; 

    private ObjectInputStream inputObject;
    private ObjectOutputStream outputObject;

    public ServerConnectionManager(HangmanClient client) {
        strHostName = "";
        nServerPort = 0;
        
        this.clientGUI = client;
        this.clientSocket = null;
    }

    public void InitializeSettings(String strServer, int nPort) {
        strHostName = strServer;
        nServerPort = nPort;
    }
    
    /**
     * Establishes initial connection with the server using
     * the initialized host name and the port number.
     */
    public void EstablishConnection() {
        
         try {
            clientSocket = new Socket(strHostName, nServerPort);
            outputObject = new ObjectOutputStream(clientSocket.getOutputStream());
            outputObject.flush();
            inputObject = new ObjectInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Server Connection Failure", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Server I/O Failure", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        if(clientSocket != null)
        {
            // Enable the controls in the client GUI
            clientGUI.ConnectedToServer();
        }
    }

    /**
     * Call to communicate with the server and get the reply back and process
     * in case of starting a new game
     */
    public void StartNewGame() {
        try {
            // Send a new message to server
            outputObject.writeObject("Start Game");
            outputObject.flush();

            // Read the new word from the server
            String strServerReply = inputObject.readObject().toString();
            
            // Split the reply and set the new construct and attempts left
            String[] splitResult = strServerReply.split(" ");
            clientGUI.SetWordConstruct(splitResult[0]);
            clientGUI.SetAttemptsLeftValue(Integer.parseInt(splitResult[1]) );
            
        } catch(ClassNotFoundException | IOException ex) {
            clientGUI.SetStatus(ex.getMessage());
        }        
    }
    
    // Processes the reply from the server and updates the GUI accordingly
    void ProcessReplyFromServer(String strReply) {
        
        // Split the reply into array of words
        String[] arrWords = strReply.split(" ");
        
        // Process and set the appropriate status on the client
        if( strReply.contains("Congratulations") ) {
            clientGUI.SetStatus("Awesome... You won");
            clientGUI.SetWordConstruct(arrWords[1]);
            clientGUI.SetScoreValue(Integer.parseInt(arrWords[2]));
        } else if( strReply.contains("GameOver") ) {
            clientGUI.SetStatus("Sorry... You lose...:-(");
            clientGUI.SetScoreValue(Integer.parseInt(arrWords[1]));
        } else {
            clientGUI.SetWordConstruct(arrWords[0]);
            clientGUI.SetAttemptsLeftValue(Integer.parseInt(arrWords[1]));
        }
    }
    
    /**
     * Call to communicate with the server and get the reply back and process
     */
    void callServer(String strClientData)
    {
        try {
                // Write the output on to the output stream
                outputObject.writeObject(strClientData);
                outputObject.flush();

                // Read the reply from the server from the input stream
                String strServerReply = inputObject.readObject().toString();
                strServerReply = strServerReply.trim();
                ProcessReplyFromServer(strServerReply);
                
        } catch (ClassNotFoundException | IOException ex) {
            clientGUI.SetStatus(ex.getMessage());
        }
    }
}