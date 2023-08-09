package com.my.product.dto;

import java.util.Objects;

public class Product {
	private String prodNo;
	private String prodName;
	private int prodPrice;
	//private Integer prodPrice;
	
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
	/*
	public boolean equals(Product p) {
		return (this.prodNo).equals(p.prodNo);
	}
	
	public boolean equals(String no) {
		return (this.prodNo).equals(no);
	}
	*/
	
	@Override
	public int hashCode() {
		return Objects.hash(prodNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		//if (getClass() != obj.getClass())
		//	return false;
		Class currentClass = this.getClass();
		Class paramClass = obj.getClass();
		if(currentClass != paramClass) {
			return false;
		}
		Product other = (Product) obj;
		return Objects.equals(prodNo, other.prodNo);
	}
	
	/* 이렇게 해도 된다
	 @Override
	 public boolean equals(Object obj){
	 	if(obj==null){
	 		return false;
	 	}
	 	if(obj instanceof Product){
	 		Product product = (Product)obj;
	 		if(this.prodNo.equals(product.prodNo)){
	 			return true;
	 		}
	 	}
	 	return false;
	 }
	*/
	
	public String toString() {
		return prodNo+":"+prodName+":"+prodPrice+"\n";
	}
}
