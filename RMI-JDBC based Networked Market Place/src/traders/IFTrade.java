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
    
    void notifyItemSold(String strBuyerName, String strItemName) throws RemoteException;
    
    void notifyWishItemAvailability(String strSellerName, String strItemName, int nNewPrice) throws RemoteException;
    
    void refreshDisplayedInventory() throws RemoteException;
    
    void notifyServerRestart() throws RemoteException;
    
    void notifyTraderWish(String strWisherName, String strItemName, int nWishPrice) throws RemoteException;
}
