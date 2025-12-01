package com.pratice.p6;

import java.math.BigDecimal;

public class Cars {
    protected String Manufacturer;
    protected String TYPE;
    protected BigDecimal MinPrice;
    protected BigDecimal price;
    public String getManufacturer() {
        return Manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }
    public String getTYPE() {
        return TYPE;
    }
    public void setTYPE(String tYPE) {
        TYPE = tYPE;
    }
    public BigDecimal getMinPrice() {
        return MinPrice;
    }
    public void setMinPrice(BigDecimal minPrice) {
        MinPrice = minPrice;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    
}
