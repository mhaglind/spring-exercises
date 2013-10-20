package no.arktekk.training.spring.serviceactivator;

import no.arktekk.training.spring.domain.Auction;

import org.springframework.transaction.annotation.Transactional;

public interface CreateAuctionServiceActivator {

    @Transactional
    public abstract void createNewAuction(Auction auction);

}