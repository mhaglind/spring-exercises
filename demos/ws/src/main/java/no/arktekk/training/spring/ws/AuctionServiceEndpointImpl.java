package no.arktekk.training.spring.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.service.AuctionService;

import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "AuctionWebService")
public class AuctionServiceEndpointImpl implements AuctionServiceEndpoint {

	@Autowired
	AuctionService service;

	@WebMethod(operationName = "RegisterAuction")
	public void addAuction(Auction auction) {
		service.newAuction(auction);
	}

	@WebMethod(operationName = "ViewAuction")
	public Auction viewAuction(String auctionId) {
		return service.findById(auctionId);
	}
	
	@WebMethod(operationName = "Ping")
	public boolean ping() {
		return true;
	}

}
