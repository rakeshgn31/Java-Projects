/*
 * Welcome to NetBeans...!!!
 */
package storage;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "CURRENCYCONVERSIONRATES")
public class ConversionRates implements Serializable {

    private static final long serialVersionUID = 123456789L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONVERSIONIDENTIFIER")
    private String strConversionIdentifier;
    
    @Column(name = "CONVERSIONRATE")
    private float fConversionRate;
    
    public String getStrConversionIdentifier() {
        return strConversionIdentifier;
    }

    public void setStrConversionIdentifier(String strConversionIdentifier) {
        this.strConversionIdentifier = strConversionIdentifier;
    }

    public float getConversionRate() {
        return fConversionRate;
    }
    
    public void setConversionRate(float fConversionRate) {
        this.fConversionRate = fConversionRate;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (strConversionIdentifier != null ? strConversionIdentifier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the strConversionIdentifier fields are not set
        if (!(object instanceof ConversionRates)) {
            return false;
        }
        ConversionRates other = (ConversionRates) object;
        if ((this.strConversionIdentifier == null && other.strConversionIdentifier != null) || (this.strConversionIdentifier != null && !this.strConversionIdentifier.equals(other.strConversionIdentifier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "storage.ConversionRates[ id=" + strConversionIdentifier + " ]";
    }  
}
