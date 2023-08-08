import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


/* 내가 만든 거
 * 
public class FileCopy {
	public static String Read(String fileName){
		FileInputStream fis = null;
		String context = "";
		
		try {
			fis = new FileInputStream(fileName);
			int readValue = -1;
			while((readValue = fis.read())!=-1) {
				context += (char)readValue;
			}
		} catch (FileNotFoundException e) {
			System.out.println("복사할 원본파일이 없습니다");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return context;
		
	}
	public static void Write(String fileName, String c_context) {
		FileOutputStream fos = null;
		String context = c_context;
		
		try {
			fos = new FileOutputStream(fileName);
			byte[] bytes = context.getBytes();
			fos.write(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("복사할 원본파일명을 입력하세요: ");
		String o_file = sc.nextLine();
		System.out.println("붙여넣기할 파일명을 입력하세요: ");
		String c_file = sc.nextLine();
		
		String c_context = Read(o_file);
		Write(c_file, c_context);
	}

}
*/

public class FileCopy{
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		System.out.println("복사할 원본파일명을 입력하세요: ");
		String o_file = sc.nextLine();
		System.out.println("붙여넣기할 파일명을 입력하세요: ");
		String c_file = sc.nextLine();
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		
		try {
			fis = new FileInputStream(o_file);
		} catch (FileNotFoundException e) {
			System.out.println("복사할 원본파일이 없습니다");
			e.printStackTrace();
			return;
		} 
		
		try {
			fos = new FileOutputStream(c_file);
		}catch(FileNotFoundException e){ //주소나 경로가 잘못되었을 때
			e.printStackTrace();
			return;
		}
		
		//여기까지 왔으면 원본이나 복제본이나 다 제대로 생성된 것
		//int readValue;
		byte[] arr = new byte[1024]; //1킬로바이트씩 읽어와서 쓰기 하는 방법 -> 처리속도가 빠름
		int readCnt; //읽어온 바이트수
		try {
			//while((readValue = fis.read()) !=-1) {
				//fos.write(readValue); //동영상이나 사진 이런 것도 하려면 (char)붙이지 말고 그대로 쓰기
			//}
			while((readCnt = fis.read(arr))!=-1) {
				fos.write(arr,0,readCnt); //배열의 0번 인덱스부터 읽어온 바이트수만큼 쓰기할 것이다
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
