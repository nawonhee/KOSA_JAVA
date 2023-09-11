import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ChessPiece_3003{
	public static void main(String[] args) throws IOException {
		//BufferedReader 이용해서 풀기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" "); //공백 단위로 각 피스 개수 분리해서 배열에 저장
		int[] find_piece = new int[6]; //정수로 변환해서 받을 배열 선언
		int[] origin_piece = {1, 1, 2, 2, 2, 8}; //피스별로 원래 있어야 할 개수 담은 배열
		
		for(int i=0;i<6;i++) {
			find_piece[i] = Integer.parseInt(temp[i]); //정수로 변환
			System.out.print(origin_piece[i]-find_piece[i]+" "); //원래 개수에서 빼고 출력
		}
		
		/*
		//Scanner 이용
		Scanner sc = new Scanner(System.in);
		int[] find_piece = new int[6];
		int[] origin_piece = {1, 1, 2, 2, 2, 8};
		
		for(int i=0;i<6;i++) {
			find_piece[i] = sc.nextInt();
			System.out.print(origin_piece[i]-find_piece[i]+" ");
		}
		*/
	}
}