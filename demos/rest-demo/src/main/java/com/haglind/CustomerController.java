package com.haglind;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository repo;

	@RequestMapping("/customers")
	List<Customer> customers() {
		return repo.findAll();
	}

	@RequestMapping(value = "/customers/{sid}", method = RequestMethod.GET)
	Customer customer(@PathVariable("sid") Long sid) {
		return repo.findOne(sid);
	}

	@RequestMapping(value = "/customers", 
			method = RequestMethod.POST, 
			consumes = "application/json")
	Customer addCustomer(@RequestBody Customer customer) {
		return repo.save(customer);
	}

	@PostConstruct
	public void addSomeCustomers() {
		repo.save(new Customer("Kalle"));
		repo.save(new Customer("Lisa"));
	}
}
