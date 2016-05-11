package com.testcode.statestreet.implementation;

import com.testcode.statestreet.interfaces.FxPrice;
import com.testcode.statestreet.enumeration.PriceProvider;
import com.testcode.statestreet.enumeration.PriceSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by mateusdasilva on 09/05/2016.
 */
public class FxPriceImpl implements FxPrice {

    private static final boolean IS_STALE = true;
    public static final boolean IS_NOT_STALE = false;


    boolean isStale;
    double bid;
    double offer;
    PriceSource priceSource;
    PriceProvider priceProvider;

    public FxPriceImpl(double bid, double offer, boolean isStale, PriceSource priceSource, PriceProvider priceProvider ){

        this.bid = bid;
        this.offer = offer;
        this.isStale = isStale;
        this.priceSource = priceSource;
        this.priceProvider = priceProvider;

    }
    public static FxPrice getStalePrice (){
        FxPriceImpl stalePrice = new FxPriceImpl(Double.NaN, Double.NaN, IS_STALE,null,null);
        return stalePrice;
    }


    public double getBid() {
        return bid;
    }

    public void setBid(){
        this.bid = bid;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(){
        this.offer=offer;
    }

    public boolean isStale() {

        return isStale;
    }

    public PriceSource getSource() {
        return priceSource;
    }

    public PriceProvider getProvider() {
        return priceProvider;
    }


}
