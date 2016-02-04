package no.arktekk.training.spring.service.impl;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AuctionRepository;
import no.arktekk.training.spring.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Service
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;

    @Autowired
    public AuctionServiceImpl(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public void newAuction(Auction auction) {
        auctionRepository.store(auction);
    }
    
    @Override
    public void storeTwoAuctions(Auction auction1, Auction auction2) {
        auctionRepository.store(auction1);
        auctionRepository.store(auction2);
    }
    
}
