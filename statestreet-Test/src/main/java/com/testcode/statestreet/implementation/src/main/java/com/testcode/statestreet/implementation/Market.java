package com.testcode.statestreet.implementation;


import com.testcode.statestreet.enumeration.PriceProvider;
import com.testcode.statestreet.enumeration.PriceSource;

/**
 * Created by mateusdasilva on 09/05/2016.
 */
public class Market {
    private static int PRICE_SOURCES_CONT = PriceSource.values().length;
    PriceSource priceSource;
    PriceProvider priceProvider;

    public Market (PriceSource priceSource,PriceProvider priceProvider){
        this.priceProvider = priceProvider;
        this.priceSource = priceSource;
    }
public  int hashCode(){
    int hashCde = priceSource.ordinal() + (priceProvider != null ? PRICE_SOURCES_CONT+priceProvider.ordinal():0);
            return hashCde;
 }
    public boolean equals(Object otherMarket){
        if (!(otherMarket instanceof Market)){
            return false;
        }


    return priceSource == ((Market)otherMarket).priceSource && priceProvider == ((Market)otherMarket).priceProvider;
    }

}
