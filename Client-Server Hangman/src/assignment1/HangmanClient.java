/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author admin
 */
public class HangmanClient extends javax.swing.JFrame {

    // Socket to connect to the server
    private String strHostName;
    private int nServerPortNumber;
    private final ServerConnectionManager connManager;
    
    public HangmanClient() {
        initComponents();
       
        this.strHostName = "localhost";
        this.nServerPortNumber = 0;
        
        this.connManager = new ServerConnectionManager(this);
        
        EnableComponents(this.pnlServerConnection, true);
        EnableComponents(this.pnlGameInfo, false);
    }

    // Method to initialize the form
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlServerConnection = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfHostName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfServerPortNumber = new javax.swing.JTextField();
        btnConnectToServer = new javax.swing.JButton();
        pnlGameInfo = new javax.swing.JPanel();
        lblAttemptsLeft = new javax.swing.JLabel();
        lblAttemptsLeftValue = new javax.swing.JLabel();
        lblTotalScore = new javax.swing.JLabel();
        lblTotalScoreValue = new javax.swing.JLabel();
        lblUserChoice = new javax.swing.JLabel();
        lblWordConstruct = new javax.swing.JLabel();
        tfUserGuess = new javax.swing.JTextField();
        tfWordConstruct = new javax.swing.JTextField();
        lblGameStatus = new javax.swing.JLabel();
        tfStatusMessage = new javax.swing.JTextField();
        btnStartGame = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        btnResetText = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hangman Game - Player");
        setName("MainFrame"); // NOI18N
        setType(java.awt.Window.Type.UTILITY);

        pnlServerConnection.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Server Connection"));

        jLabel1.setText("Host Name:");

        jLabel2.setText("Port Number:");

        tfServerPortNumber.setToolTipText("");

        btnConnectToServer.setText("Connect");
        btnConnectToServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectToServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlServerConnectionLayout = new javax.swing.GroupLayout(pnlServerConnection);
        pnlServerConnection.setLayout(pnlServerConnectionLayout);
        pnlServerConnectionLayout.setHorizontalGroup(
            pnlServerConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlServerConnectionLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfHostName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfServerPortNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConnectToServer, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlServerConnectionLayout.setVerticalGroup(
            pnlServerConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlServerConnectionLayout.createSequentialGroup()
                .addGroup(pnlServerConnectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfHostName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(tfServerPortNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnectToServer))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        pnlGameInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Game Information"));

        lblAttemptsLeft.setText("Attempts Left : ");

        lblAttemptsLeftValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAttemptsLeftValue.setText("10");

        lblTotalScore.setText("Total Score : ");

        lblTotalScoreValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalScoreValue.setText("10");

        lblUserChoice.setText("Enter Letter (or) Word         ");

        lblWordConstruct.setText("Guessed Word Construct     ");

        tfUserGuess.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tfWordConstruct.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblGameStatus.setText("Status Message");

        tfStatusMessage.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnStartGame.setText("Start Game");
        btnStartGame.setToolTipText("Starts a new game");
        btnStartGame.setAlignmentX(0.3F);
        btnStartGame.setAlignmentY(0.3F);
        btnStartGame.setHideActionText(true);
        btnStartGame.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartGameActionPerformed(evt);
            }
        });

        btnSend.setText("Send");
        btnSend.setToolTipText("Sends the entered letter/word to server");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnResetText.setText("Reset Text");
        btnResetText.setToolTipText("Resets the text entered");
        btnResetText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGameInfoLayout = new javax.swing.GroupLayout(pnlGameInfo);
        pnlGameInfo.setLayout(pnlGameInfoLayout);
        pnlGameInfoLayout.setHorizontalGroup(
            pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameInfoLayout.createSequentialGroup()
                .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlGameInfoLayout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(lblAttemptsLeft)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblAttemptsLeftValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(lblTotalScore)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTotalScoreValue, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlGameInfoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblUserChoice)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfUserGuess, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlGameInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblWordConstruct)
                            .addComponent(lblGameStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfStatusMessage)
                            .addComponent(tfWordConstruct))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGameInfoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGameInfoLayout.createSequentialGroup()
                                .addComponent(btnStartGame)
                                .addGap(36, 36, 36))
                            .addGroup(pnlGameInfoLayout.createSequentialGroup()
                                .addComponent(btnResetText, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(pnlGameInfoLayout.createSequentialGroup()
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlGameInfoLayout.setVerticalGroup(
            pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAttemptsLeft)
                    .addComponent(lblAttemptsLeftValue)
                    .addComponent(lblTotalScore)
                    .addComponent(lblTotalScoreValue)
                    .addComponent(btnStartGame))
                .addGap(25, 25, 25)
                .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserChoice)
                    .addComponent(tfUserGuess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addGap(18, 18, 18)
                .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfWordConstruct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWordConstruct)
                    .addComponent(btnResetText))
                .addGap(18, 18, 18)
                .addGroup(pnlGameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfStatusMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGameStatus))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlServerConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlGameInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlServerConnection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlGameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        try {
            this.tfStatusMessage.setText("");
            String strClientData = this.tfUserGuess.getText().trim();
            if(!"".equals(strClientData)) {
                Thread callServerThread = new Thread( () -> { connManager.callServer(strClientData); } );
                callServerThread.start();
            } else {
               this.tfStatusMessage.setText("Enter a valid value (letter/word)"); 
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Server Connection Failure", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartGameActionPerformed
        try {
            // Reset all the values
            this.tfStatusMessage.setText("");
            this.tfUserGuess.setText("");
            this.tfWordConstruct.setText("");

            this.lblAttemptsLeftValue.setText("0");
            this.lblTotalScoreValue.setText("0");

            // Communicate to server
            Thread startGameThread = new Thread( () -> { connManager.StartNewGame(); } );
            startGameThread.start();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Server Connection Failure", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }//GEN-LAST:event_btnStartGameActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnResetTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTextActionPerformed
        tfUserGuess.setText("");
    }//GEN-LAST:event_btnResetTextActionPerformed

    private void btnConnectToServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectToServerActionPerformed
        try {
            strHostName = tfHostName.getText().trim();
            nServerPortNumber = Integer.parseInt(tfServerPortNumber.getText().trim());
            connManager.InitializeSettings(strHostName, nServerPortNumber);
            
            // Start the connection in a new thread
            Thread connThread = new Thread( () -> { connManager.EstablishConnection(); } );
            connThread.start();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Server Connection Failure", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }//GEN-LAST:event_btnConnectToServerActionPerformed

    // Enables of disables the children of the entire parent container
    public final void EnableComponents(Container container, boolean bEnable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(bEnable);
            if (component instanceof Container) {
                EnableComponents((Container)component, bEnable);
            }
        }
    }
    
    /**
     * Callback method to be executed on the main thread that signals the connected state.
     * @param strStatus
     */
    public void SetStatus(String strStatus) {
        SwingUtilities.invokeLater(() -> {
            this.tfStatusMessage.setText(strStatus);
        });
    }
    
    /**
     * Callback method to be executed on the main thread when a new game is started.
     * @param strNewWordConstruct
     */
    public void SetWordConstruct(String strNewWordConstruct) {
        SwingUtilities.invokeLater(() -> {
            this.tfWordConstruct.setText(strNewWordConstruct);
        });
    }
    
    /**
     * Callback method to be executed on the main thread when a new game is started.
     * @param nAttemptsLeft
     */
    public void SetAttemptsLeftValue(int nAttemptsLeft) {
        SwingUtilities.invokeLater(() -> {
            this.lblAttemptsLeftValue.setText(Integer.toString(nAttemptsLeft));
        });
    }
    
    /**
     * Callback method to be executed on the main thread when a new game is started.
     * @param nScore
     */
    public void SetScoreValue(int nScore) {
        SwingUtilities.invokeLater(() -> {
            this.lblTotalScoreValue.setText(Integer.toString(nScore));
        });
    }

    /**
     * Callback method to be executed on the main thread that signals the connected state.
     */
    public void ConnectedToServer() {
        SwingUtilities.invokeLater(() -> {
            EnableComponents(this.pnlServerConnection, false);
            EnableComponents(this.pnlGameInfo, true);
        });
    }
    
    // No Command line arguments are used
    public static void main(String args[]) {
        
        // Set the Nimbus look and feel
        /* <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html     */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HangmanClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HangmanClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HangmanClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HangmanClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new HangmanClient().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnConnectToServer;
    private javax.swing.JButton btnResetText;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblAttemptsLeft;
    private javax.swing.JLabel lblAttemptsLeftValue;
    private javax.swing.JLabel lblGameStatus;
    private javax.swing.JLabel lblTotalScore;
    private javax.swing.JLabel lblTotalScoreValue;
    private javax.swing.JLabel lblUserChoice;
    private javax.swing.JLabel lblWordConstruct;
    private javax.swing.JPanel pnlGameInfo;
    private javax.swing.JPanel pnlServerConnection;
    private javax.swing.JTextField tfHostName;
    private javax.swing.JTextField tfServerPortNumber;
    private javax.swing.JTextField tfStatusMessage;
    private javax.swing.JTextField tfUserGuess;
    private javax.swing.JTextField tfWordConstruct;
    // End of variables declaration//GEN-END:variables
}