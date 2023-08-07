import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

public class FileIOTest {
	public static void write() {
		/*
		 * 스트림종류 : 출력스트림
		 * 목적지 : 파일
		 */
		/*
		String fileName = "D:\\b.txt";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			//fos.write(65); //A가 써짐
			byte[] bytes = "ABCDEFG".getBytes();
			fos.write(bytes); //bytes배열로 덮어써짐
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		String fileName = "D:\\c.txt";
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName,true); //생성자인자 두번째를 통해 append 옵션을 준다, true를 써주면 파일 끝에 추가로 써짐
			fw.write("가나다라마바사");
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fw !=null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void read() {
		/*
		 * 스트림종류 : 바이트입력스트림
		 * 자원 : 파일
		 */
		
		
		String fileName = "D:\\a.txt"; //백슬래쉬 두번 써줘야 함 
		
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			int readValue = -1;
			while((readValue = fis.read()) != -1) {
				System.out.print((char)readValue);
			}
		}catch(FileNotFoundException e) { //얘가 자식exception이라 위로 와야 함
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		
		
		/*
		 * 스트림종류 : 문자입력스트림FileReader
		 * 자원 : 파일
		 */
		/*
		String fileName = "D:\\a.txt";
		
		FileReader fr = null; //초기화
		try {
			fr = new FileReader(fileName); //자원과의 연결
			int readValue = -1;
			while((readValue = fr.read())!=-1) {
				System.out.print((char)readValue);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fr !=null) {
				try {
					fr.close(); //자원과의 연결 해제
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		*/
	}
	public static void main(String[] args) {
		//read();
		write();
		
	}
}
