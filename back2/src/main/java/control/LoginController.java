package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;

public class LoginController extends CustomerController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.12:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		//response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		//out.print("로그인 성공");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();

		HttpSession session = request.getSession();
		session.removeAttribute("loginedId");
		
		try {
			service.login(id, pwd);
			map.put("status", 1);
			map.put("msg", "로그인 성공");
			session.setAttribute("loginedId", id);
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", 0);
			map.put("msg", "로그인 실패");
		}
		String jsonStr = mapper.writeValueAsString(map);
		out.print(jsonStr);
		return null;
	}
	
}
