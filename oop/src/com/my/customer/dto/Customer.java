package com.my.customer.dto;

public class Customer extends Person {
	private String id;
	private String pwd;
	
	public Customer() {
		super();
	}

	public Customer(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	public Customer(String id, String pwd, String name, String address) {
		//this.id = id;
		//this.pwd = pwd;  중복되어도 써도 되지만 코드를 간결히 하기 위해!
		this(id, pwd);
		this.name = name;
		this.address = address;
		//super(name, address); -> 위에 this(id, pwd)있으면 호출 못함
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String toString() {
		return "id는"+id+", pwd는"+pwd+", name은"+name+"address는"+address;
	}
}
