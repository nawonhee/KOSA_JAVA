import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PrintStar_2444 {
	public static void main(String[] args) throws IOException  {
		int N; 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String star = "*";
		
		N = Integer.parseInt(br.readLine());
		int n = 2*N-1;
		
		for(int i=1;i<=N;i++) {
			System.out.println(" ".repeat(N-i)+"*".repeat(2*i-1));
		}
		for(int i=1;i<N;i++) {
			System.out.println(" ".repeat(i)+"*".repeat(2*(N-i)-1));
		}
	}
}