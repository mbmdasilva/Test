package com.testcode.statestreet.implementation;

import com.testcode.statestreet.interfaces.FxPrice;
import com.testcode.statestreet.interfaces.PriceProvider;
import com.testcode.statestreet.interfaces.PriceSource;

/**
 * Created by mateusdasilva on 09/05/2016.
 */
public class FXPrice implements FxPrice {
    private static final boolean IS_STALE = true;
    private static final boolean IS_NOT_STALE = false;

    boolean isStale;
    double bid;
    double offer;
    PriceSource priceSource;
    PriceProvider priceProvider;

    public FXPrice (double bid, double offer, boolean isStale,PriceSource priceSource, PriceProvider priceProvider ){

        this.bid = bid;
        this.offer = offer;
        this.isStale = isStale;
        this.priceSource = priceSource;
        this.priceProvider = priceProvider;

    }

   //TODO need to add any other method here? Stale is part of this implementation should we apply stale here?
    public double getBid() {
        return bid;
    }

    public double getOffer() {
        return offer;
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
