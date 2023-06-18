package com.mazasoft.msscbrewery.web.services;

import com.mazasoft.msscbrewery.domain.Beer;
import com.mazasoft.msscbrewery.repositories.BeerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional(readOnly = false)
@Log4j2
public class BeerService {

    private final BeerRepository beerRepository;

    @Autowired
    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Beer getBeerById(UUID beerId) {
        return beerRepository.findById(beerId).orElse(null);
    }

    public Collection<Beer> getAllBeers(){
        return beerRepository.findAll();
    }

    public void saveNewBeer(Beer beer) {
        beer.setCreatedDate(new Timestamp(new Date().getTime()));
        log.debug("beer = "+ beer);
        beerRepository.save(beer);
    }

    public void updateBeerById(UUID beerId, Beer beer) {
       /* Beer beerForUpdate = beerRepository.findById(beerId).orElse(null);
        beerForUpdate.setBeerName(beer.getBeerName());
        beerForUpdate.setBeerStyle(beer.getBeerStyle());
        beerForUpdate.setLastModifiedDate(new Timestamp(new Date().getTime()));
        beerRepository.save(beerForUpdate);*/
    }

}
