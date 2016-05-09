package com.testcode.statestreet.implementation;

import com.testcode.statestreet.interfaces.FxPrice;
import com.testcode.statestreet.interfaces.PriceProvider;
import com.testcode.statestreet.interfaces.PriceSource;
import com.testcode.statestreet.interfaces.ReferenceRateCalculator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by mateusdasilva on 08/05/2016.
 */
public class RateCalculatorReference implements ReferenceRateCalculator {

    private Set<Market>marketList;



    public void onConfiguration(Configuration configuration) {

        int sizeOfMarket = configuration.getSize();
        marketList = new HashSet<Market>(sizeOfMarket);

        for ( int listOfMarket =0; listOfMarket < sizeOfMarket; listOfMarket ++){
            PriceSource source = configuration.getSource(listOfMarket);
            PriceProvider provider = configuration.getProvider(listOfMarket);
            Market market = new Market(source, provider);
            marketList.add(market);

        }
    }

    public void onFxPrice(FxPrice fxPrice) {
        Map<Market, FxPrice> priceByMarket = new HashMap();

        Market market = new Market(fxPrice.getSource(),fxPrice.getProvider());

        if (marketList.contains(market)){
            if(fxPrice.isStale()){
                priceByMarket.remove(market);
            }else{
                priceByMarket.put(market,fxPrice);
            }
        }
        //TODO: stale price where to add NaN values?

    }

    public FxPrice calculate() {
        //TODO: Here where we calculate the median price. how to?



        return null;
    }
}
