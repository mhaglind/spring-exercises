package no.arktekk.training.spring.ws;

import java.util.ArrayList;

import no.arktekk.training.spring.domain.Album;
import no.arktekk.training.spring.domain.Auction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

	public static void main(String[] args) throws Exception {

		// http://localhost:8088/AuctionWebService?wsdl

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:clientContext.xml");

		AuctionServiceEndpoint webService = context
				.getBean(AuctionServiceEndpoint.class);

		System.out.println("Server available: " + webService.ping());

		
		ArrayList<Album> albums = new ArrayList<Album>();
		Album album = new Album("123", "Title", "Artist", null, null);
		albums.add(album);
		Auction auction = new Auction("567", 15d, "An auction", null, null,
				albums);
		auction.setStartDateTime("2013-03-15 12:00:00");
		auction.setExpiresDateTime("2016-03-15 12:00:00");
		webService.addAuction(auction);

		
		Auction auctionFromWs = webService.viewAuction("888");
		System.out.println("Got from server: " + auctionFromWs.description());
		
	}

}
