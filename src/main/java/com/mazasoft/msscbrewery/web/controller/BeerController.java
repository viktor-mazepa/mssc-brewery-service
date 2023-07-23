package com.mazasoft.msscbrewery.web.controller;

import com.mazasoft.msscbrewery.domain.Beer;
import com.mazasoft.msscbrewery.web.mappers.BeerMapper;
import com.mazasoft.msscbrewery.web.model.BeerDto;
import com.mazasoft.msscbrewery.web.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    private final BeerMapper beerMapper;

    @Autowired
    public BeerController(BeerService beerService, BeerMapper beerMapper) {
        this.beerService = beerService;
        this.beerMapper = beerMapper;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") @NotNull UUID beerId) {
        Beer beer = beerService.getBeerById(beerId);
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto) {
        beerService.saveNewBeer(beerMapper.convertDtoToBeer(beerDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
