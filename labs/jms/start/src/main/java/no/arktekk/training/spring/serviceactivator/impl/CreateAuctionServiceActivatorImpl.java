package no.arktekk.training.spring.serviceactivator.impl;

import javax.annotation.PostConstruct;

import no.arktekk.training.spring.service.AuctionService;
import no.arktekk.training.spring.serviceactivator.CreateAuctionServiceActivator;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateAuctionServiceActivatorImpl implements
        CreateAuctionServiceActivator {

    @Autowired
    private AuctionService auctionService;

    @PostConstruct
    public void init() {
        System.out.println("Initializing CreateAuctionServiceActivatorImpl");
    }

}
