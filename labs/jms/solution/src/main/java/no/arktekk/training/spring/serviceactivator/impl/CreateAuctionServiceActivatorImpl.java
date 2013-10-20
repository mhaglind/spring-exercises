package no.arktekk.training.spring.serviceactivator.impl;

import javax.annotation.PostConstruct;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.service.AuctionService;
import no.arktekk.training.spring.serviceactivator.CreateAuctionServiceActivator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CreateAuctionServiceActivatorImpl implements
		CreateAuctionServiceActivator {

	@Autowired
	private AuctionService auctionService;

	private static final Log LOG = LogFactory
			.getLog(CreateAuctionServiceActivatorImpl.class);

	@PostConstruct
	public void init() {
		if (LOG.isInfoEnabled()) {
			LOG.info("Service Activator instance initialized and open for business");
		}
	}

	@Transactional
	public void createNewAuction(Auction auction) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Message recieved. Creating auction '"
					+ auction.description() + "'");
		}
		auctionService.newAuction(auction);
	}

}
