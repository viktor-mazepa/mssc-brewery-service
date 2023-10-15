package com.mazasoft.msscbrewery.events;

import com.mazasoft.msscbrewery.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent{

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
