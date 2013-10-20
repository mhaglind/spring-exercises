package no.arktekk.training.spring.controller;

import static no.arktekk.training.spring.form.Transformations.asAuctionForm;

import java.util.ArrayList;
import java.util.List;

import no.arktekk.training.spring.domain.Album;
import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.form.AuctionForm;
import no.arktekk.training.spring.service.AuctionService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Controller
public class NewAuctionController {
	private final AuctionService auctionService;

	@Autowired
	public NewAuctionController(AuctionService auctionService) {
		this.auctionService = auctionService;
	}

	@RequestMapping("/addAuction.html")
	public String showDetails(@RequestParam double price,
			@RequestParam String description, ModelMap model) {

		DateTime now = new DateTime();
		auctionService.newAuction(new Auction(null, price, description, now
                .plusDays(-1), now.plusDays(7), new ArrayList<Album>()));

        List<AuctionForm> forms = new ArrayList<AuctionForm>();
        for (Auction auction : auctionService.allRunningAuctions()) {
            forms.add(asAuctionForm.apply(auction));
        }
        model.addAttribute("auctions", forms);
        
		return "index";
	}

}
