/*
 * Welcome to NetBeans...!!!
 */
package traders;

import banking.Bank;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.net.MalformedURLException;
import market.IFMarket;

/**
 *
 * @author admin
 */
public class TraderClientMain {
    
     public static void main(String args[]) throws RemoteException, NotBoundException, MalformedURLException {

        // Start the registry service
        try {
            LocateRegistry.getRegistry(1099).list();
        } catch (RemoteException ex) {
            LocateRegistry.createRegistry(1099);
        }
        
        // Get the Market server reference
        IFMarket marketPlaceServer = (IFMarket) Naming.lookup("rmi://localhost/marketplace");
        
        // Get the bank server refernce and set it in the MarketImpl class
        Bank objBankClient = (Bank) Naming.lookup("Nordea");    
                
        if(marketPlaceServer != null && objBankClient != null) {
            // Create and display the form  
            java.awt.EventQueue.invokeLater(() -> {
                ShoppingGUI shoppingGUI = new ShoppingGUI();
                shoppingGUI.setVisible(true);
                shoppingGUI.Initialize(marketPlaceServer, objBankClient);   // Set the reference objects in the GUI class
            });
        } else {
            System.out.println("market object & bank object are null");
            System.exit(1);
        }
    }
}
