package com.my.product.dao;

import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.exception.AddException;
import com.my.product.dto.Product;

public interface ProductDAOInterface { //객체 생성의 용도 x
	/**
	 * 상품을 저장소에 저장한다. 저장소가 꽉찬경우 "저장소가 꽉찼습니다"메시지가 출력된다
	 * @param product 상품
	 * @throws AddException 
	 */
	void insert(Product product) throws AddException; //abstract 생략된 형태 (=abstract void insert(Product product);와 같음
	/**
	 * 상품번호에 해당 상품을 저장소에 검색하여 반환한다
	 * @param prodNo 상품번호
	 * @return 상품객체. 번호에 해당 상품을 찾지 못하면 null을 반환한다 
	 * @throws FindException 번호에 해당 상품을 찾지 못하면 예외발생한다
	 */
	Product selectByProdNo(String no) throws FindException;
	/**
	 * 저장소에 저장된 상품들만 반환한다
	 * @return 상품들. 저장소에 저장된 상품이 한개도 없으면 null을 반환한다
	 * @throws FindException 저장소에 저장된 상품이 한개도 없으면 예외발생한다
	 */
	Object selectAll() throws FindException;
	
	/**
	 * 변경할 상품의 상품번호와 같은 상품을 저장소에서 찾아낸다
	 * 변경할 상품의 상품명 또는 가격으로 저장소 상품정보를 변경한다
	 * 
	 * @param p 변경할 상품. 상품번호는 반드시 필요, p가격이 0이면 변경안함
	 *                    p의이름이 null이면 저장소 상품 이름 변경안함
	 * @throws ModifyException 변경할 상품이 없으면 예외발생한다
	 */
	void update(Product p) throws ModifyException;
	
	/**
	 * 상품을 삭제한다
	 * @param prodNo 상품번호
	 * @throws RemoveException 삭제할 상품이 없으면 예외발생한다
	 */
	void delete(String prodNo) throws RemoveException;
}
