/*
 * Welcome to NetBeans...!!!
 */
package market;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Item implements Serializable {

    String strName;
    int nPrice;
    
    Item() {
        strName = "";
        nPrice = 0;
    }
    
    public String getName() {
        return strName;
    }
    
    public void setName(String sItemName) {
        strName = sItemName;
    }
    
    public int getPrice() {
        return nPrice;
    }
    
    public void setPrice(int nItemPrice) {
        nPrice = nItemPrice;
    }
}
