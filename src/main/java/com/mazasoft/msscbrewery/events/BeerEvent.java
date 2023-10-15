package com.mazasoft.msscbrewery.events;

import com.mazasoft.msscbrewery.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = 3293973619705099908L;

    private final BeerDto beerDto;
}
