package no.arktekk.training.spring.task;

import java.util.Date;

import no.arktekk.training.spring.repository.AuctionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AuctionCounter {

	@Autowired
	AuctionRepository repo;

	@Scheduled(fixedRate=8000)
	public void countAuctions() {
		System.out.println(new Date() + " there are " +
				repo.listAllRunningAuctions().size() + " running auctions.");
	}
}
