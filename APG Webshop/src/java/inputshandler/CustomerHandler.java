/*
 * Welcome to NetBeans...!!!
 */

package inputshandler;

import javax.ejb.EJB;
import java.util.List;
import java.util.HashMap;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.faces.context.FacesContext;

import storage.OrderSummary;
import storage.Inventory;
import inputsprocessor.RequestsProcessor;

@ManagedBean(name = "customer")
@SessionScoped
public class CustomerHandler {
  
    String strUsername, strPassword, strConfirmPassword;
    String strRegisteredName, strSelectedProdCategory, strErrorInfo, strOrderID;    
        
    Boolean errorBoolean;
    String strErrorMessage;
    
    List<String> listProdCategory;
    List<Inventory> listProducts;
    List<OrderSummary> listOrders;    
    HashMap<Inventory, Integer> userShoppingBasket = new HashMap<>();
    
    static double basketTotalAmount;
    private static boolean isUserLoggedIn;
    
    @EJB
    private RequestsProcessor objReqProcessor;
    
    /**
     * Creates a new instance of UserHandler
     */
    public CustomerHandler() {
        
        strUsername = "";
        strPassword = "";
        strConfirmPassword = "";
        strRegisteredName = "";
        strSelectedProdCategory = "";
        strErrorInfo = "";
        strOrderID = "";
        strErrorMessage = "";
                
        basketTotalAmount = 0;
        isUserLoggedIn = false;
    }
    
    /**
     * Returns the registered username of the customer
     * @return name string
     */
    public String getStrRegisteredName() {
        return strRegisteredName;
    }

    /**
     * Sets the registered name of the customer
     * @param strRegisteredName - Name of the customer
     */
    public void setStrRegisteredName(String strRegisteredName) {
        this.strRegisteredName = strRegisteredName;
    }
    
    /**
     * Returns the username of the customer
     * @return username
     */
    public String getStrUsername() {
        return strUsername;
    }

    /**
     * Sets the username of the customer
     * @param strUsername - Username
     */
    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    /**
     * Returns the password set by the customer
     * @return password string
     */
    public String getStrPassword() {
        return strPassword;
    }

    /**
     * Sets the password entered by the customer
     * @param strPassword - Password string
     */
    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }
    
    /**
     * Returns the password re-entered by the customer during registration
     * @return Re-entered password string
     */
    public String getStrConfirmPassword() {
        return strConfirmPassword;
    }

    /**
     * Sets the re-entered password by the customer during registration
     * @param strConfirmPassword - Password String
     */
    public void setStrConfirmPassword(String strConfirmPassword) {
        this.strConfirmPassword = strConfirmPassword;
    }
    
    /**
     * Returns a boolean that tells if the customer/admin (user) has logged on to the system
     * @return true/false
     */
    public boolean isIsUserLoggedIn() {
        return isUserLoggedIn;
    }

    /**
     * Set when the user has logged on/logged out of the system
     * @param isLogged - true/false
     */
    public void setIsUserLoggedIn(boolean isLogged) {
        isUserLoggedIn = isLogged;
    }
    
    /**
     * Returns true/false that tells if any error has occurred during the call flow
     * @return true/false
     */
    public Boolean getErrorBoolean() {
        return errorBoolean;
    }

    /**
     * Set true/false when an error occurs in the course of execution
     * @param errorBoolean - true/false
     */
    public void setErrorBoolean(Boolean errorBoolean) {
        this.errorBoolean = errorBoolean;
    }

    /**
     * Returns the error message set when an error occurred
     * @return Error message
     */
    public String getStrErrorMessage() {
        return strErrorMessage;
    }

    /**
     * Sets the error message when an error occurred
     * @param strErrorMessage - Error Message
     */
    public void setStrErrorMessage(String strErrorMessage) {
        this.strErrorMessage = strErrorMessage;
    }
    
    /**
     * Returns a list of product types
     * @return - List of Strings representing types
     */
    public List<String> getListProdCategory() {
        return listProdCategory;
    }
    
    /**
     * Sets the list of product types
     * @param listProdCategory - List of product category strings
     */
    public void setListProdCategory(List<String> listProdCategory) {
        this.listProdCategory = listProdCategory;
    }

    /**
     * Returns the order ID of a successful order in String format
     * @return order ID string
     */
    public String getStrOrderID() {
        return strOrderID;
    }

    /**
     * set an order ID in String format
     * @param strOrderID - Order ID string
     */
    public void setStrOrderID(String strOrderID) {
        this.strOrderID = strOrderID;
    }

    /**
     * Returns the list of previous orders
     * @return - list of orders
     */
    public List<OrderSummary> getListOrders() {
        listOrders = objReqProcessor.getCustomerOrderSummary(strUsername);
        return listOrders;
    }

    /**
     * Sets a list of previous orders
     * @param listOrders - list of orders 
     */
    public void setListOrders(List<OrderSummary> listOrders) {
        this.listOrders = listOrders;
    }

    /**
     * Method that returns the contents of the customer's shopping basket/cart
     * @return - Map of products vs Quantity
     */
    public HashMap<Inventory, Integer> getUserShoppingBasket() {
        return userShoppingBasket;
    }

    /**
     * Method that sets the selected products into customer's shopping basket/cart
     * @param userShoppingBasket - Map of products vs Quantity
     */
    public void setUserShoppingBasket(HashMap<Inventory, Integer> userShoppingBasket) {
        this.userShoppingBasket = userShoppingBasket;
    }

    /**
     * Returns the total amount of the items added to the basket
     * @return Total amount
     */
    public double getBasketTotalAmount() {
        return basketTotalAmount;
    }

     /**
     * Sets the total amount of the items added to the basket
     * @param basketTotalAmount - Total amount
     */
    public void setBasketTotalAmount(double basketTotalAmount) {
        CustomerHandler.basketTotalAmount = basketTotalAmount;
    }

    /**
     * Returns the selected product type
     * @return - category of product
     */
    public String getStrSelectedProdCategory() {
        return strSelectedProdCategory;
    }

    /**
     * Sets the selected type of product
     * @param strSelectedProdCategory - selected product type string
     */
    public void setStrSelectedProdCategory(String strSelectedProdCategory) {
        this.strSelectedProdCategory = strSelectedProdCategory;
    }

    /**
     * Returns the error message set during an error occurrence
     * @return - Error message
     */
    public String getStrErrorInfo() {
        return strErrorInfo;
    }

    /**
     * Sets the error message
     * @param strErrorInfo - Error string
     */
    public void setStrErrorInfo(String strErrorInfo) {
        this.strErrorInfo = strErrorInfo;
    }

    /**
     * Returns the list of products got from the inventory
     * @return - list of products
     */
    public List<Inventory> getListProducts() {
        return listProducts;
    }

    /**
     * Sets the list of products
     * @param listProducts - list of products
     */
    public void setListProducts(List<Inventory> listProducts) {
        this.listProducts = listProducts;
    }            

    /**
     * Method that checks if the supplied user is an Admin or a regular customer
     */
    public void checkIfAdmin() {

        if (isUserLoggedIn) {
            try {
                if (strUsername.equals("admin")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("AdminPage.xhtml");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("HomePage.xhtml");
                }
            } catch (IOException ex) {
                Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Method that logs an user into the system
     * @return - The next page to be displayed on successful login
     *          For Admin, next page is the Admin Home page
     *          For customer, it is the regular home page
     */
    public String login() {
        
        if (strUsername.equals("") || strPassword.equals("")) {
            errorBoolean = true;
            strErrorMessage = "Username or password cannot be empty";
            return "";
        }
        
        String strReturnValue = "";
        int result = objReqProcessor.checkUser(strUsername, strPassword, true);        
        switch (result) {
            
            case 0: // Successful Login
                errorBoolean = false;
                strErrorMessage = "";
                listProdCategory = objReqProcessor.getCategories();
                if (listProdCategory.size() > 0) {
                    if (strSelectedProdCategory.equals("")) {
                        strSelectedProdCategory = listProdCategory.get(0);
                    }
                    getProducts(strSelectedProdCategory);
                }
                
                isUserLoggedIn = true;
                if (strUsername.equals("admin")) {
                    strReturnValue = "AdminPage";
                } else {
                    strReturnValue = "HomePage";
                }
                break;

            case 1: // No such user
                errorBoolean = true;
                strErrorMessage = "Invalid Username";
                break;

            case 2: // Password mismatch
                errorBoolean = true;
                strErrorMessage = "Password mismatch";
                break;

            case 3: // User banned
                errorBoolean = true;
                strErrorMessage = "User banned by Administrator";
                break;

        }
        
        return strReturnValue;
    }

    /**
     * Registers the new customer into the system
     * @return Page to be displayed on successful registration
     */
    public String registerUser() {

        if (strRegisteredName.equals("") || strPassword.equals("") || strConfirmPassword.equals("")) {
            errorBoolean = true;
            strErrorMessage = "Username or password/confirm password cannot be empty";
            return "";
        }
        if (!strPassword.equals(strConfirmPassword)) {
            errorBoolean = true;
            strErrorMessage = "password and confirm password do not match";
            return "";
        }
        
        // If valid, call function to register the user
        int result = objReqProcessor.registerUser(strRegisteredName, strPassword);
        System.out.println("RegisterUser - result= " + result);
        if (result == 0) {
            errorBoolean = true;
            strErrorMessage = " Username is not available. Select a different username ";
            return "";
        } else {
            errorBoolean = false;
            strErrorMessage = "";
            listProdCategory = objReqProcessor.getCategories();
            if (listProdCategory.size() > 0) {
                if (strSelectedProdCategory.equals("")) {
                    strSelectedProdCategory = listProdCategory.get(0);
                }
                
                getProducts(strSelectedProdCategory);
            }
            
            isUserLoggedIn = true;
            return "HomePage";
        }
    }

    /**
     * Logs out the user (Admin/Customer) from the system
     * @return Page to be displayed
     */
    public String logout() {

        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        return "LoginPage";
    }

    /**
     * Method to get the list of products of selected category
     * @param selProdCategory - Selected Product type
     */
    public void getProducts(String selProdCategory) {
        
        strErrorMessage = "";
        strSelectedProdCategory = selProdCategory;
        listProducts = objReqProcessor.getProductsOfType(strSelectedProdCategory);
    }

    /**
     * Method to add a chosen product to the customer's shopping basket
     * @param prod - Chosen Product
     */
    public void addToBasket(Inventory prod) {
        
        strErrorMessage = prod.getProductName()+ " Added to basket successfully!";
        strErrorInfo = "";

        if (userShoppingBasket.containsKey(prod)) {
            int avbl = prod.getProductQuantity();
            int inBasket = Integer.parseInt(userShoppingBasket.get(prod) + "");
            System.out.println(userShoppingBasket.get(prod));
            if (inBasket == avbl) {
                strErrorMessage = "Stock exceeded";
                strErrorInfo = "Stock exceeded";
                return;
            }
            
            basketTotalAmount += prod.getProductPrice();
            userShoppingBasket.put(prod, inBasket + 1);
            return;
        }
        
        basketTotalAmount += prod.getProductPrice();
        userShoppingBasket.put(prod, 1);
    }

     /**
     * Method to delete a selected product from the customer's shopping basket
     * @param prod - Selected Product
     */
    public void deleteFromBasket(Inventory prod) {
        
        strErrorInfo = "";
        int inBasket = Integer.parseInt(userShoppingBasket.get(prod) + "");
        basketTotalAmount -= inBasket * prod.getProductPrice();
        userShoppingBasket.remove(prod);
    }

     /**
     * Method to reduce the number of units of a chosen product in the customer's shopping basket
     * @param prod - Chosen Product
     */    
    public void reduceQuantityInBasket(Inventory prod) {
        
        strErrorInfo = "";
        if (userShoppingBasket.containsKey(prod)) {
            int inBasket = Integer.parseInt(userShoppingBasket.get(prod) + "");
            if (inBasket == 1) {
                strErrorInfo = "Minimun quantity should be 1";
                return;
            }
            
            basketTotalAmount -= prod.getProductPrice();
            userShoppingBasket.put(prod, inBasket - 1);
        }
    }

    /**
     * Method to get the number of units of a particular product in the customer's shopping basket
     * @param prod - Chosen Product
     */
    public int getQuantity(Inventory prod) {

        if (userShoppingBasket.containsKey(prod)) {
            return userShoppingBasket.get(prod);
        } else {
            return 0;
        }
    }

    /**
     * Method to check out from the customer's shopping basket after billing and payment information entered
     * @return Page to be displayed (OrderSummary - History) on successful order
     *         else the same page is displayed with an error message
     */
    public String checkoutFromBasket() {
        
        strOrderID = objReqProcessor.checkoutFromBasket(strUsername, userShoppingBasket, basketTotalAmount);
        if (strOrderID.equals("")) {
            strErrorInfo = "Sorry...Order was not placed. Try again...";
            return "";
        } else {
            userShoppingBasket.clear();
            basketTotalAmount = 0;
            return "HistoryPage";
        }
    }
}
