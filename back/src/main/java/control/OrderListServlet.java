package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.order.service.OrderService;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/orderlist")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService service;
	public OrderListServlet() {
		service = new OrderService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.12:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("application/json;charset=utf-8");
		
		HttpSession session = request.getSession();
//		String loginedId = (String)session.getAttribute("loginedId");
		String loginedId = "B";
		
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		
		Map<String, Object> map = new HashMap<>();
		
		if(loginedId == null) {
			map.put("status", 0);
			map.put("msg", "로그인하세요");
		}else {
			List list = new ArrayList<>();
			try{
				list = service.findById(loginedId);
				map.put("status", 1);
				map.put("list", list);
			}catch(FindException e) {
				e.printStackTrace();
				map.put("status", 0);
				map.put("msg", e.getMessage());
			}
		}
		out.print( mapper.writeValueAsString(map));
	}

}
