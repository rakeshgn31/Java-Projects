/*
 * Welcome to NetBeans...!!!
 */

package storage;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "INVENTORY")
@NamedQueries({
                @NamedQuery(name = "Inventory.findAll", query = "SELECT prod FROM Inventory prod")
              })
public class Inventory implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRODUCT_NAME")
    private String productName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRODUCT_CATEGORY")
    private String productCategory;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private double productPrice;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")    
    private int productQuantity;

    /**
     * Creates an instance of the Inventory class
     * Default no-argument constructor
     */
    public Inventory() {}

    /**
     * Creates an instance of the Inventory class
     * @param productName - Name of the product
     */
    public Inventory(String productName) {

        this.productName = productName;
    }

     /**
     * Creates an instance of the Inventory class
     * @param productName   - Name of the product
     * @param prodType      - Category of the product
     * @param pricePerUnit  - Cost of one unit of product
     * @param numOfUnits    - Number of units of the product
     */
    public Inventory(String productName, String prodType, double pricePerUnit, int numOfUnits) {

        this.productName = productName;
        this.productCategory = prodType;
        this.productPrice = pricePerUnit;
        this.productQuantity = numOfUnits;
    }

    /**
     * Returns the name of the product
     * @return Product Name
     */
    public String getProductName() {

        return productName;
    }

    /**
     * Sets the name of the product
     * @param productName
     */
    public void setProductName(String productName) {

        this.productName = productName;
    }

    /**
     * Returns the type of the product
     * @return category of the product
     */
    public String getProductCategory() {

        return productCategory;
    }

    /**
     * Sets the type of product
     * @param productCategory 
     */
    public void setProductCategory(String productCategory) {

        this.productCategory = productCategory;
    }

    /**
     * Returns the price per unit of the product
     * @return Price per unit
     */
    public double getProductPrice() {

        return productPrice;
    }

    /**
     * Sets the Price per unit of the product
     * @param productPrice 
     */     
    public void setProductPrice(double productPrice) {

        this.productPrice = productPrice;
    }

    /**
     * Returns the available product stock (Number of units)
     * @return - Number of units
     */
    public int getProductQuantity() {

        return productQuantity;
    }

    /**
     * Sets the product stock
     * @param productQuantity - Number of units 
     */
    public void setProductQuantity(int productQuantity) {

        this.productQuantity = productQuantity;
    }

    @Override
    public int hashCode() {

        int hash = 0;
        hash += (productName != null ? productName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventory)) {
            return false;
        }
        
        Inventory other = (Inventory) object;
        if ((this.productName == null && other.productName != null) || (this.productName != null && !this.productName.equals(other.productName))) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {

        return "Entities.Inventory[ PRODUCT_NAME=" + productName + " ]";
    }   
}
