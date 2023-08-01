class A{
	static int si;
	int i;
	
	static void sm() {
		//System.out.println(this.i);
		System.out.println(si);
	}
	void m() {
		System.out.println(this.i);
	}
}

public class StaticTest {
	public static void main(String[] args) {
		System.out.println(A.si);
		//System.out.println(A.i); //i는 인스턴스 변수이므로 객체 생성해야 함
		
		A a1, a2;
		a1 = new A();
		a2 = new A();
		
		System.out.println(a1.i); //0
		System.out.println(a2.i); //0
		System.out.println(a1.si);
		System.out.println(a2.si);
		
		a1.i++;
		a1.si++;
		
		System.out.println(a1.i); //1
		System.out.println(a1.si); //1
		System.out.println(A.si); //1
		
		System.out.println(a2.i); //0
		System.out.println(a2.si); //1
		
		a1.m();
		//A.m(); //안 됨
		
		A.sm();
		a1.sm();
	}
}
