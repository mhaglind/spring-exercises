package no.arktekk.training.spring.controller;

import static no.arktekk.training.spring.form.Transformations.asAuctionForm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.form.AuctionForm;
import no.arktekk.training.spring.service.AuctionService;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Controller
public class IndexController {
	private final AuctionService auctionService;

	@Autowired
	public IndexController(AuctionService auctionService) {
		this.auctionService = auctionService;
	}

	@RequestMapping("/index.html")
	public @ModelAttribute("auctions") List<AuctionForm> auctionList(ModelMap map) {
		map.addAttribute("username", getUsername());
		List<AuctionForm> forms = new ArrayList<AuctionForm>();
		for (Auction auction : auctionService.allRunningAuctions()) {
			forms.add(asAuctionForm.apply(auction));
		}
		return forms;

	}

	private String getUsername() {
		String username = null;
		try {
			User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			username = principal.getUsername();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return username;
	}
}
