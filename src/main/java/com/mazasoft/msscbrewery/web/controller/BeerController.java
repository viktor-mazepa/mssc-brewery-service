package com.mazasoft.msscbrewery.web.controller;

import com.mazasoft.msscbrewery.domain.Beer;
import com.mazasoft.msscbrewery.web.services.BeerService;
import com.mazasoft.msscbrewery.web.model.BeerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    private final ModelMapper modelMapper;

    @Autowired
    public BeerController(BeerService beerService, ModelMapper modelMapper) {
        this.beerService = beerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        Beer beer = beerService.getBeerById(beerId);
        if (beer == null) {
            return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(convertToDto(beer), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Valid BeerDto beerDto) {
        beerService.saveNewBeer(convertToEntity(beerDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Valid BeerDto beerDto) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private Beer convertToEntity(BeerDto beerDto) {
        return modelMapper.map(beerDto, Beer.class);
    }

    private BeerDto convertToDto(Beer beer) {
        return modelMapper.map(beer, BeerDto.class);
    }
}
