/*
 * Welcome to NetBeans...!!!
 */
package market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class Inventory {
    
    /*  String represents the client (TRADER) name and
    *   ArrayList contains the list of items the user in interested in selling 
    */
    Map<String, ArrayList<Item>> Stock;
    
    /*  Key is the buyer (Wisher) name   
    *   Value is the list of items that the wisher wishes at a certain price
    */
    Map<String, ArrayList<Item>> WishList;
    
    Inventory() {
        Stock = new HashMap<>();
        WishList = new HashMap<>();
    }
    
    /**
     *
     * @return - the stock in the inventory
     */
    public Map<String, ArrayList<Item>> getStock() {
        return Stock;
    }
    
    /**
     *
     * @param strTrader
     * @param objItem
     */
    public void addItem(String strTrader, Item objItem) {

        ArrayList<Item> ntempList = Stock.get(strTrader);
        if(ntempList != null) {

            // Key is already present
            // Iterate through the list and see if the item is already present
            boolean bItemPresent = false;
            for(Item tempItem : ntempList) {
                if ( tempItem.getName().equalsIgnoreCase(objItem.getName()) ) {
                    tempItem.setPrice(objItem.getPrice());  // Update the price of existing item (When price is inc or dec)
                    bItemPresent = true;
                    break;
                }
            }
            if(bItemPresent == false) {
                ntempList.add(objItem);
            }
        } else {
            ntempList = new ArrayList<>();
            ntempList.add(objItem);
        } 
        
        // Update the stock with new item
        Stock.put(strTrader, ntempList);
    }
    
    /**
     *
     * @param strTrader
     * @param objItem
     */
    public void removeItem(String strTrader, Item objItem) {

        ArrayList<Item> ntempList = Stock.get(strTrader);
        if(ntempList != null) {

            // Key is already present
            // Iterate through the list and see if the item is already present
            boolean bItemPresent = false;
            int nIndex = -1;
            for(Item tempItem : ntempList) {
                nIndex++;
                if ( tempItem.getName().equalsIgnoreCase(objItem.getName()) ) {
                    bItemPresent = true;
                    break;
                }
            }
            if(bItemPresent == true) {
                ntempList.remove(nIndex);
            }
            
            // Update the stock with new item
            Stock.put(strTrader, ntempList);
        }
    }
    
    public void removeItemsOfSpecificSeller(String strSellerName) {
        
        // Remove from stock list
        if(Stock.containsKey(strSellerName)) {
            Stock.remove(strSellerName);
        }
        
        // Remove from Wish list
        if(WishList.containsKey(strSellerName)) {
            WishList.remove(strSellerName);
        }                
    }
    
    public void addToWishList(String sWisherName, Item item) {

        ArrayList<Item> ntempStockWishList = WishList.get(sWisherName);
        if(ntempStockWishList != null) {

            // Wisher is already present in the wish list
            // Iterate through the list and see if the item is already present
            boolean bItemPresent = false;
            for (Item tempItem : ntempStockWishList) {
                if ( tempItem.getName().equalsIgnoreCase(item.getName()) ) {
                    tempItem.setPrice(item.getPrice()); // Sets the new wish price
                    bItemPresent = true;
                    break;
                }
            }

            if(bItemPresent == false) {
                ntempStockWishList.add(item);  // If the item is not in the wish list
            }
        } else {
            // Wisher is new
            ntempStockWishList = new ArrayList<>();
            ntempStockWishList.add(item);
        }
        
        // Update the stock with new item
        WishList.put(sWisherName, ntempStockWishList);
    }
    
    public void removeFromWishList(String sWisherName, Item item) {

        ArrayList<Item> ntempStockWishList = WishList.get(sWisherName);
        if(ntempStockWishList != null) {

            // Wisher is already present in the wish list
            // Iterate through the list and see if the item is already present
            boolean bItemPresent = false;
            int nIndex = -1;
            for (Item tempItem : ntempStockWishList) {
                nIndex++;
                if ( tempItem.getName().equalsIgnoreCase(item.getName()) ) {
                    bItemPresent = true;
                    break;
                }
            }

            if(bItemPresent == true) {
                ntempStockWishList.remove(nIndex);  // If the item is not in the wish list
            }
            
            // Update the stock with new item
            WishList.put(sWisherName, ntempStockWishList);
        }
    }

    public ArrayList<String> getWishersList(Item objItem) {

        ArrayList<String> listOfWishers = new ArrayList<>();
                
        int nIndex = -1;
        Object[] keys = WishList.keySet().toArray();
        for(ArrayList<Item> listofItems : WishList.values()) {
            nIndex++;
            for(Item item : listofItems) {
                if(item.getName().equals(objItem.getName())) {
                    listOfWishers.add(keys[nIndex].toString());    // Add the corresponding wisher to the list
                    break;
                }
            }
        }
        
        return listOfWishers;
    }
}
