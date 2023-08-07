
public class WrapperTest {
	public static void main(String[] args) {
		int i=100;
		Object obj;
		//기본형(int) -> 참조형(Integer) -> Object로 업캐스팅
		obj = Integer.valueOf(i); 
		obj = i; //AutoBoxing됨
		         //컴파일시에 Integer.valueOf(i);로 코드가 바뀜
		
		int j;
		//Object를 다운캐스팅 UnBoxing :{Integer(참조형) -> int(기본형)}
		j = ((Integer)obj).intValue(); //obj를 Integer로 다운캐스팅하고 intValue()로 기본형 변환
		
		j = (Integer)obj; //AutoUnboxing됨
		                  //컴파일시에  ((Integer)obj).intValue();로 코드가 바뀜
	}
}
