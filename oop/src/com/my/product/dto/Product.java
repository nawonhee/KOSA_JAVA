package com.my.product.dto;

public class Product {
	private String prodNo;
	private String prodName;
	private int prodPrice;
	
	public Product(){}  //얘 없으면 디폴트 생성자 안 만들어짐
	public Product(String prodNo, String prodName){
		//this.prodNo = prodNo;
		//this.prodName = prodName;
		this(prodNo, prodName, 0);
		System.out.println("....");
	}
	public Product(String prodNo, String prodName, int prodPrice){
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	public void setProdNo(String prodNo) {
		if(prodNo.length()!=5) {
			System.out.println("상품번호는 5자리이어야 합니다");
			return;
		}
		this.prodNo = prodNo;
	}
	
	public String getProdNo() {
		return prodNo;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public String getProdName() {
		return prodName;
	}
	
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	
	public int getProdPrice() {
		return prodPrice;
	}
}
