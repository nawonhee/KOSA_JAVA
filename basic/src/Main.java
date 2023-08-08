import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T, R;
		String S, new_S;
		
		T = sc.nextInt();
		
		for(int i=0;i<T;i++) {
			R = sc.nextInt();
			S = sc.next();
			int slength = S.length();
			for(int j=0;j<slength;j++) {
				for(int t=0;t<R;t++) {
					System.out.print(S.charAt(j));
				}
			}
			System.out.println("");
		}
	}
}