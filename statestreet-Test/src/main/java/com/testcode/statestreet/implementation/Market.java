package com.testcode.statestreet.implementation;


import com.testcode.statestreet.interfaces.PriceProvider;
import com.testcode.statestreet.interfaces.PriceSource;

/**
 * Created by mateusdasilva on 09/05/2016.
 */
public class Market {

    PriceSource priceSource;
    PriceProvider priceProvider;

    public Market (PriceSource priceSource,PriceProvider priceProvider){
        this.priceProvider = priceProvider;
        this.priceSource = priceSource;
    }

   // TODO: Need to cater for PriceProvider being null for some PriceSources
}
