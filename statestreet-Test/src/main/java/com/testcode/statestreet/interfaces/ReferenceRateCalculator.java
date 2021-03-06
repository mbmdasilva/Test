package com.testcode.statestreet.interfaces;

import com.sun.istack.internal.Nullable;

/**
 * Created by mateusdasilva on 08/05/2016.
 */
public interface ReferenceRateCalculator {
    void onConfiguration(Configuration configuration);

    void onFxPrice(FxPrice fxPrice);

    FxPrice calculate();

    interface Configuration {
        int getSize();

        PriceSource getSource(int index);

        @Nullable
        PriceProvider getProvider(int index);
    }
}
