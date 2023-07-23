package com.mazasoft.msscbrewery.web.mappers;

import com.mazasoft.msscbrewery.domain.Beer;
import com.mazasoft.msscbrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto convertBeerToDto(Beer beer);

    Beer convertDtoToBeer(BeerDto dto);
}
