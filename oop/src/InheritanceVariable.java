class PVariable1{
	int a;
	void pm1() {
		System.out.println("PVariable1의 a="+a);
	}
}

class PVariable2 extends PVariable1{
	void pm2() {
		System.out.println("PVariable2의 a="+a);
	}
}

class CVariable extends PVariable2{
	String a;
	void cm() {
		System.out.println("CVariable의 a="+this.a+", PVariable1의 a="+super.a);
	}
}

public class InheritanceVariable {

	public static void main(String[] args) {
		PVariable1 pv1 = new PVariable1();
		pv1.a = 9;
		
		PVariable2 pv2 = new PVariable2();
		pv2.a = 99;
		
		CVariable cv = new CVariable();
		cv.a = "HELLO";
		
		cv.cm();
		cv.pm2();
		cv.pm1();
	}

}
