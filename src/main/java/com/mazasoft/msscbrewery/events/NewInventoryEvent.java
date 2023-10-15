package com.mazasoft.msscbrewery.events;

import com.mazasoft.msscbrewery.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
