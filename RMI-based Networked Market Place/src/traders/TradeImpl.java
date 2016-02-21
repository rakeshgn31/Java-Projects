/*
 * Welcome to NetBeans...!!!
 */
package traders;

import banking.Account;
import banking.Bank;
import banking.RejectedException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import market.IFMarket;
import market.Item;

/**
 *
 * @author admin
 */
public class TradeImpl extends UnicastRemoteObject implements IFTrade {
    
    String strRegisteredTradeName;
    ShoppingGUI objGUI;
    IFMarket marketPlaceServer;
    Bank objBankClient;
    Account objAccount;
    
    public TradeImpl(ShoppingGUI guiObject) throws RemoteException {
        super();
                
        strRegisteredTradeName = "";
        this.objGUI = guiObject;
    }
    
    public void initializeObjects(IFMarket marketServer, Bank objBank) {
        this.marketPlaceServer = marketServer;
        this.objBankClient = objBank;
    }
    
    @Override
    public void notifyItemSold(String strBuyerName, String strItemName) throws RemoteException {
        objGUI.setStatus(strBuyerName + " bought your item " + strItemName + ". Amount credited.");
        objGUI.refreshBalance((int)objAccount.getBalance());
        objGUI.removeSoldItemFromList(strItemName);
    }
    
    @Override
    public void notifyWishItemAvailability(String strSellerName, String strItemName, int nNewPrice) throws RemoteException {
        objGUI.setStatus("Your wish comes true. Item " + strItemName + " is being sold at " 
                + nNewPrice + " by Seller : " + strSellerName);
        objGUI.refreshBalance((int)objAccount.getBalance());
    }
    
    @Override
    public void refreshDisplayedInventory() {
        objGUI.RefreshTable();        
    }
    
    public void registerTrader(String strTraderName) {
        
        try {            
            marketPlaceServer.registerTrader(strTraderName, this);
            objGUI.setStatus("Client registered successfully");
            
            // Open an account in the bank, if not already exists
            objAccount = objBankClient.getAccount(strTraderName);
            if(objAccount == null) {
                objAccount = objBankClient.newAccount(strTraderName);
                objAccount.deposit(1000);
            } else {
                if(objAccount.getBalance() <= 0) {
                    objAccount.deposit(1000);
                }
            }
            
            objGUI.refreshBalance((int)objAccount.getBalance());
            
        } catch (RemoteException | RejectedException ex) {
            objGUI.setStatus("Client registration failed. Error - " + ex.getMessage());
        }        
    }
    
    public void unregisterTrader(String strTraderName) {
        
        try {            
            marketPlaceServer.unRegisterTrader(strTraderName);
            objGUI.setStatus("Client deregistered successfully");
        } catch (RemoteException ex) {
            objGUI.setStatus("Client deregistration failed. Error - " + ex.getMessage());
        }        
    }
    
    public void buyItemFromInventory(String sBuyerName, String sSellerName, String sItemName, int ndisplayedPrice) {
        
        try {            
            marketPlaceServer.buyItem(sBuyerName, sSellerName, sItemName, ndisplayedPrice);
            objGUI.setStatus("Item purchase successful");
            objGUI.refreshBalance((int)objAccount.getBalance());
        } catch (RemoteException ex) {
            objGUI.setStatus("Unable to purchase item. Error - " + ex.getMessage());
        } 
    }
    
    public boolean placeItemForSale(String sSellerName, String sItemName, int nSellingPrice) {
        
        boolean bRequestPlaced = true;
        
        try {
            marketPlaceServer.sellItem(sSellerName, sItemName, nSellingPrice);
            objGUI.setStatus("Item placed into market for sale.");            
            
        } catch (RemoteException ex) {
            bRequestPlaced = false;
            objGUI.setStatus("Unable to add item into market. Error - " + ex.getMessage());
        }
        
        return bRequestPlaced;
    }

    public void addItemToWishList(String strWishingTraderName, String strItemName, int nWishPrice) {
        
        try {
            marketPlaceServer.wishItem(strWishingTraderName, strItemName, nWishPrice);
            objGUI.setStatus("Item placed into wish list.");
           
        } catch (RemoteException ex) {
            objGUI.setStatus("Unable to add item into wish list. Error - " + ex.getMessage());
        }
    }
    
    public Map<String, ArrayList<Item>> getAllItemsFromInventory() {
        
        Map<String, ArrayList<Item>> stockFromInventory = null;
        
        try {            
            stockFromInventory = marketPlaceServer.getStock();
            if(stockFromInventory == null) {
               objGUI.setStatus("No items for sale in the inventory..."); 
            }
        } catch (RemoteException ex) {
            objGUI.setStatus("Unable to retrieve items from inventory. Error - " + ex.getMessage());
        }
        
        return stockFromInventory;
    }
}
