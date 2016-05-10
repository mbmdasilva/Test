package com.testcode.statestreet.implementation;

import com.testcode.statestreet.interfaces.FxPrice;
import com.testcode.statestreet.interfaces.PriceProvider;
import com.testcode.statestreet.interfaces.PriceSource;
import com.testcode.statestreet.interfaces.ReferenceRateCalculator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class RateCalculatorReference implements ReferenceRateCalculator {

    //There are approx. 50 different price sources and 30 different price providers.
    // The number of markets in the configuration varies from 3 to 15.
    // --> meaning a small number of data so safe to keep in memory
    private Set<Market>marketList;

    //onConfiguration() is called to pass the list of markets we want to calculate the reference rate across.
    //onConfiguration() may be called again, which will be followed by onFxPrice() and calculate() calls.
    //There is a separate instance of ReferenceRateCalculator for each currency pair.
    //Every instance of ReferenceRateCalculator runs on a single thread, which means no synchronization required.
    // --> meaning no synchronization required
    public void onConfiguration(Configuration configuration) {
        //The configuration gets changed infrequently, 1 or 2 times per day.
        // --> meaning this method can be heavy and we should do as much as possible here in order to do less on onFxPrice() and calculate()
        int sizeOfMarket = configuration.getSize();
        marketList = new HashSet<Market>(sizeOfMarket);

        for ( int listOfMarket =0; listOfMarket < sizeOfMarket; listOfMarket ++){
            PriceSource source = configuration.getSource(listOfMarket);
            PriceProvider provider = configuration.getProvider(listOfMarket);
            //There are approx. 50 different price sources and 30 different price providers.
            // The number of markets in the configuration varies from 3 to 15.
            // --> there are duplicates and Market should implement equals() & hashcode() accordingly to avoid duplicates on the Set
            Market market = new Market(source, provider);
            marketList.add(market);

        }
    }


    public void onFxPrice(FxPrice fxPrice) {
        //onFxPrice() is called on each market data update. By calling getSource() and getProvider() you know there the price came from. The price can be stale, in which case bid and offer are not defined (may be NaN or something else).
        //Each onFxPrice() call is followed by calculate() call, which produces the reference rate. Bid and offer on the returning price are always set to the same value - the reference rate.
        // --> think about what you would need to calculate the reference rate and see how you can do it here!
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
        //The rate gets recalculated after each market data update, thousands of ticks per second.
        // --> Performance really important!
        // --> think about what could be a minimal implementation. for example, how you can get to this possible implementation?
        //
        //   FxPrize referencePrize;
        //   public FxPrice calculate() {
        //       return referencePrize; // and make the calculations in onFxPrice() directly
        //   }
        
        //TODO: Here where we calculate the median price. how to?



        return null;
    }
}
