package com.mazasoft.msscbrewery.services;

import com.mazasoft.msscbrewery.domain.Beer;
import com.mazasoft.msscbrewery.repositories.BeerRepository;
import com.mazasoft.msscbrewery.web.controller.NotFoundException;
import com.mazasoft.msscbrewery.web.mappers.BeerMapper;
import com.mazasoft.msscbrewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.convertBeerToDto(repository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.convertBeerToDto(repository.save(beerMapper.convertDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = repository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beer.getBeerStyle());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.convertBeerToDto(repository.save(beer));
    }
}
