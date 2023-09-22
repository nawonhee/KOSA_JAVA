package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends CustomerController{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.12:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginedId");
		session.invalidate();
		return null;
	}
	
}
