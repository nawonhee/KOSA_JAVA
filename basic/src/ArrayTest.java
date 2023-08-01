import java.util.Scanner;

public class ArrayTest {

	public static void main(String[] args) {
		int[] arr;
		arr = new int[4];
		arr[0] = 10;
		arr[1] = 20;
		arr[2] = 33;
		arr[3] = 40;
		System.out.println(arr.length); //() 없이! 주의
		
		int totalScore = 0;
		/*
		totalScore += arr[0];
		totalScore += arr[1];
		totalScore += arr[2];
		*/
		
		int length = arr.length;
		/*
		for(int i=0;i<length;i++)
			totalScore+=arr[i];
		*/
		for(int score:arr)
			totalScore+=score;
		
		float avg = (float)totalScore / length;
		System.out.println("totalScore="+totalScore+", avg="+avg);
		
		int[][] arrTwo = new int[3][2];
		/*
		arrTwo[0][0] = 1;
		arrTwo[0][1] = 2;
		arrTwo[1][0] = 3;
		arrTwo[1][1] = 4;
		arrTwo[2][0] = 5;
		arrTwo[2][1] = 6;
		*/
		
		int num=1;
		/*
		for(int col=0; col<2; col++, num++) {
			arrTwo[0][col] = num;
		}
		*/
		int rowLength = arrTwo.length;
		for(int row=0;row<rowLength;row++) {
			int colLength = arrTwo[row].length;
			for(int col=0;col<colLength;col++, num++) {
				arrTwo[row][col]=num;
			}
		}
		
		for(int row=0;row<rowLength;row++) {
			int colLength = arrTwo[row].length;
			for(int col=0;col<colLength;col++, num++) {
				System.out.print(arrTwo[row][col]+",");
			}
			System.out.println();
		}
		
		int [][]arrTwo2 = new int[3][]; //행별 열수 다르게 하기
		arrTwo2[0] = new int[1];
		arrTwo2[1] = new int[2];
		arrTwo2[2] = new int[3];
		System.out.println(arrTwo.length);
		System.out.println(arrTwo2[0].length);
		System.out.println(arrTwo2[1].length);
		System.out.println(arrTwo2[2].length);
		
		num=1;
		rowLength = arrTwo.length;
		for(int row=0;row<rowLength;row++) {
			int colLength = arrTwo2[row].length;
			for(int col=0;col<colLength;col++, num++) {
				arrTwo2[row][col]=num;
			}
		}
		
		for(int row=0;row<rowLength;row++) {
			int colLength = arrTwo2[row].length;
			for(int col=0;col<colLength;col++, num++) {
				System.out.print(arrTwo2[row][col]+",");
			}
			System.out.println();
		}
		
		int[] arr1 = {1, 10, 6, 3, 2, 3, 6, 1, 5, 7, 6, 5};

		System.out.println("숫자의 출현횟수를 출력하시오");
		
		int[] cnt = new int[10];
		
		for(int i=0;i<12;i++) {
			cnt[ arr1[i]-1 ]++;
		}
		
		for(int i=0;i<10;i++) {
			System.out.println((i+1)+"의 출현횟수-"+cnt[i]+"회");
		}
		
		int []arr3 = {5,4,7,1,2};
		System.out.println("최대값을 계산하시오");
		
		int max = arr3[0];
		
		for(int i=1;i<arr3.length;i++) {
			if(max<=arr3[i])
				max=arr3[i];
		}
		System.out.println(max);
		
		int []arr4 = {5, 4, 7, 1, 2};
		//정렬하기
		
		int []lotto = new int[6];
		for(int i=0;i<lotto.length;i++) {
			lotto[0] = (int) (Math.random()*45+1);
			for(int j=0;j<i;j++)
				if(lotto[i]==lotto[j])
					//continue;
					i--;
					break;
		}
		for(int value: lotto) {
			System.out.print(value+",");
		}
		
		System.out.println("");
		
		int [][]arrTwo3; 
		arrTwo3 = new int[5][];
		
		//행별 열생성
		for(int i=0;i<5;i++) {
			arrTwo3[i] = new int[i+1];
		}
		
		for(int i=0;i<5;i++) {
			arrTwo3[i][0]=1;
			int colLength = arrTwo3[i].length;
			for(int j=1;j<colLength-1;j++) {
				arrTwo3[i][j] = arrTwo3[i-1][j-1]+arrTwo3[i-1][j];
			}
			arrTwo3[i][arrTwo3[i].length-1] =1;
		}

		for(int i=0;i<5;i++) {
			int colLength = arrTwo3[i].length;
			for(int j=0;j<colLength;j++) {
				System.out.print(arrTwo3[i][j]+" ");
			}
			System.out.println("");
		}
		
		
		Scanner sc = new Scanner(System.in);
		int no = 0;
		
		String []subject = {"국어", "수학", "영어"};
		int subjectLength = subject.length;
		
		int [][]arrTwo4 = new int[10][subject.length];
		
		while(no<10) {
			System.out.print("점수입력을 진행하시겠습니까[y/n]");
			String yn = sc.next();
			if(yn.equals("n"))
				break;
			
			for(int i=0;i<subjectLength;i++) {
				System.out.print((no+1)+"번 학생의 "+subject[i]+"점수:");
				int s = sc.nextInt();
				arrTwo4[no][i]=s;
			}
			
			no++;
		}
		
		no =0;
		int []totalScoreSubject = new int[3];
		
		int best_no = 0; //최고점수 학생
		float best_avg = 0; //최고점수 평균
		float subject_avg = 0;
		
		int LowLength = arrTwo4.length;
		
		while(no<10) {
			System.out.print((no+1)+"번 학생점수: ");
			totalScore = 0;
			
			for(int i=0;i<subjectLength;i++) {
				System.out.print(subject[i]+"-"+arrTwo4[no][i]+", ");
				totalScore+=arrTwo4[no][i];
				totalScoreSubject[i]=arrTwo4[no][i];
			}
			avg = (float) totalScore/3;
			System.out.println("총점-"+totalScore+", 평균-"+avg);
			
			if(best_avg<avg) {
				best_avg = avg;
				best_no = no;
			}
			
			no++;
		}
		
		for(int i=0;i<subjectLength;i++) {
			subject_avg = (float)totalScoreSubject[i]/3;
			System.out.println(subject[i]+"과목 평균 : "+subject_avg);
		}
		
		System.out.println("1등학생 : "+(best_no+1)+"번 학생"); 
	}
}
