/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author admin
 */
public interface InterfaceGameOperations {
    
    static final int MAX_TOTAL_SCORE = 10;
    
    // Method that reads next word from the dictionary/file
    String GetNextWord();
    
    // Method that reads the data from client and checks for game conditions
    String EvaluateInput(String strClientInput);
}
