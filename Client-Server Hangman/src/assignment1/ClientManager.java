/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Socket;
import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author admin
 */
public class ClientManager implements InterfaceGameOperations, Runnable {

    private final Socket clientSocket;
    private String strWord;
    private int nAttemptsLeft;
    private int nTotalScore;
    private String strCurrentWordConstruct;
    private final ArrayList<String> arrWords;
    
    private ObjectInputStream inObject;
    private ObjectOutputStream outObject;
    
    public ClientManager(Socket clientSocket) {

        this.strWord = "";
        this.nTotalScore = 0;
        this.nAttemptsLeft = 5;
        this.clientSocket = clientSocket;
        this.strCurrentWordConstruct = "";
        
        this.inObject = null;
        this.outObject = null;
        
        arrWords = new ArrayList<>();
        ReadFile();
        
        GetIOStreams();
    }

    private void ReadFile() {
        
        try {
            FileReader fileReader = new FileReader("C:\\Users\\admin\\Documents\\NetBeansProjects\\Hangman Assignment One\\words.txt");
            BufferedReader bufRead = new BufferedReader(fileReader);
            String myLine;

            while ( (myLine = bufRead.readLine()) != null)
            {    
                arrWords.add(myLine.trim());
            }
        } catch(IOException ex) {
            System.out.println("Exception while reading file. Error : " + ex.getMessage());
        }
    }
    
    private void GetIOStreams() {
        try {
            outObject = new ObjectOutputStream(clientSocket.getOutputStream());
            inObject = new ObjectInputStream(clientSocket.getInputStream());
        } catch(IOException ex) {
            System.out.println("Exception while retrieving the streams. Error : " + ex.getMessage());
        }
    }
    
    private void ConstructInitialWord() {   

        String strCurrentView = strWord.replaceAll(".", "-");
        strCurrentWordConstruct = strCurrentView;
    }
    
    private void ConstructWord(String strChar) {
        
        // Retrieve the list of indices where this character occurs
        ArrayList<Integer> listOfIndices = new ArrayList<>();
        
        int nIndex = strWord.indexOf(strChar);
        while (nIndex >= 0) 
        {
            listOfIndices.add(nIndex);
            nIndex = strWord.indexOf(strChar, nIndex + 1);
        }
        
        // Construct the new view of the word
        char[] tempCharArray = strCurrentWordConstruct.toCharArray();   // Array of characters of current word format
        char ch = strChar.toCharArray()[0];     // Character to be inserted
        listOfIndices.stream().forEach((index) -> {
            tempCharArray[index] = ch;
        });
        
        // Final string with the user guessed letter replaced
        strCurrentWordConstruct = new String(tempCharArray);
    }
    
    /*
    *  Interface method that gets the next word for a new game
    */
    @Override
    public String GetNextWord() {
        
        String strNextWord;
        
        Random randomIndexGen = new Random();
        int nRandomIndex = randomIndexGen.nextInt(arrWords.size());
        strNextWord = arrWords.get(nRandomIndex);
        System.out.println("Next Word is : " + strNextWord);
        
        return strNextWord;
    }
    
    @Override
    public String EvaluateInput(String strClientInput) {
        
        // Builds the required string to be returned as output
        StringBuilder sbReturnString = new StringBuilder();
        
        if(strClientInput.equalsIgnoreCase("Start Game"))
        {
            // Start a fresh game with a new word
            strWord = GetNextWord();
            ConstructInitialWord();     // Replaces all the letters with dashes
            
            // Calculate Attempts Left based on the length of the word
            nAttemptsLeft = (int) Math.floor( (strWord.length()/2) );   // Reset the attempts left
            nTotalScore = 0;    // Reset the score variable
            
            // Ex.: -------- 4
            sbReturnString.append(strCurrentWordConstruct);
            sbReturnString.append(" ");
            sbReturnString.append(nAttemptsLeft);
        } 
        else // Continue the previous game
        {
            // Check if it is a Single character/Word
            if(strClientInput.length() == 1) {
                if(strWord.contains(strClientInput)) {
                    /* If the word contains the letter guessed by the user,
                    * replace the letter instances and check if the word is complete or not */
                    ConstructWord(strClientInput);
                    if(strCurrentWordConstruct.equalsIgnoreCase(strWord)) {
                        // Ex.: Congratulations xxxxxxxx <SCORE>
                        nTotalScore++;
                        sbReturnString.append("Congratulations").append(" ");
                        sbReturnString.append(strCurrentWordConstruct).append(" ");
                        sbReturnString.append(nTotalScore);
                    } else {
                        // Ex.:  --q--x 2
                        sbReturnString.append(strCurrentWordConstruct).append(" ");
                        sbReturnString.append(nAttemptsLeft);
                    }
                }
                else
                {
                    // Decrement the attempts left
                    nAttemptsLeft--;
                    
                    if(nAttemptsLeft <= 0) {
                        nAttemptsLeft = 0;
                        nTotalScore--;
                        if(nTotalScore < 0) { nTotalScore = 0; }

                        sbReturnString.append("GameOver").append(" ");
                        sbReturnString.append(nTotalScore);
                    } else {
                        // Ex.: xxxxxxxx <attempts_left>
                        sbReturnString.append(strCurrentWordConstruct).append(" ");
                        sbReturnString.append(nAttemptsLeft);
                    }     
                }
            } else {
                if(strClientInput.equalsIgnoreCase(strWord)) {
                    // Ex.: Congratulations xxxxxxxx <SCORE>
                    nTotalScore++;
                    sbReturnString.append("Congratulations").append(" ");
                    sbReturnString.append(strWord).append(" ");
                    sbReturnString.append(nTotalScore);
                } else {
                    // Decrement the attempts left
                    nAttemptsLeft--;
                    
                    if(nAttemptsLeft <= 0) {
                        nAttemptsLeft = 0;
                        nTotalScore--;
                        if(nTotalScore < 0) { nTotalScore = 0; }

                        sbReturnString.append("GameOver").append(" ");
                        sbReturnString.append(nTotalScore);
                    } else {
                        // Ex.: xxxxxxxx <attempts_left>
                        sbReturnString.append(strCurrentWordConstruct).append(" ");
                        sbReturnString.append(nAttemptsLeft);
                    }
                }
            }
        }
        
        return sbReturnString.toString(); 
    }

    /* Thread Run method 
    *   This method is used for communication with the client
    */
    @Override
     public void run() {
         
         boolean bRead = true;
        try {
            
            do {
                String strClientData = inObject.readObject().toString();
                
                if(!"".equals(strClientData))
                {
                    // Evaluate the Input from client and write resulting output onto output stream                    
                    String strServerReply = EvaluateInput(strClientData);
                    
                    outObject.writeObject(strServerReply);
                    outObject.flush();
                }
            } while(bRead);
        } catch ( ClassNotFoundException | IOException ex)
        {
            System.out.println("Exception in run(). Error : " + ex.getMessage());
        }
    }
}
