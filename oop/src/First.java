
public class First {
	static int smf;  //static 필드
	int mf;  //non-static 필드
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int lv;  //local variable 지역변수
		
		//인스턴스화 (객체만들기)
		First one; //참조형 지역변수
		one = new First(); //인스턴스화 (객체만들기)
		System.out.println(one.mf);
	}

}
