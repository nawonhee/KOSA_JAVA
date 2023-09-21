package control;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
		//post방식의 요청인 경우 요청바디의 default형식은 application/x-www-form-urlencoded입니다
//		String tValue = request.getParameter("t");
//		String f1Value = request.getParameter("f1");
		
		//post방식의 요청인 경우 요청바디의 형식은 multi-part/form-data인 경우
//		ServletInputStream sis = request.getInputStream();
//		Scanner sc = new Scanner(sis);
//		while(sc.hasNextLine()) {
//			System.out.println(sc.nextLine());
//		}
		
		String attachesDir = "D:\\KOSA202307\\attaches"; //첨부경로
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		File repository = new File(attachesDir);
		fileItemFactory.setRepository(repository);
		//fileItemFactory.
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			for(FileItem item: items) {
				if(item.isFormField()) { //요청전달데이터인 경우
					System.out.println(item.getFieldName() + ":" + item.getString());
				}else { //첨부파일인 경우
					System.out.println(item.getName() + ":" + item.getSize());
					if(item.getSize() > 0) {
						File attacheFile = new File(attachesDir, item.getName());
						try {
							item.write(attacheFile);
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
