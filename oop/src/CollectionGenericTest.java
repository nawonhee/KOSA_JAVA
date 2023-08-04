import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.text.html.HTMLDocument.Iterator;

//CollectionTest에 잇는 노란줄 없애기

public class CollectionGenericTest {
	public static void test(Collection<String> c) {
		c.add("one");
		c.add("two");
		c.add("one");
		c.add("four");
		c.add("five");
		
		System.out.println("요소갯수:"+c.size());
		System.out.println(c); //c.toString() 자동호출
		
		c.remove("one");
		System.out.println("one객체 삭제됨");
		
		for(Object e:c) {
			System.out.println("저장된 요소:"+e);
		}
	}
	public static void main(String[] args) {
		Collection<String> c;
		//c = new Collection();
		c = new ArrayList<>();
		test(c);
		System.out.println("----------");
		test(new HashSet<>());
	}

}
