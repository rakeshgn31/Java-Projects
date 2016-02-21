/*
 * Welcome to NetBeans...!!!
 */
package market;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import traders.IFTrade;

/**
 *
 * @author admin
 */
public interface IFMarket extends Remote {
    
    void registerTrader(String sTraderName, IFTrade objTrader) throws RemoteException;
    
    void unRegisterTrader(String sTraderName) throws RemoteException;
    
    void sellItem(String sTraderName, String sItemName, int nPricePerQuantity) throws RemoteException;

    void buyItem(String sBuyerName, String sSellerName, String sItemName, int ndisplayedPrice) throws RemoteException;

    Map<String, ArrayList<Item>> getStock() throws RemoteException;
    
    void wishItem(String sWisherName, String sItemName, int nWishPrice) throws RemoteException;
}
