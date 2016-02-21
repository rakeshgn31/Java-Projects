/*
 * Welcome to NetBeans...!!!
 */
package traders;

import bank.Account;
import bank.Bank;
import bank.RejectedException;
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
        objGUI.refreshInventory();
    }
    
    @Override
    public void notifyServerRestart() {
       objGUI.setStatus("Server was restarted. Logout and Login again to reconnect");
    }
    
    @Override
    public void notifyTraderWish(String strWisherName, String strItemName, int nWishPrice) {
        objGUI.setStatus(strWisherName + " wishes " + strItemName + " for " + nWishPrice + " SEK.");
    }
    
    public boolean registerTrader(String strUserName, String strPassword) {
        
        boolean bRetValue = false;
        
        try {            
            marketPlaceServer.registerTrader(strUserName, strPassword);
            objGUI.setStatus("Client registered successfully");                        
            
            bRetValue = true;
            
        } catch (RemoteException ex) {
            objGUI.setStatus("Registration failed. Error - " + ex.getMessage());
        }

        return bRetValue;        
    }
    
    public void unregisterTrader(String strTraderName) {
        
        try {            
            marketPlaceServer.unRegisterTrader(strTraderName);
            objGUI.setStatus("Trader deregistered successfully");
        } catch (RemoteException ex) {
            objGUI.setStatus("Deregistration failed. Error - " + ex.getMessage());
        }        
    }
    
    public boolean loginToMarket(String strUserName, String strPassword) {
        
        boolean bRetValue = false;
        
        try {            
            marketPlaceServer.loginToMarket(strUserName, strPassword, this);
            objGUI.setStatus("Login successful");           
            
            // Open an account in the bank, if not already exists
            objAccount = objBankClient.getAccount(strUserName);
            if(objAccount == null) {
                objAccount = objBankClient.newAccount(strUserName);
                objAccount.deposit(1000);
            } else {
                if(objAccount.getBalance() <= 0) {
                    objAccount.deposit(1000);
                }
            }
            
            objGUI.refreshBalance((int)objAccount.getBalance());
            
            bRetValue = true;
            
        } catch (RemoteException | RejectedException ex) {
            objGUI.setStatus("Login failed. Error - " + ex.getMessage());
        }
                
        return bRetValue;
    }
    
    public ProfileInformation getProfileInformation(String strTraderName) {
        
        ProfileInformation objProfileInfo = null;
        
        try {            
            // Call remote method on the server
            objProfileInfo = marketPlaceServer.getTradeStatistics(strTraderName);
            
        } catch(RemoteException ex) {
            objGUI.setStatus("Failed to get Profile Information. Error - " + ex.getMessage());
        }
        
        return objProfileInfo;
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
    
    public void placeItemForSale(String sSellerName, String sItemName, int nSellingPrice) {
       
        try {
            marketPlaceServer.sellItem(sSellerName, sItemName, nSellingPrice);
            objGUI.setStatus("Item placed into market for sale.");            
            
        } catch (RemoteException ex) {
            objGUI.setStatus("Unable to add item into market. Error - " + ex.getMessage());
        }
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
