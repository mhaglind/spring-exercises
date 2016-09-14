package com.haglind;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Account {

	@Id
	@GeneratedValue
	Long id;
	
	String accountName;
	Long accountNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date created;

	protected Account() {
		super();
	}

	public Account(String accountName, Long accountNumber, Date created) {
		super();
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.created = created;
	}
	
	
}
