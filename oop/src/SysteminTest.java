import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SysteminTest {
	public static void main(String[] args) {
		InputStream is = System.in;
		/*
		try{
			//int readValue = is.read();
			//System.out.println(readValue);
			int readValue = -1;
			while((readValue = is.read())!= -1) {
				System.out.print((char)readValue);
			} //windows의 스트림종료 ctrl+z
		}catch(IOException e) {
			e.printStackTrace();
		}
		*/
		
		//InputStreamReader : InputStream -> Reader
		InputStreamReader isr = new InputStreamReader(is);
		try{
			//isr.read(); //문자단위로 읽기
			int readValue = -1;
			while((readValue = isr.read())!=-1) {
				System.out.print((char)readValue);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
