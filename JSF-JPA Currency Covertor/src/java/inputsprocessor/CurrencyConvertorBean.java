/*
 * Welcome to NetBeans...!!!
 */
package inputsprocessor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import storage.ConversionRates;

/**
 *
 * @author admin
 */
@Stateless
public class CurrencyConvertorBean {

    @PersistenceContext(unitName = "CurrencyConversionRatesPU")    
    private EntityManager em;
    
    private final String INR_2_EUR = "INR2EUR";
    private final String INR_2_SEK = "INR2SEK";
    private final String INR_2_USD = "INR2USD";
    private final String INR_2_GBP = "INR2GBP";
    private final String SEK_2_EUR = "SEK2EUR";
    private final String SEK_2_USD = "SEK2USD";
    private final String SEK_2_GBP = "SEK2GBP";
    private final String EUR_2_USD = "EUR2USD";
    private final String EUR_2_GBP = "EUR2GBP";
    private final String USD_2_GBP = "USD2GBP";
    
    public CurrencyConvertorBean() {}
    
    public float convertAmount(String strFromCurrency, String strToCurrency, float fAmountToConvert) {

        float fConversionAmount = 0;
        float fConversionRate = 0;
        
        fConversionRate = getConversionRate(strFromCurrency, strToCurrency);        
        fConversionAmount = fConversionRate * fAmountToConvert;

        return fConversionAmount;
    }
    
    private float getConversionRate(String strFromCurrency, String strToCurrency) {

        float fConversionRate = 0;
        
        if("INR".equals(strFromCurrency) && "EUR".equals(strToCurrency)) {
            fConversionRate = getConversionRateFromDB(INR_2_EUR);
            
        } else if ("INR".equals(strToCurrency) && "EUR".equals(strFromCurrency)) {
            fConversionRate = getConversionRateFromDB(INR_2_EUR);
            fConversionRate = getInverseOf(fConversionRate);
            
        } else if ("INR".equals(strFromCurrency) && "SEK".equals(strToCurrency)) {
            fConversionRate = getConversionRateFromDB(INR_2_SEK);
            
        } else if ("INR".equals(strToCurrency) && "SEK".equals(strFromCurrency)) {
            fConversionRate = getConversionRateFromDB(INR_2_SEK);
            fConversionRate = getInverseOf(fConversionRate);
            
        } else if ("INR".equals(strFromCurrency) && "GBP".equals(strToCurrency)) {
            fConversionRate = getConversionRateFromDB(INR_2_GBP);
            
        } else if ("INR".equals(strToCurrency) && "GBP".equals(strFromCurrency)) {
            fConversionRate = getConversionRateFromDB(INR_2_GBP);
            fConversionRate = getInverseOf(fConversionRate);
            
        } else if ("INR".equals(strFromCurrency) && "USD".equals(strToCurrency)) {
            fConversionRate = getConversionRateFromDB(INR_2_USD);
            
        } else if ("INR".equals(strToCurrency) && "USD".equals(strFromCurrency)) {
            fConversionRate = getConversionRateFromDB(INR_2_USD);
            fConversionRate = getInverseOf(fConversionRate);
            
        } else if ("SEK".equals(strFromCurrency) && "EUR".equals(strToCurrency)) {
            fConversionRate = getConversionRateFromDB(SEK_2_EUR);
            
        } else if ("SEK".equals(strToCurrency) && "EUR".equals(strFromCurrency)) {
            fConversionRate = getConversionRateFromDB(SEK_2_EUR);
            fConversionRate = getInverseOf(fConversionRate);
            
        } else if ("SEK".equals(strFromCurrency) && "GBP".equals(strToCurrency)) {
            fConversionRate = getConversionRateFromDB(SEK_2_GBP);
            
        } else if ("SEK".equals(strToCurrency) && "GBP".equals(strFromCurrency)) {
            fConversionRate = getConversionRateFromDB(SEK_2_GBP);
            fConversionRate = getInverseOf(fConversionRate);
            
        } else if ("SEK".equals(strFromCurrency) && "USD".equals(strToCurrency)) {
            fConversionRate = getConversionRateFromDB(SEK_2_USD);
            
        } else if ("SEK".equals(strToCurrency) && "USD".equals(strFromCurrency)) {
            fConversionRate = getConversionRateFromDB(SEK_2_USD);
            fConversionRate = getInverseOf(fConversionRate);
            
        } else if ("EUR".equals(strFromCurrency) && "GBP".equals(strToCurrency)) {
            fConversionRate = getConversionRateFromDB(EUR_2_GBP);
            
        } else if ("EUR".equals(strToCurrency) && "GBP".equals(strFromCurrency)) {
            fConversionRate = getConversionRateFromDB(EUR_2_GBP);
            fConversionRate = getInverseOf(fConversionRate);
            
        } else if ("EUR".equals(strFromCurrency) && "USD".equals(strToCurrency)) {
            fConversionRate = getConversionRateFromDB(EUR_2_USD);
            
        } else if ("EUR".equals(strToCurrency) && "USD".equals(strFromCurrency)) {
            fConversionRate = getConversionRateFromDB(EUR_2_USD);
            fConversionRate = getInverseOf(fConversionRate);
            
        } else if ("USD".equals(strFromCurrency) && "GBP".equals(strToCurrency)) {
            fConversionRate = getConversionRateFromDB(USD_2_GBP);
            
        } else if ("USD".equals(strToCurrency) && "GBP".equals(strFromCurrency)) {
            fConversionRate = getConversionRateFromDB(USD_2_GBP);
            fConversionRate = getInverseOf(fConversionRate);
        }
        
        return fConversionRate;
    }

    private float getInverseOf(float fConversionRate) {

        float fInverseRate = 0;
        
        if(fConversionRate != 0) {
            fInverseRate = (float)(1/fConversionRate);
        } else {
            fInverseRate = 0;
        }
        
        return  fInverseRate;
    }
    
    private float getConversionRateFromDB (String strIdentifier) {

        float fConversionRate = 0;        
        
        ConversionRates entity =  em.find(ConversionRates.class, strIdentifier);
        if (entity == null) {
            System.out.println("Conversion rate for identifier " + strIdentifier + " not found...");
        } else {
            fConversionRate = entity.getConversionRate();
        }
        
        return fConversionRate;
    }
}
