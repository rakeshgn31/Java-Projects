/*
 * Welcome to NetBeans...!!!
 */
package viewhandler;

import inputsprocessor.CurrencyConvertorBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author admin
 */
@ManagedBean(name = "UIHandlerBean")
@SessionScoped
public class ConvertorUIHandler implements Serializable {
    
    private String fromCurrency;
    private String toCurrency;
    private float amountToConvert;
    private float conversionAmount;
    private final ArrayList<String> currenciesList;
    
    @EJB
    private CurrencyConvertorBean convertorBean;
    
    public ConvertorUIHandler() {
        
        fromCurrency = "";
        toCurrency = "";
        amountToConvert = 0;
        conversionAmount = 0;
        
        currenciesList = new ArrayList<>();
        InitCurrenciesList();
    }
    
    private void InitCurrenciesList() {
        currenciesList.add("EUR");
        currenciesList.add("INR");
        currenciesList.add("GBP");
        currenciesList.add("SEK");
        currenciesList.add("USD");
    }
    
    public String getFromCurrency() {
        return fromCurrency;
    }
    
    public void setFromCurrency (String sFromCurrency) {
        this.fromCurrency = sFromCurrency;
    }
    
    public String getToCurrency() {
        return toCurrency;
    }
        
    public void setToCurrency (String sToCurrency) {
        this.toCurrency = sToCurrency;   
    }
    
    public float getAmountToConvert() {
        return amountToConvert;
    }
    
    public void setAmountToConvert(float amtToConvert) {
        amountToConvert = amtToConvert;
    }
    
    public float getConversionAmount() {
        return conversionAmount;
    }
    
    public void setConversionAmount(float resAmount) {
        conversionAmount = resAmount;
    }
    
    public List<String> getCurrenciesList() {
        return currenciesList;
    }

    public void convertAmount() {
        conversionAmount = convertorBean.convertAmount(fromCurrency, toCurrency, amountToConvert);
    }
}
