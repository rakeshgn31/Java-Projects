/*
 * Welcome to NetBeans...!!!
 */

package market;

import bank.Bank;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author admin
 */
public class MarketServerMain {
    
    private static final int REGISTRY_PORT_NUMBER = 1099;

    public static void main(String[] args) {
        try {
            
            try {
                LocateRegistry.getRegistry(REGISTRY_PORT_NUMBER).list();
            } catch (RemoteException ex) {
                LocateRegistry.createRegistry(REGISTRY_PORT_NUMBER);
            }
            
            // Register the server with the naming service
            Naming.rebind("rmi://localhost/marketplace", new MarketImpl("derby", "Market"));
            
            // Get the bank server refernce and set it in the MarketImpl class
            Bank objBank = (Bank) Naming.lookup("Nordea");
            if(objBank != null) {
                MarketImpl.objBank = objBank;
            } else {
                throw new RemoteException("Failed to get an instance of bank object.");
            }
            
        } catch (RemoteException | NotBoundException | MalformedURLException re) {
            System.out.println(re);
            System.exit(1);
        }
    }
}
