import java.io.*;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer("");
		
		int sum=0;
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		
		br.close();
		
		for(int i=0;i<N;i++) {
			sum += (int)s.charAt(i)-48;
		}
		
		bw.write(String.valueOf(sum));

		bw.close();
	}
}

