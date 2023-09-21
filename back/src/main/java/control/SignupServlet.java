package control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.customer.dto.Customer;
import com.my.customer.service.CustomerService;
import com.my.exception.AddException;
import com.my.util.Attach;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService service;
	public SignupServlet() {
		service = new CustomerService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.12:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		
		try {
			Attach attach=new Attach(request);
			String id=attach.getParameter("id");
			String pwd=attach.getParameter("pwd");
			String name=attach.getParameter("name");
			Customer c= new Customer(id, pwd, name);
			service.signup(c);
		
			try {
					String originProfileFileName=attach.getFile("f1").get(0).getName();
					attach.upload("f1", id+"_profile_"+originProfileFileName);
			
					String originIntroFileName=attach.getFile("f2").get(0).getName();
					attach.upload("f2", id+"_intro_"+originIntroFileName);
			} catch(Exception e) {
				
			}
		
			map.put("status", 1);
			map.put("msg", "가입성공");
		} catch(Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}
		
		out.print(mapper.writeValueAsString(map));
		
		
//		request.setCharacterEncoding("utf-8");
//		String encoding = "utf-8";
//		
//		String tempDir = "C:\\KOSA202307\\temp";
//		String attachesDir = "C:\\KOSA202307\\attaches"; //첨부경로
//		
//		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
//		File repository = new File(tempDir);
//		if(!repository.exists()) {
//			if(repository.mkdir()) {
//				System.out.println(tempDir+"폴더 생성");
//			}else {
//				System.out.println(tempDir+"폴더 생성 안 됨");
//				return;
//			}
//		}
//		
//		if(!new File(attachesDir).exists()) {
//			if(new File(attachesDir).mkdir()) {
//				System.out.println(attachesDir+"폴더 생성");
//			}else {
//				System.out.println(attachesDir+"폴더 생성 안 됨");
//				return;
//			}
//		}
//		
//		fileItemFactory.setRepository(repository); //업로드 경로 설정
//		fileItemFactory.setSizeThreshold(10*1024); //10*1024byte이상인 경우 임시파일이 만들어짐
//		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
//		
//		List<FileItem> items;
//		
//		try {
//			Customer c = new Customer();
//			FileItem profileFileItem = null;
//			FileItem introFileItem = null;
//			
//			items = fileUpload.parseRequest(request);
//			for(FileItem item: items) {
//				String fieldName = item.getFieldName();
//				if(item.isFormField()) { //요청전달데이터인 경우
//					String fieldValue = item.getString();
//					if(fieldName.equals("id")) {
//						c.setId(fieldValue);
//					}else if(fieldName.equals("pwd")) {
//						c.setPwd(fieldValue);
//					}else if(fieldName.equals("name")) {
//						c.setName(fieldValue);
//					}
//				}else { //첨부파일인 경우
//					String fileName = item.getName();
//					if()
//					if(item.getSize() > 0) {
//						UUID uuid = UUID.randomUUID();
//						File attacheFile = new File(attachesDir, "B"+uuid+"_"+item.getName());
//						try {
//							item.write(attacheFile); //첨부파일 서버에 저장
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//		} catch (FileUploadException e) {
//			e.printStackTrace();
//		}
//		
	}

}
