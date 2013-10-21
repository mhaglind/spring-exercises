package no.arktekk.training.spring.service.impl;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AuctionRepository;
import no.arktekk.training.spring.service.AuctionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 * @author <a href="mailto:marten@haglind.com">Marten Haglind</a>
 */
public class DefaultAuctionService implements AuctionService {
	
    private AuctionRepository auctionRepository;

    public List<Auction> allRunningAuctions() {
        return auctionRepository.listAllRunningAuctions();
    }

    public Auction findById(Double auctionId) {
        return auctionRepository.findById(auctionId);
    }

	public void setAuctionRepository(AuctionRepository auctionRepository) {
		this.auctionRepository = auctionRepository;
	}
    
}
