/*
 * Welcome to NetBeans...!!!
 */
package market;

import bank.Account;
import bank.Bank;
import bank.RejectedException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import traders.IFTrade;
import traders.ProfileInformation;

/**
 *
 * @author admin
 */
@SuppressWarnings("serial")
public class MarketImpl extends UnicastRemoteObject implements IFMarket {
        
    static Bank objBank;    
    Map<String, IFTrade> mapRegisteredTraders;
    
    Connection dbConnection;    
    private final String strDBMS;
    private final String strDataSource;
    
    private static final String TRADERS_TABLE_NAME = "TRADERS";
    private static final String ITEMS_TABLE_NAME = "ITEMS";
    private static final String WISH_TABLE_NAME = "WISH";
    
    private PreparedStatement insertTraderStatement;
    private PreparedStatement insertItemStatement;
    private PreparedStatement insertWishStatement;
    private PreparedStatement updateTraderBuyStatsStatement;
    private PreparedStatement updateTraderSellStatsStatement;
    private PreparedStatement updateItemPriceStatement;
    private PreparedStatement updateWishPriceStatement;
    private PreparedStatement deleteTraderStatement;
    private PreparedStatement deleteItemsStatement;
    private PreparedStatement deleteSpecificItemStatement;
    private PreparedStatement deleteWisherStatement;
    private PreparedStatement deleteWishedItemStatement;    
    private PreparedStatement getStockStatement;
    private PreparedStatement getListOfTraderNamesStatement;
    private PreparedStatement getTraderPwdStatement;
    private PreparedStatement getTraderStatsStatement;
    private PreparedStatement getWishersListStatement;
    
    public MarketImpl(String strDBMS, String strDataSrc) throws RemoteException, MalformedURLException {
        
        super();                
        
        this.strDBMS = strDBMS;
        strDataSource = strDataSrc;        
        mapRegisteredTraders = new HashMap<>();
        
        initializeDB();
        initializeTradersList();        
    }   
    
    private void initializeDB() {
        
        try {
            dbConnection = createDataSource();
            prepareStatements(dbConnection);
            
        } catch(ClassNotFoundException | SQLException ex) {
            System.out.println("InitializeDB() - Exception - " + ex.getMessage());
            System.exit(1);
        }
    }
    
    private Connection createDataSource() throws ClassNotFoundException, SQLException {
                
        int tableNameColumn = 3;
        boolean bTradersTableExist = false, bItemsTableExists = false, bWishTableExists = false;        
        
        Connection connection = getConnection();
        DatabaseMetaData dbm = connection.getMetaData();
        for (ResultSet rs = dbm.getTables(null, null, null, null); rs.next();) {
            String strTableName = rs.getString(tableNameColumn);
            if (strTableName.equals(TRADERS_TABLE_NAME)) {
                bTradersTableExist = true;
            } else if (strTableName.equals(ITEMS_TABLE_NAME)) {
                bItemsTableExists = true;
            } else if(strTableName.equals(WISH_TABLE_NAME)) {
                bWishTableExists = true;
            }
            
            if(bTradersTableExist && bItemsTableExists && bWishTableExists) {
                rs.close();
                break;
            }
        }
        
        if (!bTradersTableExist) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE " + TRADERS_TABLE_NAME + 
                    " (Username VARCHAR(32) PRIMARY KEY, Password VARCHAR(32)," + 
                    " NumItemsBought INT, NumItemsSold INT)");
        }
        
        if(!bItemsTableExists) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE " + ITEMS_TABLE_NAME + 
                    " (SellerName VARCHAR(32) NOT NULL, ItemName VARCHAR(32), PricePerQuantity INT," +
                    " CONSTRAINT FK_ITEM FOREIGN KEY(SellerName) REFERENCES " + TRADERS_TABLE_NAME + "(Username))"); 
        }
        
        if(!bWishTableExists) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE " + WISH_TABLE_NAME + 
                    " (Wishername VARCHAR(32) NOT NULL, WishedItemName VARCHAR(32), WishedPrice INT," +
                    " CONSTRAINT FK_WISH FOREIGN KEY(Wishername) REFERENCES " + TRADERS_TABLE_NAME + "(Username))"); 
        }
        
        return connection;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        
        if (strDBMS.equalsIgnoreCase("cloudscape")) {
            Class.forName("COM.cloudscape.core.RmiJdbcDriver");
            return DriverManager.getConnection("jdbc:cloudscape:rmi://localhost:1099/" + strDataSource + ";create=true;");
            
        } else if (strDBMS.equalsIgnoreCase("pointbase")) {
            Class.forName("com.pointbase.jdbc.jdbcUniversalDriver");
            return DriverManager.getConnection("jdbc:pointbase:server://localhost:9092/" + strDataSource + ",new", "PBPUBLIC", "PBPUBLIC");
            
        } else if (strDBMS.equalsIgnoreCase("derby")) {
            Class.forName("org.apache.derby.jdbc.ClientXADataSource");
            return DriverManager.getConnection("jdbc:derby://localhost:1527/" + strDataSource + ";create=true");
            
        } else if (strDBMS.equalsIgnoreCase("mysql")) {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + strDataSource, "root", "javajava");
            
        } else {
            return null;
        }
    }

    private void prepareStatements(Connection connection) throws SQLException {
        
        insertTraderStatement = connection.prepareStatement("INSERT INTO " + TRADERS_TABLE_NAME + 
                " VALUES (?, ?, 0, 0)");
        
        insertItemStatement = connection.prepareStatement("INSERT INTO " + ITEMS_TABLE_NAME + 
                " VALUES (?, ?, ?)");
        
        insertWishStatement = connection.prepareStatement("INSERT INTO " + WISH_TABLE_NAME + 
                " VALUES (?, ?, ?)");
        
        updateTraderBuyStatsStatement = connection.prepareStatement("UPDATE " + TRADERS_TABLE_NAME + 
                " SET NumItemsBought = NumItemsBought + 1 WHERE Username = ?");
        
        updateTraderSellStatsStatement = connection.prepareStatement("UPDATE " + TRADERS_TABLE_NAME + 
                " SET NumItemsSold = NumItemsSold + 1 WHERE Username = ?");
        
        updateItemPriceStatement = connection.prepareStatement("UPDATE " + ITEMS_TABLE_NAME + 
                " SET PricePerQuantity = ? WHERE SellerName = ? AND ItemName = ?");

        updateWishPriceStatement = connection.prepareStatement("UPDATE " + WISH_TABLE_NAME + 
                " SET WishedPrice = ? WHERE Wishername = ? AND WishedItemName = ?");
        
        deleteTraderStatement = connection.prepareStatement("DELETE FROM " + TRADERS_TABLE_NAME + 
                " WHERE Username = ?");

        deleteItemsStatement = connection.prepareStatement("DELETE FROM " + ITEMS_TABLE_NAME + 
                " WHERE SellerName = ?");
                
        deleteWisherStatement = connection.prepareStatement("DELETE FROM " + WISH_TABLE_NAME + 
                " WHERE Wishername = ?");
        
        deleteSpecificItemStatement = connection.prepareStatement("DELETE FROM " + ITEMS_TABLE_NAME +
                " WHERE SellerName = ? AND ItemName = ?");
        
        deleteWishedItemStatement = connection.prepareStatement("DELETE FROM " + WISH_TABLE_NAME +
                " WHERE Wishername = ? AND WishedItemName = ?");
                
        getStockStatement = connection.prepareStatement("SELECT * from " + ITEMS_TABLE_NAME);
        
        getListOfTraderNamesStatement = connection.prepareStatement("SELECT Username FROM " + TRADERS_TABLE_NAME);
        
        getTraderPwdStatement = connection.prepareStatement("SELECT Password FROM " + TRADERS_TABLE_NAME +
                " WHERE Username = ?");
        
        getTraderStatsStatement = connection.prepareStatement("SELECT * FROM " + TRADERS_TABLE_NAME + 
                " WHERE Username = ?");
        
        getWishersListStatement = connection.prepareStatement("SELECT Wishername FROM " + WISH_TABLE_NAME +
                " WHERE WishedItemName = ? AND WishedPrice >= ?");
    }
    
    private void initializeTradersList() {

        try {
            if(mapRegisteredTraders != null) {
                
                ResultSet result = getListOfTraderNamesStatement.executeQuery();
                while (result.next()) {
                    String strTemp = result.getString(1);
                    if( !mapRegisteredTraders.containsKey(strTemp) ) {
                        mapRegisteredTraders.put(strTemp, null);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Exception while initializing the traders list.");
        }
    }
    
    @Override
    public void registerTrader(String sTraderUserName, String strPassword) throws RemoteException {
        
        try {
            if(mapRegisteredTraders != null) {

                // Verify that the username is unique
                if (mapRegisteredTraders.containsKey(sTraderUserName)) {
                    throw new RemoteException("Username already registered. Choose different username");
                } else {                
                    // Verify that the passowrd is of min. 8 chars in length
                    if(strPassword.toCharArray().length < 8) {
                        throw new RemoteException("Password should be minimum 8 characters.");
                    } else {                                        
                        // Add the trader to the database table
                        insertTraderStatement.setString(1, sTraderUserName);
                        insertTraderStatement.setString(2, strPassword);
                        insertTraderStatement.executeUpdate();

                        // Register the trader
                        mapRegisteredTraders.put(sTraderUserName, null);
                    }
                }
            }
        } catch (RemoteException | SQLException ex) {
            throw new RemoteException("Error registering - " + ex.getMessage());
        }
    }
    
    @Override
    public void unRegisterTrader(String sTraderName) throws RemoteException {
        
        try {
            if(mapRegisteredTraders != null) {
                if ( !mapRegisteredTraders.containsKey(sTraderName) ) {
                    throw new RemoteException("Trader is not registered with the market");
                } else {
                    // Remove the entries from the wish table
                    deleteWisherStatement.setString(1, sTraderName);
                    deleteWisherStatement.executeUpdate();
                    
                    // Remove entries from the Items table
                    deleteItemsStatement.setString(1, sTraderName);
                    deleteItemsStatement.executeUpdate();
                    
                    // Remove entries from the Trader table
                    deleteTraderStatement.setString(1, sTraderName);
                    deleteTraderStatement.executeUpdate();
                    
                    // Delete from the map
                    mapRegisteredTraders.remove(sTraderName);
                }
            }

            // Notify all other clients by refreshing their tables
            for(IFTrade trader : mapRegisteredTraders.values()) {
                if(trader != null) {
                    trader.refreshDisplayedInventory();
                }
            }
        } catch(RemoteException | SQLException ex) {
            throw new RemoteException("Error unregistering - " + ex.getMessage());
        }        
    }
    
    @Override
    public void loginToMarket(String strTraderUserName, String strPassword, IFTrade objTrader) throws RemoteException {
        
        try {
            if(mapRegisteredTraders != null) {

                // Verify that the username is unique
                if ( ! mapRegisteredTraders.containsKey(strTraderUserName) ) {
                    throw new RemoteException("No such trader is registered. Register to login.");
                } else {
                    // Get password of the trader and verify
                    getTraderPwdStatement.setString(1, strTraderUserName);
                    ResultSet res = getTraderPwdStatement.executeQuery();
                    if(res.next()) {
                        String strPwd = res.getString("Password");
                        if(strPassword.equals(strPwd)) {
                            mapRegisteredTraders.put(strTraderUserName, objTrader);
                        } else {
                            throw new RemoteException("Username/Password is invalid.");
                        }
                    }
                }
            }
        } catch (SQLException | RemoteException ex) {
            throw new RemoteException("Error login - " + ex.getMessage());
        }        
    }
    
    @Override
    public ProfileInformation getTradeStatistics(String strTraderName) throws RemoteException {        
        
        ProfileInformation objProfileInfo = null;
        
        try {
            if( !mapRegisteredTraders.containsKey(strTraderName) ) {
                throw new RemoteException("No such trader is registered.");
            } else {
                // Get the statistics info from the database
                getTraderStatsStatement.setString(1, strTraderName);
                ResultSet result = getTraderStatsStatement.executeQuery();
                if(result.next()) {
                    objProfileInfo = new ProfileInformation();
                    objProfileInfo.setTraderName(result.getString(1));
                    objProfileInfo.setPassword(result.getString(2));
                    objProfileInfo.setPwdStrength("Good");
                    objProfileInfo.setNumItemsBought(result.getInt(3));
                    objProfileInfo.setNumItemsSold(result.getInt(4));
                }
            }
        } catch (RemoteException | SQLException ex) {
            throw new RemoteException( "Error getting Profile Info - " + ex.getMessage() );
        }
        
        return objProfileInfo;
    }
    
    @Override
    public void sellItem(String sSellerName, String sItemName, int nPricePerQuantity) throws RemoteException {

       try {
            if(mapRegisteredTraders.containsKey(sSellerName)) {

                // Check if the same item from the same seller is available, if yes update the price
                updateItemPriceStatement.setInt(1, nPricePerQuantity);
                updateItemPriceStatement.setString(2, sSellerName);
                updateItemPriceStatement.setString(3, sItemName);
                int  nRetValue = updateItemPriceStatement.executeUpdate();
                if(nRetValue == 0) {
                   // Add the item into the database, as it is not present already
                    insertItemStatement.setString(1, sSellerName);
                    insertItemStatement.setString(2, sItemName);
                    insertItemStatement.setInt(3, nPricePerQuantity);
                    insertItemStatement.executeUpdate(); 
                }                           

                // Notify the wisher, if the new item/price is matching the one in the wish list.
                Item item = new Item();
                item.setName(sItemName);
                item.setPrice(nPricePerQuantity);
                notifyWisher(sSellerName, item);

                // Notify all other clients by refreshing their tables
                for(IFTrade trader : mapRegisteredTraders.values()) {
                    if(trader != null) {
                        trader.refreshDisplayedInventory();
                    }
                } 
            }
            else {
                throw new RemoteException("No such Trader registered. Cannot sell item.");
            }
       } catch(RemoteException | SQLException ex) {
           throw new RemoteException("Sell Error - " + ex.getMessage());
       }
    }
    
    @Override
    public void buyItem(String sBuyerName, String sSellerName, String sItemName, int ndisplayedPrice) throws RemoteException {

        try {
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
                        deleteWishedItemStatement.setString(1, sBuyerName);
                        deleteWishedItemStatement.setString(2, sItemName);
                        deleteWishedItemStatement.executeUpdate();                                                

                        // Remove item from the inventory
                        deleteSpecificItemStatement.setString(1, sSellerName);
                        deleteSpecificItemStatement.setString(2, sItemName);
                        deleteSpecificItemStatement.executeUpdate();
                        
                        // Increment trade statistics of buyer and seller
                        updateTraderBuyStatsStatement.setString(1, sBuyerName);
                        updateTraderBuyStatsStatement.executeUpdate();
                        updateTraderSellStatsStatement.setString(1, sSellerName);
                        updateTraderSellStatsStatement.executeUpdate();

                        // Notify the seller that his item placed for sale is sold and amount is deposited
                        notifySeller(sBuyerName, sSellerName, sItemName);

                        // Notify all other clients by refreshing their tables
                        for(IFTrade trader : mapRegisteredTraders.values()) {
                            if(trader != null) {
                                trader.refreshDisplayedInventory();
                            }
                        } 
                    } else {
                        throw new RemoteException("Insufficient funds.");
                    }                
                } else {
                    throw new RemoteException("No bank account(s) to use for purchase.");
                }
            } else {
                throw new RemoteException("Trader is not yet registered.");
            } 
        } catch(SQLException | RemoteException | RejectedException ex) {
            throw new RemoteException("Failed to purchase. Error - " + ex.getMessage());
        }
    }
    
    @Override
    public Map<String, ArrayList<Item>> getStock() throws RemoteException {

        Map<String, ArrayList<Item>> stock = new HashMap<>();
        
        try {
            ResultSet result = getStockStatement.executeQuery();
            while(result.next()) {
                
                // Read seller name, Item name and price
                String strSellerName = result.getString(1);
                Item item = new Item();
                item.setName(result.getString(2));
                item.setPrice(result.getInt(3));
                
                // Check if the seller entry is already present
                if(stock.containsKey(strSellerName)) {
                    stock.get(strSellerName).add(item);
                } else {
                    ArrayList<Item> listItems = new ArrayList<>();
                    listItems.add(item);
                    stock.put(strSellerName, listItems);
                }
            }
        } catch(SQLException ex) {
            stock = null;
            System.out.println("Failed to retrieve stock. Error - " + ex.getMessage());
        }
        
        return stock;
    }
    
    @Override
    public void wishItem(String sWisherName, String sItemName, int nWishPrice) throws RemoteException {

        try {
            if(mapRegisteredTraders.containsKey(sWisherName)) {
                
                // Check if the same wisher wishes same item for a new price
                updateWishPriceStatement.setInt(1, nWishPrice);
                updateWishPriceStatement.setString(2, sWisherName);
                updateWishPriceStatement.setString(3, sItemName);
                int nRetValue = updateWishPriceStatement.executeUpdate();
                if(nRetValue == 0) {
                    
                    // Item is new to wish list, add it
                    insertWishStatement.setString(1, sWisherName);
                    insertWishStatement.setString(2, sItemName);
                    insertWishStatement.setInt(3, nWishPrice);
                    insertWishStatement.executeUpdate();
                }
                
                // Notify other traders of the wish
                notifyTraderWish(sWisherName, sItemName, nWishPrice);
                
            } else {
                throw new RemoteException("Trader is not yet registered.");
            }
        } catch (SQLException | RemoteException ex) {
            throw new RemoteException("Failed to queue your wish. Error - " + ex.getMessage());
        }
    }

    private void notifyWisher(String strSellerName, Item objItem) {
        
        try {
            // Get the wishers from the wish table for this new item for sale
            getWishersListStatement.setString(1, objItem.getName());
            getWishersListStatement.setInt(2, objItem.getPrice());
            ResultSet result = getWishersListStatement.executeQuery();

            while(result.next()) {
                String strTrader = result.getString(1);
                IFTrade tradeWisher = mapRegisteredTraders.get(strTrader);
                try {
                    if(tradeWisher != null) {
                        tradeWisher.notifyWishItemAvailability(strSellerName, objItem.getName(), objItem.getPrice());
                    } else {
                        System.out.println("No such trader (" + strTrader + ") is registered to notify. Object null.");
                    }
                } catch(RemoteException ex) {
                   System.out.println("Failed to notify the wished trader - " + strTrader + ". Error - " + ex.getMessage()); 
                }
            }        
        } catch(SQLException ex) {
            System.out.println("Failed to notify the wished traders of the new item. Error - " + ex.getMessage()); 
        }     
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
    
    private void notifyTraderWish(String strWisherName, String strItemName, int nWishPrice) {
        
        // Notify other traders of the wish
        for(String trader : mapRegisteredTraders.keySet()) {
            if( !trader.equals(strWisherName) ) {
                try{
                    IFTrade objTrader = mapRegisteredTraders.get(trader);
                    if(objTrader != null) {
                        objTrader.notifyTraderWish(strWisherName, strItemName, nWishPrice);
                    }
                } catch (RemoteException ex) {
                    System.out.println("Failed to notify " + trader + " of new wish. Error - " + ex.getMessage());
                }
            }
        }        
    }
}
