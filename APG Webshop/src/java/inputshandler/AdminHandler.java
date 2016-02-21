/*
 * Welcome to NetBeans...!!!
 */

package inputshandler;

import storage.Inventory;
import storage.Customers;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import inputsprocessor.RequestsProcessor;

/**
 *
 * @author admin
 */
@ManagedBean(name = "admin")
@SessionScoped
public class AdminHandler {

    String strInfo;
    String strProductName, strProductCategory;
    String strProductQuantity, strProductPrice;
    List<Inventory> productsList;
    List<Customers> customersList;
    
    @EJB
    private RequestsProcessor objReqProcessor;
    
    /**
     * Creates a new instance of AdminHandler
     */
    public AdminHandler() {
        
        strInfo = "";
        strProductName = "";
        strProductCategory = "";
        strProductQuantity = "";
        strProductPrice = "";        
    }

     /**
     * Returns a list of products in the inventory
     * @return list of products in the inventory
     */
    public List<Inventory> getProductsList() {
        productsList=objReqProcessor.getProductsOfType("AdminAll");
        return productsList;
    }

     /**
     * sets the product list in the inventory
     * @param productsList - list of products
     */
    public void setProductsList(List<Inventory> productsList) {
        this.productsList = productsList;
    }

     /**
     * Returns the list of customers
     * @return list of registered customers
     */
    public List<Customers> getCustomersList() {
        customersList=objReqProcessor.getAllCustomers();
        return customersList;
    }

    /**
     * 
     * @param customersList - list of customers
     */
    public void setCustomersList(List<Customers> customersList) {
        this.customersList = customersList;
    }

    /**
     * Returns the name of the product
     * @return Product name 
     */
    public String getStrProductName() {
        return strProductName;
    }

    /**
     * Sets the product name
     * @param strProductName - Name of the product
     */
    public void setStrProductName(String strProductName) {
        this.strProductName = strProductName;
    }

    /**
     * Returns the category of the product
     * @return type of product
     */
    public String getStrProductCategory() {
        return strProductCategory;
    }

    /**
     * Sets the type of product
     * @param strProductCategory - category of the product
     */
    public void setStrProductCategory(String strProductCategory) {
        this.strProductCategory = strProductCategory;
    }

    /**
     * Returns the price per unit of the product
     * @return - product price per unit
     */
    public String getStrProductPrice() {
        return strProductPrice;
    }

    /**
     * Set the price per unit of a particular product type
     * @param strProductPrice 
     */
    public void setStrProductPrice(String strProductPrice) {
        this.strProductPrice = strProductPrice;
    }

    /**
     * Returns the number of units in stock of a particular product
     * @return number of units
     */
    public String getStrProductQuantity() {
        return strProductQuantity;
    }

    /**
     * Set the number of units of the product
     * @param strProductQuantity - number of units
     */
    public void setStrProductQuantity(String strProductQuantity) {
        this.strProductQuantity = strProductQuantity;
    }

    /**
     * Returns the error/success message of a particular call
     * @return - Message
     */
    public String getStrInfo() {
        return strInfo;
    }

    /**
     * Sets the error/success message
     * @param strInfo - Message
     */
    public void setStrInfo(String strInfo) {

        this.strInfo = strInfo;
    }

    /**
     * Method to clear the text from the fields
     */
    public void resetFields() {
         
        strProductName = "";
        strProductPrice = "";
        strProductCategory = "";
        strProductQuantity = "";         
     }
         
    /**
     * Method to ban/unban the user by the administrator
     * @param username - Name of the customer/user to ban/unban
     */
    public void banUser(String username) {

        objReqProcessor.setBanStatus(username);              
    }
    
    /**
     * Deletes the product completely from the webshop inventory
     * @param productName - Name of the product
     */
    public void deleteProduct(String productName) {

        objReqProcessor.removeProduct(productName);
    }
    
    /**
     * Method used to update the product stock, price or category
     */
    public void updateProduct() {
        
        if(strProductName.equals(""))
        {
            strInfo = "Product name cannot be empty";
            return;
        }
        
        if(strProductCategory.equals("") && strProductPrice.equals("") && strProductQuantity.equals(""))
        {
            strInfo="Product category/cost/stock cannot be empty";
            return;
        }
        
        double cost = 0;
        try
        {
            cost = Double.parseDouble(strProductPrice);
        }
        catch(NumberFormatException ex)
        {
             strInfo = "Cost should be a numerical value";
            return;
        }
        
        int stock = 0;
        try
        {
            stock = Integer.parseInt(strProductQuantity);
        }
        catch(NumberFormatException ex)
        {
             strInfo = "Stock should be an integer value";
            return;
        }
        
        if(objReqProcessor.addProduct(strProductName,strProductCategory,cost,stock,false))
        {
            strInfo = "Product updated successfully";
            resetFields();
        } else {
            strInfo = "No such product exists";
        }
    }
    
    /**
     * Method to add a new product into the inventory
     */
     public void addProduct() {
         
        if(strProductName.equals(""))
        {
            strInfo = "Product name cannot be empty";
            return;
        }
        
        if(strProductCategory.equals(""))
        {
            strInfo = "Product should be of some category";
            return;
        }
        
        if(strProductPrice.equals(""))
        {
            strInfo = "Cost cannot be empty";
            return;
        }
        
        if(strProductQuantity.equals(""))
        {
            strInfo = "Stock cannot be empty";
            return;
        }
        
        double cost = 0;
        try
        {
            cost = Double.parseDouble(strProductPrice);
        }
        catch(NumberFormatException ex)
        {
             strInfo = "Cost should be a numerical value";
            return;
        }
        
        int stock = 0;
        try
        {
            stock = Integer.parseInt(strProductQuantity);
        }
        catch(NumberFormatException ex)
        {
             strInfo = "Stock should be an integer value";
            return;
        }

        if(objReqProcessor.addProduct(strProductName,strProductCategory,cost,stock,true))
        {
            strInfo = "Product added successfully";
            resetFields();
        } else {
            strInfo = "Product with same name already exists. Use a different name or update existing name.";
        }
    }    
}
