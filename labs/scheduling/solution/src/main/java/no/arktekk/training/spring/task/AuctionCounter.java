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

	@Scheduled(fixedRate = 10000)
	public void countAuctions() {
		int nrOfAuctions = repo.listAllRunningAuctions().size();
		System.out.println(new StringBuffer().append(new Date())
				.append(" there are ").append(nrOfAuctions)
				.append(" running auctions."));
	}
}
