
public class OverloadTest {
	public static void plus(int a, int b) {
		System.out.println("합은 "+(a+b));
	}
	public static void plus(int a, int b, int c) {
		System.out.println("합은 "+(a+b+c));
	}
	
	public static void plus(double a, double b) {
		System.out.println("합은 "+(a+b));
	}
	
	public static void main(String[] args) {
		plus(1,2);
		plus(1,2,3);
		plus(1, 2.0);
	}
}
