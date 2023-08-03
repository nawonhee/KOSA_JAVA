import java.util.Scanner;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dao.ProductDAOArray;
import com.my.product.dao.ProductDAOInterface;
import com.my.product.dao.ProductDAOList;
import com.my.product.dto.Product;

public class ProductUser {
	Scanner sc = new Scanner(System.in);
	
	ProductDAOInterface dao = new ProductDAOArray();
	//ProductDAOArray dao = new ProductDAOArray();
	//ProductDAOInterface dao = new ProductDAOList();
	
	void findAll() throws FindException{
		System.out.println(">>상품 전체목록<<");
		Product[] all1 = dao.selectAll();
		if(all1 == null) {
			System.out.println("상품이 없습니다"); //출력됨
		}else {
			for(Product p: all1) {
				System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
			}
		}
		System.out.println("----------------");
	}
	void findByProdNo() throws FindException{
		System.out.println(">>상품 번호로 검색<<");
		System.out.print("상품번호를 입력하세요: ");
		String prodNo = sc.nextLine();
		
		Product p;
		p = dao.selectByProdNo(prodNo);
		if(p == null) {
			System.out.println("상품이 없습니다"); 
		}else{
			//출력됨
			//System.out.println(p.prodNo +"번호 상품의 상품명:" + p.prodName + ", 가격:" + p.prodPrice);
			System.out.println(p.getProdNo() +"번호 상품의 상품명:" + p.getProdName() + ", 가격:" + p.getProdPrice());  //prodNo가 private가 될 경우
		}
	}
	void add(){
		System.out.println(">>상품 추가<<");
		System.out.print("상품번호를 입력하세요: ");
		String prodNo = sc.nextLine();
		System.out.print("상품명을 입력하세요: ");
		String prodName = sc.nextLine();
		System.out.print("상품가격을 입력하세요: ");
		String prodPrice = sc.nextLine();
		
		Product p = new Product(prodNo, prodName, Integer.parseInt(prodPrice)); 
		
		try{
			dao.insert(p);
		} catch(AddException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	void modify() throws ModifyException, FindException{
		System.out.println("변경할 상품의 번호를 입력하세요: ");
		String prodNo = sc.nextLine();
		System.out.println("변경할 상품명: ");
		String prodName = sc.nextLine();
		System.out.println("변경할 가격: ");
		int prodPrice = sc.nextInt();
		
		Product p = new Product(prodNo, prodName, prodPrice);
	}
	
	void delete() throws RemoveException, FindException {
		System.out.println("삭제할 상품의 번호를 입력하세요: ");
		String prodNo = sc.nextLine();
		
	}
	
	public static void main(String[] args) throws FindException {
		ProductUser user = new ProductUser();
		
		while(true){
			System.out.println("작업을 선택하세요: 상품전체목록-1, 상품번호로검색-2, 상품추가-3, 종료-9");
			String opt = user.sc.nextLine();
			if(opt.equals("1")) {
				user.findAll();
			}
			else if(opt.equals("2")){
				user.findByProdNo();
			}
			else if(opt.equals("3")){
				user.add();
					}
			else if(opt.equals("9")){
				break;
			}
		}
		
				
		
		/*
		
		//추가후 상품전체목록검색
		System.out.println(">>상품 전체목록<<");
		Product[] all2 = dao.selectAll();
		if(all2 == null) {
			System.out.println("상품이 없습니다"); 
		}else {
			//출력됨
			for(Product p: all2) {
				System.out.println(p.prodNo + ":" + p.prodName + ":" + p.prodPrice);
			}
		}
		System.out.println("----------------");

		System.out.println(">>상품 번호로 검색<<");
		Product p;
		p = dao.selectByProdNo("C0002");
		if(p == null) {
			System.out.println("상품이 없습니다"); 
		}else{
			//출력됨
			System.out.println(p.prodNo +"번호 상품의 상품명:" + p.prodName + ", 가격:" + p.prodPrice);
		}
		
		System.out.println(">>저장 안된 상품번호로 검색<<");
		p = dao.selectByProdNo("F0001"); 
		if(p == null) {
			System.out.println("상품이 없습니다"); //출력됨
		}else {
			System.out.println(p.prodNo +"번호 상품의 상품명:" + p.prodName + ", 가격:" + p.prodPrice);
		}	
		*/	
	
	}
}
