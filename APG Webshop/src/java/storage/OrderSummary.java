/*
 * Welcome to NetBeans...!!!
 */

package storage;

import java.io.Serializable;
import java.util.HashMap;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "ORDERSUMMARY")
@NamedQueries({
                @NamedQuery(name = "OrderSummary.findAll", query = "SELECT orders FROM OrderSummary orders")
              })
public class OrderSummary implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDER_ID")
    private Integer orderID;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "USERNAME")
    private String username;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "LIST_OF_ORDERS")
    private byte[] mapProductsvsQuantity;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PURCHASE_AMOUNT")
    private double purchaseAmount;

    /**
     * Creates an instance of OrderSummary class
     * Default no-argument constructor
     */
    public OrderSummary() {}

    /**
     * Creates an instance of OrderSummary class
     * @param orderID - Order ID
     */
    public OrderSummary(Integer orderID) {

        this.orderID = orderID;
    }

    /**
     * Creates an instance of OrderSummary class with supplied data
     * @param username  - Name of the customer
     * @param mapProdvsQuantity  - Map of Products vs Quantity (Previous orders)
     * @param totalOrderAmount      - Total amount of that particular order
     */
    public OrderSummary(String username, byte[] mapProdvsQuantity, double totalOrderAmount) {

        this.username = username;
        this.mapProductsvsQuantity = mapProdvsQuantity;
        this.purchaseAmount = totalOrderAmount;
    }

    /**
     * Returns the order ID of a successful order
     * @return order ID
     */
    public Integer getOrderID() {

        return orderID;
    }

    /**
     * Sets the order ID
     * @param orderID 
     */
    public void setOrderID(Integer orderID) {

        this.orderID = orderID;
    }

    /**
     * Returns the username of the customer
     * @return Name of customer
     */
    public String getUsername() {

        return username;
    }

    /** 
     * Sets the username of the customer
     * @param username 
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * Map of Products vs Quantity representing the purchase history
     * @return Data serialized into byte array
     */
    public byte[] getMapProductsvsQuantity() {

        return mapProductsvsQuantity;
    }

    /**
     * Sets the purchase history of the customer
     * @param mapProductsvsQuantity - Serialized byte array of purchase history
     */
    public void setMapProductsvsQuantity(byte[] mapProductsvsQuantity) {

        this.mapProductsvsQuantity = mapProductsvsQuantity;
    }

    /**
     * Returns the amount of purchase of the order
     * @return 
     */
    public double getPurchaseAmount() {

        return purchaseAmount;
    }

    /**
     * Sets the purchase amount of the order
     * @param purchaseAmount 
     */
    public void setPurchaseAmount(double purchaseAmount) {

        this.purchaseAmount = purchaseAmount;
    }

    @Override
    public int hashCode() {

        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        // TODO: Warning - this method won't work in the case the orderID fields are not set
        if (!(object instanceof OrderSummary)) {
            return false;
        }

        OrderSummary other = (OrderSummary) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {

        return "Entities.OrderSummary[ ORDER_ID=" + orderID + " ]";
    }

    /**
     * Method that de-serializes the purchase history retrieved from the database
     * @return Hashmap of customer's purchase history
     */
    public HashMap<Inventory, Integer> getOrderContents() {
        HashMap<Inventory, Integer> contents=(HashMap) SerializationUtils.deserialize(mapProductsvsQuantity);

        return contents;
    }
}
