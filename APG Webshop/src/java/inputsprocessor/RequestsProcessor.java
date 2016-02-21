/*
 * Welcome to NetBeans...!!!
 */

package inputsprocessor;

import storage.OrderSummary;
import storage.Inventory;
import storage.Customers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author admin
 */
@Stateful
public class RequestsProcessor {

    @PersistenceContext(unitName = "APGnomePU")
    private EntityManager em;

    /**
     * Registers the supplied customer
     * @param username - Username of the customer
     * @param password - Password set by the customer
     * @return  0 = If username already exists
     *          -1 otherwise
     */
    public int registerUser(String username, String password) {

        int result = checkUser(username, password, false);
        if (result == 0) {
            return 0; // Username already exists
        }
        
        Customers user = new Customers(username, password, false, false);
        em.persist(user);
        
        return -1;
    }

    /**
     * Evaluates if the credentials supplied by the customer is valid
     * @param username - Username of the customer
     * @param password - Password set by the customer
     * @param isLogin - true if it is to check the credentials during login process
     *                  false if it is to check the username is available during registration/sign up
     * @return  0 = Successful Login/Username is already in use
     *          1 = No such user exists/Username is available
     *          2 = Password mismatch
     *          3 = Customer is banned by the Administrator
     */
    public int checkUser(String username, String password, boolean isLogin) {
 
        Customers user = em.find(Customers.class, username);
        if (user == null) {
            System.out.println("Rakesh user not found");
            return 1; // No such user exists
        }
        if (isLogin) {

            if (user.getIsBanned()) {
                return 3; // User is banned
            }
            if (!user.getPassword().equals(password)) {
                return 2; // Password mismatch
            }
        }

        return 0; // Login successful
    }

    /**
     * Retrieves a list of product categories from the inventory
     * @return - list of product types
     */
    public List<String> getCategories() {

        List<String> results = em.createQuery("SELECT DISTINCT inv.productCategory FROM Inventory inv where inv.productPrice > 0").getResultList();
        System.out.println("Rakesh categories: " + results.toString());

        return results;
    }

    /**
     * Returns list of products belonging to a supplied product type
     * @param selectedCategory - type of product
     * @return - list of products
     */
    public List<Inventory> getProductsOfType(String selectedCategory) {

        Query qry;
        if (!selectedCategory.equals("AdminAll")) {
            qry = em.createQuery("SELECT inv FROM Inventory inv where inv.productCategory=:category and inv.productPrice > 0");
            qry.setParameter("category", selectedCategory);
        } else {
            qry = em.createQuery("SELECT inv FROM Inventory inv ORDER BY inv.productCategory");
        }
        
        List<Inventory> results = qry.getResultList();
        return results;
    }

    /**
     * Checks out from the customer's shopping basket after the bill and payment details
     * @param username - Customer placing the order
     * @param basketContent - Various products and their quantity being ordered
     * @param totalAmount - Total amount of the products being ordered
     * @return order ID on successful order
     *         empty otherwise
     */
    public String checkoutFromBasket(String username, HashMap<Inventory, Integer> basketContent, double totalAmount) {

        try {
            for (Map.Entry<Inventory, Integer> entry : basketContent.entrySet()) {                                

                // Updates the existing product stock
                System.out.println(entry.getKey() + "/" + entry.getValue());
                Inventory product = em.find(Inventory.class, entry.getKey().getProductName());
                product.setProductQuantity(product.getProductQuantity() - basketContent.get(product));
                em.persist(product);
            }
            
            // persist new order placed by the user
            byte[] basketData = SerializationUtils.serialize(basketContent);
            OrderSummary newOrd = new OrderSummary(username, basketData, totalAmount);
            em.persist(newOrd);
            
            String oid = "";
            Query createQuery = em.createQuery("SELECT max(ord.orderID) FROM OrderSummary ord where ord.username=:username");
            createQuery.setParameter("username", username);
            oid = createQuery.getSingleResult().toString();
            return oid;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    /**
     * Returns the previous purchase history of the customer
     * @param username - Name of the customer
     * @return - list of previous orders
     */
    public List<OrderSummary> getCustomerOrderSummary(String username) {

        return em.createQuery("SELECT ord FROM OrderSummary ord where ord.username=:username ORDER BY ord.orderID DESC").setParameter("username", username).getResultList();
    }

    /**
     * Returns a list of all the registered customers - Use by Admin only
     * @return - List of customers
     */
    public List<Customers> getAllCustomers() {

        return em.createQuery("SELECT cust FROM Customers cust where cust.username<>'admin' ORDER BY cust.username ASC").getResultList();
    }

    /**
     * Sets the ban status of the supplied customer
     * @param username - Name of the customer
     */
    public void setBanStatus(String username) {

        Customers customer = em.find(Customers.class, username);
        customer.setIsBanned(!customer.getIsBanned());
        em.persist(customer);
    }

    /** 
     * Removes the supplied product from the inventory
     * @param prodName - Name of the product
     */
    public void removeProduct(String prodName) {

        Inventory product = em.find(Inventory.class, prodName);
        em.remove(product);
    }

    /**
     * Method used to add a new product or update an existing product stock
     * @param prodName - Name of the product
     * @param prodCategory - Type of the product
     * @param prodPrice - Price per unit of the product
     * @param prodQuantity - Number of units of the product
     * @param isAdd - Indicates add/update function
     * @return true on successful operation otherwise false
     */
    public boolean addProduct(String prodName, String prodCategory, double prodPrice, int prodQuantity, boolean isAdd) {

        try{
            Inventory product = null;
            product = em.find(Inventory.class, prodName);
            if(isAdd && product != null)
            {
                return false; // Another product with same name found
            }

            if (!isAdd) {
                // Update the product
                if(product == null) {
                    return false; // No product with this name found to update
                }
                if (!prodCategory.equals("")) {
                    product.setProductCategory(prodCategory);
                }
                if (prodPrice != 0) {
                    product.setProductPrice(prodPrice);
                }
                if (prodQuantity != 0) {
                    product.setProductQuantity(prodQuantity);
                }
            } else {
                // Add a new product
                product = new Inventory(prodName, prodCategory, prodPrice, prodQuantity);
            }

            em.persist(product);
            return true;
            
        } catch (Exception ex) {
            System.out.println("Exception - " + ex.getMessage());
            return false;
        }
    }
}
