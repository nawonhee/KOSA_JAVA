class Copy implements Cloneable{ //extends Object
	int i;
	int []arr = {1,2,3};
	Object copy() {
		Object obj = null;
		try {
			obj = this.clone(); //딥카피 1.객체복제
			int []arrCopy = arr.clone(); //2
			
			Copy copy = (Copy)obj; //3
			copy.arr = arrCopy;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
public class CloneTest {
	public static void main(String[] args) {
		Copy origin = new Copy();
		origin.i = 9;
		origin.arr[0] = 9;
		
		//origin.clone
		Object obj = origin.copy();
		Copy copy = (Copy)obj;
		System.out.println("복제본객체의 i="+copy.i); //9
		System.out.println("복제본객체의 arr[0]="+copy.arr[0]); //9
		
		copy.i = 7; //복제본객체는 이제 7
		copy.arr[0] = 7;
		System.out.println("원본객체의 i="+origin.i); //9
		System.out.println("원본객체의 arr[0]="+origin.arr[0]); //7 : shallow copy , 9 : deep copy
	}
}
