import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class FileTest {
	public static void main(String[] args) {
		File f;
		f = new File("D:\\");
		if(f.isDirectory()) {
			/*
			String[] list=f.list();
			for(String sub:list) {
				System.out.println(sub);
			}
			*/
			File[]files = f.listFiles();
			for(File file:files) {
				String name = file.getName();
				if(file.isFile()) {
					long fileSize = file.length();
					long lastModifiedTime = file.lastModified();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:MM");
					String formatStr = sdf.format(new Date(lastModifiedTime));
					System.out.println(name+"\t"+fileSize);
				}else { //\\d:의 하위폴더
					System.out.println("["+name+"]");
				}
			}
		}
		
		String folder = "D:\\attache";
		File file = new File(folder);
		if(!file.exists()) { //if(file.exists() == false){
			boolean result = file.mkdir();
			if(result) {
				System.out.println(folder+"폴더 생성 되었습니다");
			}else {
				System.out.println(folder+" 생성 안 되었습니다");
			}
		}else {
			System.out.println(folder+"폴더가 이미 존재합니다");
		}
		String fileName = "a.txt";
		File file1 = new File(file, fileName);
		try {
			if(!file1.exists()) {
				file1.createNewFile();
				System.out.println(fileName+"파일이 생성되었습니다");
			}else {
				System.out.println(fileName+"파일이 이미 존재합니다");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
