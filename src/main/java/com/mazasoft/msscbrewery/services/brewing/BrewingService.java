package com.mazasoft.msscbrewery.services.brewing;

import com.mazasoft.msscbrewery.config.JmsConfig;
import com.mazasoft.msscbrewery.domain.Beer;
import com.mazasoft.msscbrewery.events.BrewBeerEvent;
import com.mazasoft.msscbrewery.repositories.BeerRepository;
import com.mazasoft.msscbrewery.services.inventory.BeerInventoryService;
import com.mazasoft.msscbrewery.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;

    private final JmsTemplate jmsTemplate;

    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());

            log.debug("Min Onhand is:" + beer.getMinOnHand());
            log.debug("Inventory is: " + invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
