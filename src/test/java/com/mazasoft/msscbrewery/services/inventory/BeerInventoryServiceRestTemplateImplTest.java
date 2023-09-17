package com.mazasoft.msscbrewery.services.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {
    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnhandInventory() {
        UUID testUuid = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
        //todo evolve to use UPC
        Integer qoh = beerInventoryService.getOnhandInventory(testUuid);
        System.out.println(qoh);

    }
}