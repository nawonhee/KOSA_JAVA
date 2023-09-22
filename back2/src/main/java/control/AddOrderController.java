package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.AddException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
import com.my.product.dto.Product;

public class AddOrderController extends OrderController{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.12:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginedId");
		String jsonStr;
		
		Map<String, Object> map = new HashMap<>();
		
		if(loginedId == null) {
			map.put("status",0);
			map.put("msg", "로그인하세요");
		}else {
			Map<String,Integer> cart = (Map)session.getAttribute("cart");
			if(cart == null) {
				map.put("status", 0);
				map.put("msg", "장바구니가 비었습니다");
			}else {
				OrderInfo info = new OrderInfo();
				info.setOrderId(loginedId);
				
				List<OrderLine> lines = new ArrayList<>();
				info.setLines(lines);
				for(String prodNo : cart.keySet()) {
					int quantity = cart.get(prodNo);
					
					OrderLine line = new OrderLine();
					Product p = new Product();
					p.setProdNo(prodNo);
					line.setOrderP(p);
					line.setOrderQuantity(quantity);
					lines.add(line);
				}
				try{
					service.add(info);
					session.removeAttribute("cart"); //장바구니 비우기
					map.put("status", 1);
				}catch(AddException e) {
					e.printStackTrace();
					map.put("status", 0);
					map.put("msg", e.getMessage());
				}
			}
		}
		out.print(mapper.writeValueAsString(map));
		return null;
	}
	
}
