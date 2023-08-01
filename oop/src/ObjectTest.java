
public class ObjectTest {
	public static void main(String[] args) {
		Object o1;
		Object o2;
		Object o3;
		
		o1 = new Object();
		o2 = new Object();
		o3 = o1;
		
		System.out.println(o1.hashCode());
		System.out.println(o2.hashCode());
		System.out.println(o3.hashCode());
		
		System.out.println(o1 == o2); //false
		System.out.println(o1 == o3); //true
		
		System.out.println(o1.equals(o2)); //false
		System.out.println(o1.equals(o3)); //true
		
		System.out.println(o1.toString()); //java.lang.Object@123772c4
		
		Circle c1, c2;
		c1 = new Circle(5);
		c2 = new Circle(5);
		System.out.println(c1.toString()); //Cirle@xxxxx
		System.out.println(c2.toString()); //Circle@yyyyy
		System.out.println(c1.equals(c2)); //false
	}
}
