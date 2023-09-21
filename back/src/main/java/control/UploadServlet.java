package control;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.12:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		//post방식의 요청인 경우 요청바디의 default형식은 application/x-www-form-urlencoded입니다
//		String tValue = request.getParameter("t");
//		String f1Value = request.getParameter("f1");
		
		//post방식의 요청인 경우 요청바디의 형식은 multi-part/form-data인 경우
//		ServletInputStream sis = request.getInputStream();
//		Scanner sc = new Scanner(sis);
//		while(sc.hasNextLine()) {
//			System.out.println(sc.nextLine());
//		}
		
		String tempDir = "C:\\KOSA202307\\temp";
		String attachesDir = "C:\\KOSA202307\\attaches"; //첨부경로
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		File repository = new File(tempDir);
		if(!repository.exists()) {
			if(repository.mkdir()) {
				System.out.println(tempDir+"폴더 생성");
			}else {
				System.out.println(tempDir+"폴더 생성 안 됨");
				return;
			}
		}
		
		if(!new File(attachesDir).exists()) {
			if(new File(attachesDir).mkdir()) {
				System.out.println(attachesDir+"폴더 생성");
			}else {
				System.out.println(attachesDir+"폴더 생성 안 됨");
				return;
			}
		}
		
		fileItemFactory.setRepository(repository); //업로드 경로 설정
		fileItemFactory.setSizeThreshold(10*1024); //10*1024byte이상인 경우 임시파일이 만들어짐
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			for(FileItem item: items) {
				if(item.isFormField()) { //요청전달데이터인 경우
					System.out.println(item.getFieldName() + ":" + item.getString());
				}else { //첨부파일인 경우
					System.out.println(item.getName() + ":" + item.getSize());
					if(item.getSize() > 0) {
						UUID uuid = UUID.randomUUID();
						File attacheFile = new File(attachesDir, "B"+uuid+"_"+item.getName());
						try {
							item.write(attacheFile); //첨부파일 서버에 저장
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
	}
}
