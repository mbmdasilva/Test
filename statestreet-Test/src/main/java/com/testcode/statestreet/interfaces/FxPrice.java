package com.testcode.statestreet.interfaces;

import com.sun.istack.internal.Nullable;

/**
 * Created by mateusdasilva on 08/05/2016.
 */
public interface FxPrice {

    double getBid();

    double getOffer();

    boolean isStale();

    PriceSource getSource();
    @Nullable
    PriceProvider getProvider();
}
