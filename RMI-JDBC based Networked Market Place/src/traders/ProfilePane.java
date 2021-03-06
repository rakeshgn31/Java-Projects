/*
 * Welcome to NetBeans...!!!
 */
package traders;

/**
 *
 * @author admin
 */
public class ProfilePane extends javax.swing.JPanel {

    ShoppingGUI mainGUI;
    
    /**
     * Creates new form ProfilePane
     * @param objMainGUI
     */
    public ProfilePane(ShoppingGUI objMainGUI) {
        initComponents();
        
        this.mainGUI = objMainGUI;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContainer = new javax.swing.JPanel();
        lblHello = new javax.swing.JLabel();
        pnlProfile = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfProfileName = new javax.swing.JTextField();
        tfPasswordStrength = new javax.swing.JTextField();
        pnlTradeStatistics = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfNumItemsBought = new javax.swing.JTextField();
        tfNumItemsSold = new javax.swing.JTextField();
        btnRefreshProfile = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblHello.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblHello.setForeground(new java.awt.Color(255, 0, 51));
        lblHello.setText(" Hello... Peep into your Profile...!!! ");

        pnlProfile.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "My Profile "));

        jLabel2.setText("Profile Name ");

        jLabel3.setText("Password Strength ");

        tfProfileName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfProfileName.setEnabled(false);

        tfPasswordStrength.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfPasswordStrength.setEnabled(false);

        javax.swing.GroupLayout pnlProfileLayout = new javax.swing.GroupLayout(pnlProfile);
        pnlProfile.setLayout(pnlProfileLayout);
        pnlProfileLayout.setHorizontalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlProfileLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(89, 89, 89)
                        .addComponent(tfProfileName, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlProfileLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(58, 58, 58)
                        .addComponent(tfPasswordStrength)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlProfileLayout.setVerticalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfProfileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tfPasswordStrength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnlTradeStatistics.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Trade Statistics "));

        jLabel4.setText("Number of Items Bought  ");

        jLabel5.setText("Number of Items Sold      ");

        tfNumItemsBought.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfNumItemsBought.setEnabled(false);

        tfNumItemsSold.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfNumItemsSold.setEnabled(false);

        javax.swing.GroupLayout pnlTradeStatisticsLayout = new javax.swing.GroupLayout(pnlTradeStatistics);
        pnlTradeStatistics.setLayout(pnlTradeStatisticsLayout);
        pnlTradeStatisticsLayout.setHorizontalGroup(
            pnlTradeStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTradeStatisticsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTradeStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(32, 32, 32)
                .addGroup(pnlTradeStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfNumItemsBought, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(tfNumItemsSold))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTradeStatisticsLayout.setVerticalGroup(
            pnlTradeStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTradeStatisticsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTradeStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfNumItemsBought, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlTradeStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfNumItemsSold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        btnRefreshProfile.setText("Refresh");
        btnRefreshProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContainerLayout = new javax.swing.GroupLayout(pnlContainer);
        pnlContainer.setLayout(pnlContainerLayout);
        pnlContainerLayout.setHorizontalGroup(
            pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContainerLayout.createSequentialGroup()
                        .addComponent(lblHello, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefreshProfile)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(pnlProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTradeStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlContainerLayout.setVerticalGroup(
            pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHello, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefreshProfile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTradeStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshProfileActionPerformed
        
        ProfileInformation objProfileInfo = null;
        
        // Invoke method on main GUI to make a remote call
        objProfileInfo = mainGUI.getProfileData();
        if(objProfileInfo != null) {
            // set the values in the profile pane
            this.tfProfileName.setText(objProfileInfo.getTraderName());
            this.tfPasswordStrength.setText(objProfileInfo.getPwdStrength());
            this.tfNumItemsBought.setText( Integer.toString(objProfileInfo.getNumItemsBought()) );   
            this.tfNumItemsSold.setText( Integer.toString(objProfileInfo.getNumItemsSold()) );
        }
    }//GEN-LAST:event_btnRefreshProfileActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefreshProfile;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblHello;
    private javax.swing.JPanel pnlContainer;
    private javax.swing.JPanel pnlProfile;
    private javax.swing.JPanel pnlTradeStatistics;
    private javax.swing.JTextField tfNumItemsBought;
    private javax.swing.JTextField tfNumItemsSold;
    private javax.swing.JTextField tfPasswordStrength;
    private javax.swing.JTextField tfProfileName;
    // End of variables declaration//GEN-END:variables
}
