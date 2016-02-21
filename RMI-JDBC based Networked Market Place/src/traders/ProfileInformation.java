/*
 * Welcome to NetBeans...!!!
 */
package traders;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class ProfileInformation implements Serializable {
    
    private String strTraderName;
    
    private String strPassword;
    
    private String strPasswordStrength;
    
    private int nNumberOfItemsBought;
    
    private int nNumberOfItemsSold;
    
    public ProfileInformation() {}
    
    public String getTraderName() { return strTraderName; }
    
    public void setTraderName(String sTraderName) { this.strTraderName = sTraderName; }
    
    public String getPassword() { return strPassword; }
    
    public void setPassword(String sPassword) { this.strPassword = sPassword; }
    
    public String getPwdStrength() { return strPasswordStrength; }
    
    public void setPwdStrength(String sPwdStrength) { this.strPasswordStrength = sPwdStrength; }

    public int getNumItemsBought() { return nNumberOfItemsBought; }
    
    public void setNumItemsBought(int nNumItemsBought) { this.nNumberOfItemsBought = nNumItemsBought; }

    public int getNumItemsSold() { return nNumberOfItemsSold; }
    
    public void setNumItemsSold(int nNumItemsSold) { this.nNumberOfItemsSold = nNumItemsSold; }    
}
