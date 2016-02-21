/*
 * Welcome to NetBeans...!!!
 */
package traders;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author admin
 */
public interface IFTrade extends Remote {
    
    void refreshDisplayedInventory() throws RemoteException;
    
    void notifyItemSold(String strBuyerName, String strItemName) throws RemoteException;
    
    void notifyWishItemAvailability(String strSellerName, String strItemName, int nNewPrice) throws RemoteException;        
}
