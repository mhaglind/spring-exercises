package no.arktekk.training.spring.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import no.arktekk.training.spring.domain.Auction;

@WebService(serviceName = "AuctionWebService")
// @SOAPBinding(parameterStyle=ParameterStyle.BARE, style=Style.RPC,
// use=Use.ENCODED)
public interface AuctionServiceEndpoint {

	@WebMethod(operationName = "RegisterAuction")
	void addAuction(Auction auction);

	@WebMethod(operationName = "ViewAuction")
	Auction viewAuction(String auctionId);

	@WebMethod(operationName = "Ping")
	public boolean ping();
}