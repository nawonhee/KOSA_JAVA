package com.my.product.dao;
import com.my.exception.AddException;
import com.my.product.dto.Product;

public class ProductDAOArray implements ProductDAOInterface{
	private Product[] products = new Product[5];
	int productslength = products.length;
	private int totalCnt;
	/**
	 * 상품을 저장소에 저장한다. 저장소가 꽉찬경우 "저장소가 꽉찼습니다"메시지가 출력된다
	 * @param product 상품
	 */
	public void insert(Product product) throws AddException{
		/*
		if(totalCnt==productslength) 
			System.out.println("저장소가 꽉찼습니다");
		else {
			products[totalCnt] = product;
			totalCnt++;
		}
		*/
		for(int i=0;i<totalCnt;i++) {
			products[i].getProdNo().equals(product.getProdNo());{
				//System.out.println("이미 존재하는 상품입니다");
				//return;
				throw new AddException("이미 존재하는 상품입니다");
			}
		}
		try {
			products[totalCnt] = product;
			totalCnt++; 
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new AddException("저장소가 꽉찼습니다. 저장된 상품수:"+totalCnt); //강제 예외 발생
			//System.out.println("저장소가 꽉찼습니다. 저장된 상품수:"+totalCnt);
		}
	}
	/**
	 * 상품번호에 해당 상품을 저장소에 검색하여 반환한다
	 * @param prodNo 상품번호
	 * @return 상품객체. 번호에 해당 상품을 찾지 못하면 null을 반환한다 
	 */
	public Product selectByProdNo(String prodNo) {
		for(int i=0;i<totalCnt;i++) {
			if(products[i].getProdNo().equals(prodNo)) {
				return products[i];
			}
		}
		return null;
	}
	/**
	 * 저장소에 저장된 상품들만 반환한다
	 * @return 상품들. 저장소에 저장된 상품이 한개도 없으면 null을 반환한다
	 */
	public Product[] selectAll() {
		Product[] p = new Product[totalCnt];
		if(totalCnt==0)
			return null;
		for(int i=0;i<totalCnt;i++) {
			if(products[i] != null)
				p[i] = products[i];
		}
		return p;
	}
}
