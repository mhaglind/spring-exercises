package com.haglind;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long sid;

    private String name;
    
    
	public Customer(String name) {
		super();
		this.name = name;
	}
	
	@SuppressWarnings("unused")
	private Customer() {
	}
	
	public Long getSid() {
		return sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [sid=").append(sid).append(", name=").append(name).append("]");
		return builder.toString();
	}
    
    
}
