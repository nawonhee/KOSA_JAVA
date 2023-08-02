import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Scanner;

public class ReflectionTest {
	
	public static void test(String className) throws ClassNotFoundException{
		Class c = Class.forName(className); //런타임 다이나믹 로드된 클래스
		Field[] fields = c.getDeclaredFields(); //런타임 다이나믹 로드된 클래스가 갖고 있는 멤버변수가 무엇이 있는지 필드 배열 타입으로 받아옴
		for(Field f:fields) {
			System.out.println(f.getName());
		}
		System.out.println("-------------");
		Method[] methods = c.getDeclaredMethods(); //런타임 다이나믹 로드된 클래스에 메서드들은 어떤 게 있는지 알아보는 것
		for(Method m : methods) {
			System.out.println(m.getName());
		}
		
		//객체 생성
		try {
			//c.newInstance(); //해당되는 클래스 타입 객체로 알아서 생성하도록 하자
			Object obj = c.getDeclaredConstructor().newInstance(); //윗부분보단 얘로 쓰는 게 맞다
			System.out.println(obj); //obj.toString() 자동호출
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.print("클래스이름을 입력하세요 ex)java.util.Date:");
		String className = sc.nextLine();
		test(className);
	}
	
}
