
public class MethodCallByValue {
	public static void m(int i) {
		i=99;  //실행 끝나면 사라짐 why? 참조해서 쓰는 것이므로 main에서 사용하고 있는 i랑은 다른애
	}
	
	public static void m(int[] arr) {
		arr[0] = 99;  //애초에 배열은 주소값 연산이므로 main에서 조작하든 여기서 조작하든 heap에 저장된 배열 내용물이 변함
	}
	public static void main(String[] args) {
		int i = 1;
		m(i);
		System.out.println(i); //1
		
		int[] arr = {1, 2, 3};
		m(arr);
		System.out.println(arr[0]); //99
	}
}
