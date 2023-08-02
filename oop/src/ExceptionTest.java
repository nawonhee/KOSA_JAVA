import java.util.InputMismatchException;
import java.lang.ArithmeticException;
import java.util.Scanner;

public class ExceptionTest {
	public static void m(Object obj) {
		try{  
			String str = obj.toString();
			System.out.println("객체정보:"+str);
		}catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}finally {
			System.out.println("finally");
		}
	}
	public static void m(int num) {
		try {
			System.out.println("99를 "+num+"로 나눈 나머지값은 "+(99%num));
		}catch(ArithmeticException e){
			String msg = e.getMessage();
			System.out.println(msg);
			System.out.println("0을 입력하셨습니다");
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("0이아닌 숫자값을 입력하세요:");
		int num;
	
		try {
			num = sc.nextInt();
		}catch(InputMismatchException e) {
			//e.printStackTrace();
			//System.out.println("숫자값을 입력하세요");
			System.out.println("숫자값을 입력하지 않았습니다. 1로 자동설정합니다");
			num=1;
		}
		m(num);
		
		Object o;
		o = new Object();
		m(o);
		
		o = null;
		m(o); //null.toString()을 못해서 오류남, 그래서 예외처리 해주었음
	}
}
