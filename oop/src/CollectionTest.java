import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.my.customer.dto.Customer;
import com.my.product.dto.Product;

public class CollectionTest {
	public static void test(Collection c) {
		c.add("one");
		c.add(Integer.valueOf(2));
		c.add(Float.valueOf(3.0F));
		c.add("one");
		c.add(Boolean.valueOf(false));
		c.add(new Product("C0001", "아메리카노", 1000));
		c.add(new Product("C0001", "라테", 2000));
		
		System.out.println("요소갯수:"+c.size());
		System.out.println(c); //c.toString() 자동호출
		
		c.remove("one");
		System.out.println("one객체 삭제됨");
		/*
		Iterator it = c.iterator();
		while(it.hasNext()) {; //방문할 요소가 있니
			it.next(); //요소 방문
			System.out.println("저장된 요소:"+e);
		}*/
		for(Object e:c) {
			System.out.println("저장된 요소:"+e);
		}
	}
	
	public static void test(Map m) {
		m.put("one", new Date());
		m.put("two", new String("second"));
		m.put("one", Integer.valueOf(3));
		m.put("four", Float.valueOf(4.5F));
		m.put("five", Boolean.valueOf(false));
		m.put(new Product("C0001",  "아메리카노", 1000), "커피1");
		m.put(new Product("C0001",  "라테", 2000), "커피2");
		m.put(new Customer("id1",  "p1"), "고객1");
		m.put(new Customer("id1",  "p2"), "고객2");
		
		System.out.println("요소갯수:"+m.size());
		System.out.println(m);
		Product p = new Product();
		p.setProdNo("C0001");
		System.out.println("C0001상품키의 값은 "+m.get(p)); //커피2로 덮어쓰기
	}
	public static void main(String[] args) {
		Collection c;
		c = new ArrayList();
		test(c);
		System.out.println("----------");
		test(new HashSet());
		System.out.println("----------");
		test(new HashMap());
	}

}
