import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.my.customer.dto.Customer;

public class ObjectIOTest {
	public static void write() {
		/*
		 * 스트림 : 바이트단위 출력스트림
		 * 필터스트림 : 객체단위 출력스트림
		 * 목적지 : 파일
		 */
		String fileName = "a.ser";
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(new Date()); //얼렸다
			Customer c = new Customer("id1","p1","n1","a1"); //프로그램 끝나면 객체 휘발되니까 얼려보자
			oos.writeObject(c); //얼렸는데 잘 안 된다, serializable 인터페이스 구현해보자 -> Customer 클래스 가서 완료
 		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(oos !=null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void read() {
		String fileName = "a.ser";
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
			Object obj1 = ois.readObject();
			System.out.println(obj1.toString());
			
			Object obj2 = ois.readObject();
			System.out.println(obj2); //obj2.toString() 자동호출
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(ois!=null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		write();
		read(); //얼린 시점의 시간값이 나옴, 직렬화할 때 부모의 멤버변수는 직렬화가 안 됨
	}

}
