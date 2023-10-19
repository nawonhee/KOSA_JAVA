package com.my.customer.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString 
public class Customer extends Person {
	private String id;
	transient private String pwd; 
	
	public Customer() {
		super();
	}

	public Customer(String id, String pwd, String name) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}

}
