package no.arktekk.training.spring.repository.impl;

import java.util.ArrayList;
import java.util.List;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AuctionRepository;


/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class StubAuctionRepository implements AuctionRepository {

    private List<Auction> auctions = new ArrayList<Auction>();

    public List<Auction> listAllRunningAuctions() {
        return auctions;
    }

    public Auction findById(Double auctionId) {
        if (auctionId == 0) {
            return new Auction(0d, "Test auction");
        }
        for (Auction auction : auctions) {
            if (auction.id().equals(auctionId)) {
                return auction;
            }
        }
        throw new IllegalArgumentException("Auction Id not found");
    }

    public void createNewAuction(Auction auction) {
        auctions.add(auction);
    }
}
