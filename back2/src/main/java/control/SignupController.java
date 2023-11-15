package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.customer.dto.Customer;
import com.my.util.Attach;

public class SignupController extends CustomerController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.12:5174");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		
		try {
//			Attach attach=new Attach(request);
//			String id=attach.getParameter("id");
//			String pwd=attach.getParameter("pwd");
//			String name=attach.getParameter("name");
			String id=request.getParameter("id");
			String pwd=request.getParameter("pwd");
			String name=request.getParameter("name");
			System.out.println(id+" "+pwd+" "+name);
			Customer c= new Customer(id, pwd, name);
			service.signup(c);
		
//			try {
//					String originProfileFileName=attach.getFile("f1").get(0).getName();
//					attach.upload("f1", id+"_profile_"+originProfileFileName);
//			
//					String originIntroFileName=attach.getFile("f2").get(0).getName();
//					attach.upload("f2", id+"_intro_"+originIntroFileName);
//			} catch(Exception e) {
//				
//			}
		
			map.put("status", 1);
			map.put("msg", "가입성공");
		} catch(Exception e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", e.getMessage());
		}
		
		out.print(mapper.writeValueAsString(map));
		
		return null;
	}
	
	
}
