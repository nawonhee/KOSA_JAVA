package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;

public class IdDupChkController extends CustomerController{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.12:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		String id = request.getParameter("id");
		System.out.println(id);
		
		Map<String, Object> map = new HashMap<>();
		
		try{
			service.idDupChk(id);
			map.put("status", 0);
		}catch(FindException e) {
			map.put("status", 1);
		}
		
		out.print(mapper.writeValueAsString(map));
		return null;
	}
	
}
