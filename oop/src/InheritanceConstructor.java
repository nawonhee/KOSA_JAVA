class ParentConstructor{
	ParentConstructor(){
		System.out.println("ParentConstructor()생성자가 호출됨");
	}
	ParentConstructor(String name){
		System.out.println("ParentConstructor("+name+")생성자가 호출됨");
	}
}

class ChildConstructor extends ParentConstructor{
	ChildConstructor(){
		System.out.println("ChildConstructor()생성자가 호출됨");
	}
	ChildConstructor(int age){
		super("나원희");
		System.out.println("ChildConstructor("+age+")생성자가 호출됨");
	}
}

public class InheritanceConstructor {
	public static void main(String[] args) {
		ParentConstructor p = new ParentConstructor();
		ChildConstructor c = new ChildConstructor(); //ParentConstructor() 생성자 호출됨
		                                             //ChildConstructor() 생성자 호출됨
		
		ChildConstructor c1 = new ChildConstructor(10); //ParentConstructor() 생성자 호출됨
		                                                //ChildConstructor(10) 생성자 호출됨
	}
}
