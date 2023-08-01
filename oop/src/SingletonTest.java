class Single{
	static Single s = new Single();
	private Single() { //얘 덕분에 다른 클래스에서 생성자 못 만든다
		
	}
	/*
	void m() {
		new Single(); //ok 본인 클래스 안에서는 생성자 만들 수 있음
	}
	*/
	static Single getInstance(){
		//return null;
		//return new Single();
		return s;
	}
}


public class SingletonTest {

	public static void main(String[] args) {
		Single s1,s2;
		//s1 = new Single(); //error 
		s1 = Single.getInstance();
		s2 = Single.getInstance();
		System.out.println(s1==s2);
	}

}
