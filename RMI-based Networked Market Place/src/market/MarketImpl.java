/*
 * Welcome to NetBeans...!!!
 */
package market;

import banking.Account;
import banking.Bank;
import banking.RejectedException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import traders.IFTrade;

/**
 *
 * @author admin
 */
@SuppressWarnings("serial")
public class MarketImpl extends UnicastRemoteObject implements IFMarket {
    
    static Bank objBank;
    Inventory objInventory;
    Map<String, IFTrade> mapRegisteredTraders;
    
    public MarketImpl() throws RemoteException, MalformedURLException {
        super(); 
        objInventory = new Inventory();
        mapRegisteredTraders = new HashMap<>();
    }   
    
    @Override
    public void registerTrader(String sTraderName, IFTrade refTrader) throws RemoteException {
        
        if(mapRegisteredTraders != null) {
            if (mapRegisteredTraders.containsKey(sTraderName)) {
                throw new RemoteException("Trader is already registered");
            }
        }
        
        mapRegisteredTraders.put(sTraderName, refTrader);
    }
    
    @Override
    public void unRegisterTrader(String sTraderName) throws RemoteException {
        
        if(mapRegisteredTraders != null) {
            if (!mapRegisteredTraders.containsKey(sTraderName)) {
                throw new RemoteException("Trader is not registered with the market");
            }
        }
        
        // Remove the items from the list and notify other clients
        objInventory.removeItemsOfSpecificSeller(sTraderName);
        
        // Remove the client completely
        mapRegisteredTraders.remove(sTraderName);
        
        // Notify all other clients by refreshing their tables
        for(IFTrade trader : mapRegisteredTraders.values()) {
            trader.refreshDisplayedInventory();
        }        
    }
    
    @Override
    public void sellItem(String sSellerName, String sItemName, int nPricePerQuantity) throws RemoteException {

        if(mapRegisteredTraders.containsKey(sSellerName)) {
            Item item = new Item();            
            item.setName(sItemName);
            item.setPrice(nPricePerQuantity);
            
            // Add item to the inventory
            objInventory.addItem(sSellerName, item);
            
            // Notify the wisher, if the new item/price is matching the one in the wish list.
            notifyWisher(sSellerName, item);
            
            // Notify all other clients by refreshing their tables
            for(IFTrade trader : mapRegisteredTraders.values()) {
                trader.refreshDisplayedInventory();
            } 
        }
        else {
            throw new RemoteException("Trader is not yet registered. Hence cannot sell the item.");
        }
    }
    
    @Override
    public void buyItem(String sBuyerName, String sSellerName, String sItemName, int ndisplayedPrice) throws RemoteException {

        if( mapRegisteredTraders.containsKey(sBuyerName) && mapRegisteredTraders.containsKey(sSellerName) ) {
                        
            Account buyerAccount = objBank.getAccount(sBuyerName);
            Account sellerAccount = objBank.getAccount(sSellerName);
            if(buyerAccount != null && sellerAccount != null) {
                // Check if buyer has sufficient funds in his account
                int buyerBal = (int) buyerAccount.getBalance();
                if(buyerBal >= ndisplayedPrice) {
                    
                    try {
                        // Withdraw money from buyer bank account and deposit it in seller bank account
                        buyerAccount.withdraw(ndisplayedPrice);
                        sellerAccount.deposit(ndisplayedPrice);
                    } catch (RejectedException ex) {
                        throw new RemoteException("Failed to purchase. Error - " + ex.getMessage());
                    }
                    // Remove from Wish list, if present
                    Item item = new Item();            
                    item.setName(sItemName);
                    objInventory.removeFromWishList(sBuyerName, item);

                    // Remove item from the inventory
                    objInventory.removeItem(sSellerName, item);                        

                    // Notify the seller that his item placed for sale is sold and amount is deposited
                    notifySeller(sBuyerName, sSellerName, sItemName);
                    
                    // Notify all other clients by refreshing their tables
                    for(IFTrade trader : mapRegisteredTraders.values()) {
                        trader.refreshDisplayedInventory();
                    } 
        
                } else {
                    throw new RemoteException("Failed to purchase due to insufficient funds.");
                }                
            } else {
                throw new RemoteException("Failed to purchase. User does not have a bank account to use it for purchase.");
            }
        }
        else {
            throw new RemoteException("Trader is not yet registered. Hence cannot buy the item.");
        }        
    }
    
    @Override
    public Map<String, ArrayList<Item>> getStock() throws RemoteException {

        return objInventory.getStock();
    }
    
    @Override
    public void wishItem(String sWisherName, String sItemName, int nWishPrice) throws RemoteException {

        if(mapRegisteredTraders.containsKey(sWisherName)) {
        Item item = new Item();
        
        item.setName(sItemName);
        item.setPrice(nWishPrice);
        
        objInventory.addToWishList(sWisherName, item);
        } else {
            throw new RemoteException("Trader is not yet registered. Hence cannot wish the item.");
        }
    }

    private void notifyWisher(String strSellerName, Item objItem) {
        
        // Get the wisher trader name from the wish list
        ArrayList<String> listofWishers = objInventory.getWishersList(objItem);

        // Send a message to the interested traders
        listofWishers.stream().forEach((trader) -> {
            try {
                IFTrade tradeWisher = mapRegisteredTraders.get(trader);
                if(tradeWisher != null) {
                    tradeWisher.notifyWishItemAvailability(strSellerName, objItem.getName(), objItem.getPrice());
                } else {
                    System.out.println("No such trader (" + trader + ") is registered to notify. Object null.");
                }
            } catch(RemoteException ex) {
                System.out.println("Failed to notify the wished trader - " + trader + ". Error - " + ex.getMessage());
            }
        });      
    }

    private void notifySeller(String sBuyerName, String sSellerName, String sItemName) {
        
        try {
            IFTrade tradeSeller = mapRegisteredTraders.get(sSellerName);
            if(tradeSeller != null) {
                tradeSeller.notifyItemSold(sBuyerName, sItemName);
            } else {
                    System.out.println("No such trader (" + sSellerName + ") is registered to notify. Object null.");
            }
        } catch (RemoteException ex) {
            System.out.println("Failed to notify the seller - " + sSellerName + ". Error - " + ex.getMessage());
        }
    }
}
