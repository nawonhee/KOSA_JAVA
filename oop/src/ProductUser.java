import java.io.IOException;
import java.util.List;
import java.util.Properties;
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
	
	//ProductDAOInterface dao = new ProductDAOArray();
	//ProductDAOArray dao = new ProductDAOArray();
	//ProductDAOInterface dao = new ProductDAOList();
	ProductDAOInterface dao; //의존성 주입 , 외부로부터 결정되기 때문
	ProductUser() {
		//dao = new ProductDAOList();
		Properties env = new Properties();
		try{
			env.load(ProductUser.class.getResourceAsStream("my.properties")); //productuser클래스 있는 경로에서 my.properties 파일을 읽어와라
			String className = env.getProperty("product.dao"); //product.dao가 키값, 해당하는 value를 가져와라
			Class clazz = Class.forName(className); //clazz에 저장해라
			//clazz.newInstance();
			clazz.getDeclaredConstructor().newInstance(); //매개변수없는 생성자 이용
			Object obj = clazz.getDeclaredConstructor().newInstance();
			dao = (ProductDAOInterface)obj;
			System.out.println(dao.getClass().getName());
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void findAll() {
		System.out.println(">>상품 전체목록<<");
		try {
			//Product[] all1 = dao.selectAll();
			Object obj = dao.selectAll();
			if(obj instanceof Product[]) {
				Product[] all1 = (Product[])obj;				
				for(int i = 0; i< all1.length; i++){
					Product p = all1[i];
					//System.out.println(p.prodNo + ":" + p.prodName + ":" + p.prodPrice);
					System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
				}
			}else if(obj instanceof List) {
				List<Product> list = (List)obj;
				for(int i=0; i<list.size(); i++) {
					Product p = list.get(i);
					System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
				}
			}

		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("----------------");
	}
	
	/*
	
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
	*/
	
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
	void add() {
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
	
	void modify() throws ModifyException{
		System.out.println("변경할 상품의 번호를 입력하세요: ");
		String prodNo = sc.nextLine();
		
		try {
			dao.selectByProdNo(prodNo);
		}catch(FindException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}
		
		System.out.println("변경할 상품명: ");
		String prodName = sc.nextLine();
		if(prodName.equals("")) {
			prodName = null;
		}
		System.out.println("변경할 가격: ");
		String prodPrice = sc.nextLine();
		
		Product p = new Product(prodNo, prodName, Integer.parseInt(prodPrice));
		
		try{
			dao.update(p);
		} catch(ModifyException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	void remove() throws RemoveException {
		System.out.println("삭제할 상품의 번호를 입력하세요: ");
		String prodNo = sc.nextLine();
		
		try{
			dao.delete(prodNo);
		} catch(RemoveException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) throws FindException, ModifyException, RemoveException, AddException {
		ProductUser user = new ProductUser();
		
		while(true){
			System.out.println("작업을 선택하세요: 상품전체목록-1, 상품번호로검색-2, 상품추가-3, 상품수정-4, 상품삭제-5, 종료-9");
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
			else if(opt.equals("4")){
				user.modify();
			}
			else if(opt.equals("5")){
				user.remove();
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
