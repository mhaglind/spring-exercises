package no.arktekk.training.spring.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AuctionRepository;

/**
 * @author <a href="mailto:marten@haglind.com">Marten Haglind</a>
 */
public class KeyedAuctionRepository implements AuctionRepository {

    private int initialCapacity;

    Map<Double, Auction> auctionsDataStore;

    @Override
    public List<Auction> listAllRunningAuctions() {
        return new ArrayList<Auction>(auctionsDataStore.values());
    }

    @Override
    public Auction findById(Double auctionId) {
        Auction auction = auctionsDataStore.get(auctionId);
        if (auction == null) {
            throw new IllegalArgumentException("Auction Id not found");
        } else {
            return auction;
        }

    }

    @Override
    public void createNewAuction(Auction auction) {
        auctionsDataStore.put(auction.id(), auction);
    }

    public void setInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    @PostConstruct
    public void initStorage() {
        auctionsDataStore = new HashMap<Double, Auction>(initialCapacity);
    }
}
