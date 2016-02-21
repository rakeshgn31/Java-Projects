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
@Table(name = "CUSTOMERS")
@NamedQueries({
                @NamedQuery(name = "Customers.findAll", query = "SELECT cust FROM Customers cust")
              })
public class Customers implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "USERNAME")
    private String username;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PASSWORD")
    private String password;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ADMIN")
    private boolean isAdmin;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_BANNED")
    private boolean isBanned;

    /**
     * Creates a new instance of Customers class
     * Default no-argument constructor
     */
    public Customers() {}

     /**
     * Creates a new instance of Customers class with the chosen username   
     * @param username - username of the customer
     */
    public Customers(String username) {
        
        this.username = username;
    }

     /**
     * Creates a new instance of Customers class with the supplied data
     * @param username - username of the customer
     * @param password - Password set by the customer
     * @param isAdmin  - Indicates if the user is an Admin or a regular customer
     * @param isBanned - Indicates if the user is banned by the Administrator
     */
    public Customers(String username, String password, boolean isAdmin, boolean isBanned) {
        
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
    }

    /**
     * Returns the username of the customer
     * @return Username
     */
    public String getUsername() {
        
        return username;
    }

    /**
     * Sets the username chosen by the customer
     * @param username
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * Returns the password for a specific customer
     * @return password string
     */
    public String getPassword() {

        return password;
    }

    /**
     * Sets the password for the customer
     * @param password 
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * Returns if the user is an Admin
     * @return true if Admin else false
     */
    public boolean getIsAdmin() {

        return isAdmin;
    }

     /**
     * Sets if the user is an Admin
     * @param isAdmin true if Admin else false
     */
    public void setIsAdmin(boolean isAdmin) {

        this.isAdmin = isAdmin;
    }

    /**
     * Returns a boolean if the user is banned or not
     * @return true if banned else false
     */
    public boolean getIsBanned() {

        return isBanned;
    }

    /**
     * Sets the ban status for the customer
     * @param isBanned 
     */
    public void setIsBanned(boolean isBanned) {

        this.isBanned = isBanned;
    }

    @Override
    public int hashCode() {
        
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customers)) {
            return false;
        }
        
        Customers other = (Customers) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "Entities.Customers[ USERNAME=" + username + " ]";
    }

    /**
     * Gets the ban status of the customer
     * @return "Ban" if banned else "Unban" is returned
     */
    public String getBanStatus() {

        if (isBanned) {
            return "Unban";
        } else {
            return "Ban";
        }
    }
}
