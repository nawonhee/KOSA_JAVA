import java.util.StringTokenizer;

public class StringTest {
	public static void main(String[] args) {
		String s = "안녕하세요";
		char c = s.charAt(0); //'안'
		int size = s.length(); //5
		
		s="가나다라다나가";
		//System.out.println("Palindrome문자열입니다");
		//System.out.println("Palindrome문자열이 아닙니다");
		
		/*
		if(s.charAt(0)!=s.charAt(size-1)) {
			System.out.println("Palindrome문자열이 아닙니다");
		}
		else if(s.charAt(1)!=s.charAt(size-1-1)) {
			System.out.println("Palindrome문자열이 아닙니다");
		}
		else if(s.charAt(2)!=s.charAt(size-1-2)) {
			System.out.println("Palindrome문자열이 아닙니다");
		}else {
			System.out.println("Palindrome문자열입니다");
		}
		*/

		int i =0;
		int max = size/2;
		for(i=0;i<size/2;i++) {
			if(s.charAt(i)==s.charAt(size-1-i)) {
				break;
			}
		}
		
		if(i==max) {
			System.out.println("Palindrome문자열입니다");
		}else {
			System.out.println("Palindrome문자열이 아닙니다");
		}
		
		s="100:70:65";
		s="100::65";
		String delim=":";
		String[]arr = s.split(delim);
		
		for(String str:arr) {
			if(str.equals("")) {
				System.out.println("미응시");
			}else {
				System.out.println(Integer.parseInt(str));
			}
		}
		
		System.out.println("-----------------");
		StringTokenizer st = new StringTokenizer(s,delim);
		while(st.hasMoreTokens()) {
			String str = st.nextToken(); //"100", "65"
			System.out.println(str);
		}
	}
}
