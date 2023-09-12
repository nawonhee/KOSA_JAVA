package com.my.product.dao;

import java.util.List;

import com.my.exception.FindException;
import com.my.product.dto.Product;

public interface ProductRepository {
	/**
	 * 상품을 오름차순 정렬된 상품들 중 시작행에서부터 끝행까지 상품들을 검색한다
	 * @param startRow 시작행
	 * @param ndRow 끝행
	 * @return 상품들
	 * @throws FindException db와의 연결 실패시 예외발생
	 */
	List<Product> selectAll(int startRow, int endRow) throws FindException;
	/**
	 * 전체 상품 수를 검색한다
	 * @return 전체상품수
	 * @throws FindException DB와의 연결 실패하면 예외 발생한다
	 */
	int selectCount() throws FindException;
}
