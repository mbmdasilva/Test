package com.testcode.statestreet.implementation;

import com.testcode.statestreet.interfaces.FxPrice;
import com.testcode.statestreet.enumeration.PriceProvider;
import com.testcode.statestreet.enumeration.PriceSource;
import com.testcode.statestreet.interfaces.ReferenceRateCalculator;

import java.util.*;

/**
 * Created by mateusdasilva on 08/05/2016.
 */
public class ReferenceRateCalculatorImpl implements ReferenceRateCalculator {

    //There are approx. 50 different price sources and 30 different price providers.
    // The number of markets in the configuration varies from 3 to 15.
    // --> meaning a small number of data so safe to keep in memory

    private Set<Market>marketList;
    //onConfiguration() is called to pass the list of markets we want to calculate the reference rate across.
    //onConfiguration() may be called again, which will be followed by onFxPrice() and calculate() calls.
    //There is a separate instance of ReferenceRateCalculator for each currency pair.
    //Every instance of ReferenceRateCalculator runs on a single thread, which means no synchronization required.
    // --> meaning no synchronization required
    Map<Market, FxPrice> marketFxPriceMap = new HashMap();


    public void onConfiguration(Configuration configuration) {
        //The configuration gets changed infrequently, 1 or 2 times per day.
        // --> meaning this method can be heavy and we should do as much as
        // possible here in order to do less on onFxPrice() and calculate()
        int sizeOfMarket = configuration.getSize();
        marketList = new HashSet<Market>(sizeOfMarket);

        for ( int index =0; index < sizeOfMarket; index ++){
            PriceSource source = configuration.getSource(index);
            PriceProvider provider = configuration.getProvider(index);
            //There are approx. 50 different price sources and 30 different price providers.
            // The number of markets in the configuration varies from 3 to 15.
            // --> there are duplicates and Market should implement equals() & hashcode()
            // accordingly to avoid duplicates on the Set--done


            Market market = new Market(source, provider);
            marketList.add(market);

        }
    }

    public void onFxPrice(FxPrice fxPrice) {
        //onFxPrice() is called on each market data update. By calling getSource() and
        // getProvider() you know there the price came from. The price can be stale,
        // in which case bid and offer are not defined (may be NaN or something else).
        //Each onFxPrice() call is followed by calculate() call,
        // which produces the reference rate. Bid and offer on the returning price
        // are always set to the same value - the reference rate.
        // --> think about what you would need to calculate the reference rate and see how you can do it here!

        int indexOfMiddleElement;
        Market market = new Market(fxPrice.getSource(),fxPrice.getProvider());

        if (marketList.contains(market)){

            if(fxPrice.isStale()){
                marketFxPriceMap.remove(market);

            }else{

                marketFxPriceMap.put(market,fxPrice);
            }
        }

        ArrayList<FxPrice> fxPrices = new ArrayList<FxPrice>(marketFxPriceMap.values());
        int size = fxPrices.size();
        FxPrice fxAveragePrice = FxPriceImpl.getStalePrice();
        if (size >0){
                Double averagePrice;
                int indexOfPrice = size/2;
                fxAveragePrice = fxPrices.get(indexOfPrice);
                averagePrice= (fxAveragePrice.getOffer() + fxAveragePrice.getBid())/2d;
            }
        

    }
    FxPrice fxReferenceRate;
    public FxPrice calculate() {
        //The rate gets recalculated after each market data update, thousands of ticks per second.
        // --> Performance really important!
        // --> think about what could be a minimal implementation. for example, how you can get to this possible implementation?
        //
        //   FxPrize referencePrize;
        //   public FxPrice calculate() {
        //       return referencePrize; // and make the calculations in onFxPrice() directly
        //   }
        
        return fxReferenceRate;
    }
}
