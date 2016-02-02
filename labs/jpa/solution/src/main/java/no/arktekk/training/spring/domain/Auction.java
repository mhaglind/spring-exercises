package no.arktekk.training.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author <a href="mailto:marten@haglind.com">Mårten Haglind</a>
 */
@Entity
public class Auction {

	@Id
	@GeneratedValue
	private Long id;
	private double minimumPrice;
	private String description;

	@SuppressWarnings("unused")
	private Auction() {
	}

	public Auction(double minimumPrice, String description) {
		this.minimumPrice = minimumPrice;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public double getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
