import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

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
